package dao;

import domain.Contact;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

import static dao.AdressDAO.makeAdress;

public class ContactDAO implements dao.interfaceDAO.ContactDAO {
    private final static Logger log = Logger.getLogger(ContactDAO.class);
    private Connection connection;

    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertContact(Contact contact) throws SQLException{
        String sql = "Insert into Contacts(name, birthDate, gender, nationality, mariageStatus, webSite, eMail, placeOfWork) values(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prst = connection.prepareStatement(sql);
        try {
            prst.setString(1,contact.getName());
            prst.setString(2,contact.getBirthDate());
            prst.setString(3,contact.getGender());
            prst.setString(4,contact.getCitizenship());
            prst.setString(5,contact.getMarriageStatus());
            prst.setString(6,contact.getWebSite());
            prst.setString(7,contact.geteMail());
            prst.setString(8,contact.getPlaceOfWork());

            prst.executeUpdate();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.info("Created new contact: " + contact.getName());
    }

    public void updateContact(Contact contact) throws SQLException {
        String sql = "Update Contacts set name =?, birthDate=?, gender=?, nationality=?, mariageStatus=?,  webSite=?, eMail=?, placeOfWork=? where id=? ";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, contact.getName());
        pstm.setString(2, contact.getBirthDate());
        pstm.setString(3, contact.getGender());
        pstm.setString(4, contact.getCitizenship());
        pstm.setString(5, contact.getMarriageStatus());
        pstm.setString(6, contact.getWebSite());
        pstm.setString(7, contact.geteMail());
        pstm.setString(8, contact.getPlaceOfWork());
        pstm.setInt(9, contact.getId());

        PhoneDAO phoneDAO = new PhoneDAO(connection);
        phoneDAO.deletePhones(contact.getId());
        if (contact.getList()!=null) {
            phoneDAO.groupInsertPhone(contact.getList());
        }
        pstm.executeUpdate();
        log.info("Updated contact: " + contact.getName());
    }

    public List<Contact> queryContact() throws SQLException {
        String sql = "Select * from Contacts c INNER JOIN Adress a ON c.id = a.contact_id;";

        PreparedStatement prst = connection.prepareStatement(sql);

        ResultSet rs = prst.executeQuery();
        List<Contact> list = new ArrayList<Contact>();
        while (rs.next()){
            list.add(makeContact(rs));
        }

        return list;
    }

    public Contact findContactById(int id) throws SQLException{
        String sql = "Select * from Contacts c INNER JOIN Adress a ON c.id = a.contact_id where c.id = ?;";

        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1, id);

        ResultSet rs = prst.executeQuery();

        if (rs.next()){
            return makeContact(rs);
        }
        return null;
    }



    private static Contact makeContact(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        Contact contact = new Contact.Builder()
                .withId(id)
                .withName(rs.getString("name"))
                .withBirthDate(rs.getString("birthDate"))
                .withPlaceOfWork(rs.getString("placeOfWork"))
                .withGender(rs.getString("gender"))
                .withCitizenship(rs.getString("nationality"))
                .withMarriageStatus(rs.getString("mariageStatus"))
                .withWebSite(rs.getString("webSite"))
                .withEmail(rs.getString("eMail"))
                .withAdress(makeAdress(rs,id))
                .build();
        return contact;
    }

    public List<Contact> querySearch(String querry) throws SQLException{
        String sql = "Select * from Contacts Where MATCH(name, gender, nationality, mariageStatus, webSite, eMail, placeOfWork) AGAINST(?)";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setString(1,querry);
        AdressDAO adressDAO = new AdressDAO(connection);
        ResultSet rs = prst.executeQuery();
        List<Contact> list = new ArrayList<Contact>();
        int id = 0;
        while (rs.next()){
            id = rs.getInt("id");
            list.add(new Contact.Builder()
                .withId(id)
                .withName(rs.getString("name"))
                .withBirthDate(rs.getString("birthDate"))
                .withGender(rs.getString("gender"))
                .withCitizenship(rs.getString("nationality"))
                .withMarriageStatus(rs.getString("mariageStatus"))
                .withWebSite(rs.getString("webSite"))
                .withEmail(rs.getString("eMail"))
                .withPlaceOfWork(rs.getString("placeOfWork"))
                .withAdress(adressDAO.findAdress(id))
                .build());
        }
        return list;
    }

    public void deleteContact(int id) throws SQLException{
        String sql = "Delete from Contacts where id = ?";

        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1,id);

        prst.executeUpdate();
        log.info("Deleted contact with id = : " + id);
    }


    public List<Contact> querryBirthDateContacts() throws SQLException{
        Calendar calendar = Calendar.getInstance();

        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);

        List<Contact> list = new ArrayList<Contact>();

        String sql = "Select * from Contacts where birthDate like '%" + month + "-" + day + "';";
        PreparedStatement prst = connection.prepareStatement(sql);

        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            list.add(new Contact.Builder()
                    .withId(rs.getInt("id"))
                    .withName(rs.getString("name"))
                    .withBirthDate(rs.getString("birthDate"))
                    .withPlaceOfWork(rs.getString("placeOfWork"))
                    .withGender(rs.getString("gender"))
                    .withCitizenship(rs.getString("nationality"))
                    .withMarriageStatus(rs.getString("mariageStatus"))
                    .withWebSite(rs.getString("webSite"))
                    .withEmail(rs.getString("eMail"))
                    .build());
        }

        log.info("Email with birthday contacts sended");
        return list;
    }

}
