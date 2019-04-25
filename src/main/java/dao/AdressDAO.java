package dao;

import domain.Adress;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdressDAO implements dao.interfaceDAO.AdressDAO {
    private final static Logger log = Logger.getLogger(AdressDAO.class);

    private Connection connection;

    public AdressDAO(Connection connection){
        this.connection = connection;
    }

    public void insertAdress(Adress adress) throws SQLException{
        String s = "SELECT id FROM Contacts ORDER BY id desc";
        PreparedStatement prst = connection.prepareStatement(s);
        ResultSet rs = prst.executeQuery();

        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
        }
        String sql = "Insert into Adress (contact_id, country, city, street, home, apartment, postcode) values(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        try {
            statement.setInt(1, id);
            statement.setString(2, adress.getCountry());
            statement.setString(3, adress.getCity());
            statement.setString(4, adress.getStreet());
            statement.setInt(5, adress.getHome());
            statement.setInt(6, adress.getApartment());
            statement.setInt(7, adress.getPostcode());

            statement.executeUpdate();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    public static Adress makeAdress(ResultSet rs, int contact_id) throws SQLException{
        Adress adress = new Adress.Builder()
                .withCountry(rs.getString("country"))
                .withCity(rs.getString("city"))
                .withHome(rs.getInt("home"))
                .withStreet(rs.getString("street"))
                .withApartment(rs.getInt("apartment"))
                .withPostcode(rs.getInt("postcode"))
                .withContactId(contact_id)
                .build();
        return adress;
    }

    public Adress findAdress(int contact_id) throws SQLException{
        String sql = "SELECT * FROM Adress WHERE contact_id=?";

        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1,contact_id);

        ResultSet rs = prst.executeQuery();

        if(rs.next()) {
            return makeAdress(rs, contact_id);
        }
        return null;
    }

    public void updateAdress(int contact_id, Adress adress) throws SQLException{
        String sql = "UPDATE adress set country =?, city=?, street=?, home=?, apartment=?,  postcode=? where contact_id=?";

        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setString(1, adress.getCountry());
        prst.setString(2, adress.getCity());
        prst.setString(3, adress.getStreet());
        prst.setInt(4, adress.getHome());
        prst.setInt(5, adress.getApartment());
        prst.setInt(6, adress.getPostcode());
        prst.setInt(7, contact_id);

        prst.executeUpdate();
    }
}
