package dao.interfaceDAO;

import domain.Phone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PhoneDAO {
    void groupInsertPhone(ArrayList<Phone> list) throws SQLException;
    List<Phone> queryPhone(int id) throws SQLException;
    void insertPhone(Phone phone) throws SQLException;
    void deletePhones(int id) throws SQLException;
}
