package dao.interfaceDAO;

import domain.Contact;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
    List<Contact> queryContact() throws SQLException;
    void updateContact(Contact contact) throws SQLException;
    Contact findContactById(int id) throws SQLException;
    void insertContact(Contact contact) throws SQLException;
    void deleteContact(int id) throws SQLException;
    List<Contact> querySearch(String querry) throws SQLException;
}
