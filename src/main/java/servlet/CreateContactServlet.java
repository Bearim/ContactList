package servlet;

import dao.*;
import domain.Contact;
import org.apache.log4j.Logger;
import utils.JdbcUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;

import static utils.DomainUtils.*;

@WebServlet(urlPatterns = {"/createContact"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class CreateContactServlet extends HttpServlet {
    private final static String CREATE_CONTACT_VIEW = "/WEB-INF/views/createContactView.jsp";
    private final static Logger log = Logger.getLogger(CreateContactServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(CREATE_CONTACT_VIEW);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        PhoneDAO phoneDAO = new PhoneDAO(connection);
        AdressDAO adressDAO = new AdressDAO(connection);
        DocumentDAO documentDAO = new DocumentDAO(connection);
        PhotoDAO photoDAO = new PhotoDAO(connection);

        Contact contact = createContact(request);

        try {
            contactDAO.insertContact(contact);
            if (contact.getList() != null) {
                phoneDAO.groupInsertPhone(contact.getList());
            }
            if (contact.getAdress()!=null){
                adressDAO.insertAdress(contact.getAdress());
            }
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            if (filePart!=null){
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                photoDAO.setImageInTable(fileContent, fileName);
            }
            if (request.getParameterValues("documents")!=null){
                documentDAO.writeArrayToDB(request.getParameterValues("documents"), request.getParameterValues("documentPath"), request.getParameterValues("comms"));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/contactList");

    }

}
