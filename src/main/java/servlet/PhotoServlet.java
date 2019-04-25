package servlet;

import dao.PhotoDAO;
import domain.Photo;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import static utils.JdbcUtils.getConnection;

@WebServlet(urlPatterns = {"/image"})
public class PhotoServlet extends HttpServlet{
    private final static String NO_PHOTO = "/WEB-INF/images/no-photo.jpg";
    private final static Logger log = Logger.getLogger(PhotoServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection;
        Photo photo;
        try {

            connection = getConnection();
            PhotoDAO photoDAO = new PhotoDAO(connection);
            int id = Integer.parseInt(request.getParameter("id"));
            photo = photoDAO.getImageInTable(id);

            if ((photo==null)||photo.getImageFileName().equals("")) {
                // No record found, redirect to default image.
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher(NO_PHOTO);
                dispatcher.forward(request, response);
                return;
            }

            String imageFileName = photo.getImageFileName();
            System.out.println("File Name: "+ imageFileName);

            String contentType = this.getServletContext().getMimeType(imageFileName);
            System.out.println("Content Type: "+ contentType);

            response.setHeader("Content-Type", contentType);

            response.setHeader("Content-Length", String.valueOf(photo.getImageData().length));

            response.setHeader("Content-Disposition", "inline; filename=\"" + photo.getImageFileName() + "\"");

            // Write image data to Response.
            response.getOutputStream().write(photo.getImageData());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
