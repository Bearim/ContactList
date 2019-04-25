package dao.interfaceDAO;

import domain.Adress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdressDAO {
    void insertAdress(Adress adress) throws SQLException;
    Adress findAdress(int contact_id) throws SQLException;

}
