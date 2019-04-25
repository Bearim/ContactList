package dao;

import domain.Phone;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAO implements dao.interfaceDAO.PhoneDAO {
    private final static Logger log = Logger.getLogger(PhoneDAO.class);

    private Connection connection;

    public PhoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void groupInsertPhone(ArrayList<Phone> list) throws SQLException {
        for(Phone phone: list){
            insertPhone(phone);
        }
    }

    public List<Phone> queryPhone(int id) throws SQLException {
        String sql = "Select * from Phones Where contact_id = ?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1, id);

        ResultSet rs = prst.executeQuery();
        List<Phone> list = new ArrayList<Phone>();
        while (rs.next()){
            list.add(makePhone(rs));
        }
        return list;
    }

    public void insertPhone(Phone phone) throws SQLException{
        String s = "SELECT id FROM Contacts ORDER BY id desc";
        PreparedStatement prst = connection.prepareStatement(s);
        ResultSet rs = prst.executeQuery();

        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
        }
        String sql = "Insert into Phones(contact_id, phone, description, comment) values(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        try {
            statement.setInt(1, id);
            statement.setString(2, phone.getNumber());
            statement.setString(3, phone.getDescription());
            statement.setString(4, phone.getComment());

            statement.executeUpdate();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private static Phone makePhone(ResultSet rs) throws SQLException{
        Phone phone = new Phone.Builder()
                .withContactId(rs.getInt("contact_id"))
                .withNumber(rs.getString("phone"))
                .withComment(rs.getString("comment"))
                .withDescription(rs.getString("description"))
                .build();
        return phone;
    }

    public void deletePhones(int id) throws SQLException{
        String sql = "Delete from Phones where contact_id = ?";

        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1, id);

        prst.executeUpdate();
    }
}
