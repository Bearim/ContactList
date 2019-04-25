package dao;

import domain.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO implements dao.interfaceDAO.DocumentDAO {
    private Connection connection;

    public DocumentDAO(Connection connection){
        this.connection = connection;
    }

    public Document getDocumentFromBD(int id, String  fileName) throws SQLException{
        String sql = "Select * from Documents where contact_id = ? and file_name = ?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1,id);
        prst.setString(2,fileName);
        ResultSet rs = prst.executeQuery();
        if(rs.next()){
            return new Document.Builder()
                    .withContactId(rs.getInt("contact_id"))
                    .withFileName(rs.getString("file_name"))
                    .withFileData(rs.getBlob("file_data"))
                    .withComment(rs.getString("comment"))
                    .build();
        }
        return null;
    }

    public List<Document> querryDocuments(int id) throws SQLException{
        List<Document> list = new ArrayList<Document>();
        String sql = "Select * from Documents where contact_id = ?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1, id);
        ResultSet rs = prst.executeQuery();
        while (rs.next()){
            list.add(new Document.Builder()
                    .withContactId(rs.getInt("contact_id"))
                    .withFileName(rs.getString("file_name"))
                    .withComment(rs.getString("comment"))
                    .withFilePath(rs.getString("file_path"))
                    .build());
        }
        return list;
    }
    private void writeToDB(String fileName, String path, String description) throws SQLException, IOException {

        String sql = "Insert into Documents(contact_id, file_name, file_data, file_path, comment) values (?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        File imgPath = new File(path);

        int id = getId();
        pstm.setLong(1, id);
        pstm.setString(2, imgPath.getName());
        pstm.setBinaryStream(3, extractBytes(path), (int) imgPath.length());
        pstm.setString(4, path);
        pstm.setString(5, description);
        pstm.executeUpdate();
    }

    public int getId() throws SQLException {
        String sel = "SELECT id FROM Contacts ORDER BY id desc";
        PreparedStatement prst = connection.prepareStatement(sel);
        ResultSet rs = prst.executeQuery();

        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }

    private FileInputStream extractBytes (String path) throws IOException {
        File imgPath = new File(path);
        FileInputStream fis = new FileInputStream(imgPath);

        return fis;
    }

    public void writeArrayToDB(String[] fileNames, String[] arrayOfPath,  String[] descriptions) throws SQLException, IOException{
        for(int i = 0; i < arrayOfPath.length; i++){
            writeToDB(fileNames[i], arrayOfPath[i], descriptions[i]);
        }
    }

    private void deleteAttachment(int contact_id) throws SQLException{
        String sql = "Delete from Documents where contact_id = ?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setInt(1, contact_id);
        prst.executeUpdate();
    }

    public void updateDocuments(String[] fileNames, String[] arrayOfPath,  String[] descriptions, int id) throws SQLException, IOException{
        deleteAttachment(id);
        writeArrayToDB(fileNames,arrayOfPath,descriptions);
    }
}
