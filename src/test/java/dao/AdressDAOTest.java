package dao;

import domain.Adress;
import domain.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;
import static utils.JdbcUtils.getConnection;

public class AdressDAOTest {
    Connection connection;
    ContactDAO contactDAO;
    AdressDAO adressDAO;
    Adress adress;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/ContactList",
                "root", "qwerty");
        contactDAO = new ContactDAO(connection);
        adressDAO = new AdressDAO(connection);
    }

    @Test
    public void insertAdress() throws Exception {
        contactDAO.insertContact(new Contact.Builder()
                .withName("Vlad Valov")
                .withBirthDate("1988-01-13")
                .withPlaceOfWork("NetCracker")
                .withGender("Female")
                .withCitizenship("Belarus")
                .withMarriageStatus("Single")
                .withWebSite("http://evgeniy.com")
                .withEmail("autoemail@email.com")
                .build());
        adress = new Adress.Builder()
                .withCity("mis")
                .withStreet("asd")
                .withHome(1)
                .withCountry("belarus")
                .withApartment(1)
                .withPostcode(121314)
                .build();
        adressDAO.insertAdress(adress);
        assertTrue((adressDAO.findAdress(getLastId()).getCity().equals(adress.getCity())&&(adressDAO.findAdress(getLastId()).getStreet().equals(adress.getStreet()))));
    }

    @Test
    public void makeAdress() throws Exception {
        Contact contact = contactDAO.findContactById(getLastId());
        assertTrue((contact.getAdress().getCity().equals("mis")&&(contact.getAdress().getStreet().equals("asd"))));
    }

    @Test
    public void findAdress() throws Exception {
        Adress adress = adressDAO.findAdress(getLastId());
        assertTrue((adress.getCity().equals("mis"))&&(adress.getStreet().equals("asd")));
    }

    @Test
    public void updateAdress() throws Exception {
        adress = new Adress.Builder()
                .withCity("minsk")
                .withStreet("surganova")
                .withHome(2)
                .withCountry("russia")
                .withApartment(2)
                .withPostcode(222222)
                .build();
        adressDAO.updateAdress(getLastId(),adress);
        assertTrue((adressDAO.findAdress(getLastId()).getCity().equals("minsk")&&(adressDAO.findAdress(getLastId()).getStreet().equals("surganova"))));
    }

    public int getLastId() throws SQLException {
        String s = "SELECT id FROM Contacts ORDER BY id desc";
        PreparedStatement prst = connection.prepareStatement(s);
        ResultSet rs = prst.executeQuery();

        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }
}