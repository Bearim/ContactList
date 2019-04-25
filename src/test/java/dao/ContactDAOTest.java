package dao;

import domain.Adress;
import domain.Contact;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class ContactDAOTest {
    Connection connection ;
    ContactDAO testContactDAO;
    Adress adress;
    AdressDAO adressDAO;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/ContactList",
                "root", "qwerty");
        testContactDAO = new ContactDAO(connection);
        adressDAO = new AdressDAO(connection);
        adress = new Adress.Builder()
                .withCity("mis")
                .withStreet("asd")
                .withHome(1)
                .withCountry("belarus")
                .withApartment(1)
                .withPostcode(121314)
                .build();
    }

    @Test
    public void insertContact() throws Exception {
        testContactDAO.insertContact(new Contact.Builder()
                .withName("Evgeniy Vladimirovich")
                .withBirthDate("1999-09-25")
                .withPlaceOfWork("iTechArt")
                .withGender("Female")
                .withCitizenship("Belarus")
                .withMarriageStatus("Single")
                .withWebSite("http://evgeniy.com")
                .withEmail("autoemail@email.com")
                .build());
        adressDAO.insertAdress(adress);
        testContactDAO.insertContact(new Contact.Builder()
                .withName("Anatoliy Vaserman")
                .withBirthDate("1961-05-01")
                .withPlaceOfWork("BSUIR")
                .withGender("Female")
                .withCitizenship("Russia")
                .withMarriageStatus("Marriage")
                .withWebSite("http://anatoliy.com")
                .withEmail("autoemail@email.com")
                .build());
        adressDAO.insertAdress(adress);
        testContactDAO.insertContact(new Contact.Builder()
                .withName("Vlad Valov")
                .withBirthDate("1988-01-13")
                .withPlaceOfWork("NetCracker")
                .withGender("Female")
                .withCitizenship("Belarus")
                .withMarriageStatus("Single")
                .withWebSite("http://evgeniy.com")
                .withEmail("autoemail@email.com")
                .build());
        adressDAO.insertAdress(adress);
        assertEquals(testContactDAO.queryContact().size(), 3);
    }

    @Test
    public void updateContact() throws Exception{
        List<Contact> contactsBefore = testContactDAO.queryContact();
        testContactDAO.updateContact(new Contact.Builder()
                .withId(getLastId()-2)
                .withName("Vlad Valov")
                .withBirthDate("1988-01-13")
                .withPlaceOfWork("NetCracker")
                .withGender("Female")
                .withCitizenship("Belarus")
                .withMarriageStatus("Single")
                .withWebSite("http://evgeniy.com")
                .withEmail("autoemail@email.com")
                .build());
        testContactDAO.updateContact(new Contact.Builder()
                .withId(getLastId()-1)
                .withName("Anatoliy Vaserman")
                .withBirthDate("1961-05-01")
                .withPlaceOfWork("BSUIR")
                .withGender("Female")
                .withCitizenship("Russia")
                .withMarriageStatus("Marriage")
                .withWebSite("http://anatoliy.com")
                .withEmail("autoemail@email.com")
                .build());
        testContactDAO.updateContact(new Contact.Builder()
                .withId(getLastId())
                .withName("Evgeniy Vladimirovich")
                .withBirthDate("1999-03-24")
                .withPlaceOfWork("iTechArt")
                .withGender("Female")
                .withCitizenship("Belarus")
                .withMarriageStatus("Single")
                .withWebSite("http://evgeniy.com")
                .withEmail("autoemail@email.com")
                .build());
        List<Contact> contactsAfter = testContactDAO.queryContact();
        assertTrue(!contactsAfter.equals(contactsBefore));
    }

    @Test
    public void queryContact() throws Exception{
        assertEquals(testContactDAO.queryContact().size(), 3);
    }

    @Test
    public void findContactById() throws Exception{
        Contact contact1 = testContactDAO.findContactById(getLastId()-2);
        Contact contact2 = testContactDAO.findContactById(getLastId()-1);
        Contact contact3 = testContactDAO.findContactById(getLastId());
        assertTrue((contact1.getName().equals("Vlad Valov"))&&(contact2.getName().equals("Anatoliy Vaserman"))&&(contact3.getName().equals("Evgeniy Vladimirovich")));
    }

    @Test
    public void deleteContact() throws Exception {
        List<Contact> list = testContactDAO.queryContact();
        for (Contact c : list){
            testContactDAO.deleteContact(c.getId());
        }
        list = testContactDAO.queryContact();
        assertTrue(list.size()==0);
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