package dao.interfaceDAO;

import domain.Photo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public interface PhotoDAO {
    Photo getImageInTable(int contact_id) throws SQLException;
    FileInputStream extractBytes (String ImageName) throws IOException;
    void  setImageInTable(InputStream is, String image_name) throws SQLException, IOException;
    void updatePhoto(InputStream is, String imageName, int contact_id) throws SQLException, IOException;
}
