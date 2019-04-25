package dao.interfaceDAO;

import domain.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DocumentDAO {
    Document getDocumentFromBD(int id, String  fileName) throws SQLException;
    List<Document> querryDocuments(int id) throws SQLException;
    int getId() throws SQLException;
    void writeArrayToDB(String[] fileNames, String[] arrayOfPath,  String[] descriptions) throws SQLException, IOException;
}
