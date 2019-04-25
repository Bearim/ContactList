package dao;

import domain.Photo;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAO implements dao.interfaceDAO.PhotoDAO {
    private Connection connection;

    public PhotoDAO(Connection connection){
        this.connection = connection;
    }

    public void  setImageInTable(InputStream is, String image_name) throws SQLException, IOException {
        String sel = "SELECT id FROM Contacts ORDER BY id desc";
        PreparedStatement statement = connection.prepareStatement(sel);
        ResultSet rs = statement.executeQuery();
        int id = 0;

        if(rs.next()) {
            id = rs.getInt("id");
            String sql = "Insert into Photos(contact_id, image_data, image_file_name) VALUES(?, ?, ?)";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.setInt(1,id);
            prst.setBinaryStream(2, is);
            prst.setString(3, image_name);
            prst.executeUpdate();
        }
    }


    public FileInputStream extractBytes (String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        FileInputStream fis = new FileInputStream(imgPath);

        return fis;
    }

    public Photo getImageInTable(int contact_id) throws SQLException{
        String sql = "Select contact_id, image_data, image_file_name from Photos where contact_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, contact_id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            byte[] imageData = rs.getBytes("image_Data");
            String imageFileName = rs.getString("image_file_name");
            return new Photo.Builder()
                    .withContactId(contact_id)
                    .withImageData(imageData)
                    .withImageFileName(imageFileName)
                    .build();
        }
        return null;
    }

    public void updatePhoto(InputStream is, String imageName, int contact_id) throws SQLException, IOException{
        String sql = "Update Photos Set image_data = ?, image_file_name = ? where contact_id = ?";
        PreparedStatement prst = connection.prepareStatement(sql);

        prst.setBinaryStream(1, is);
        prst.setString(2, imageName);
        prst.setInt(3, contact_id);
        prst.executeUpdate();
    }
}
