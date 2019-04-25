package dao;

import domain.Adress;
import domain.Contact;
import domain.Phone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PhoneDAOTest {
    Connection connection;
    ContactDAO contactDAO;
    AdressDAO adressDAO;
    Phone phone;
    PhoneDAO phoneDAO;
    Contact contact;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/ContactList",
                "root", "qwerty");
        contactDAO = new ContactDAO(connection);
        phoneDAO = new PhoneDAO(connection);
        adressDAO = new AdressDAO(connection);

    }

    @Test
    public void groupInsertPhone() throws SQLException {
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
        Adress adress = new Adress.Builder()
                .withCity("mis")
                .withStreet("asd")
                .withHome(1)
                .withCountry("belarus")
                .withApartment(1)
                .withPostcode(121314)
                .build();
        adressDAO.insertAdress(adress);
        ArrayList<Phone> list = new ArrayList<>();
        list.add(new Phone.Builder()
                .withContactId(getLastId())
                .withNumber("+375339003027")
                .withDescription("home")
                .withDescription("My phone")
                .build());
        list.add(new Phone.Builder()
                .withContactId(getLastId())
                .withNumber("+375338002017")
                .withDescription("mobile")
                .withDescription("Your Phone")
                .build());
        list.add(new Phone.Builder()
                .withContactId(getLastId())
                .withNumber("+375337001007")
                .withDescription("mobile")
                .withDescription("Our phone")
                .build());
        phoneDAO.groupInsertPhone(list);
        assertTrue(phoneDAO.queryPhone(getLastId()).size()==3);
    }

    @Test
    public void queryPhone() throws SQLException{
        assertTrue(phoneDAO.queryPhone(getLastId()).size()==3);
    }

    @Test
    public void deletePhones() throws SQLException {
        phoneDAO.deletePhones(getLastId());
    }

    public int getLastId() throws SQLException{
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