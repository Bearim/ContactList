package servlet;

import dao.*;
import domain.*;
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
import java.sql.SQLException;
import java.util.List;

import static utils.DomainUtils.createAdress;
import static utils.DomainUtils.createContact;

@WebServlet(urlPatterns = {"/editContact"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class EditContactServlet extends HttpServlet {
    private final static String EDIT_CONTACT_VIEW = "/WEB-INF/views/editContactView.jsp";
    private final static Logger log = Logger.getLogger(EditContactServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        PhoneDAO phoneDAO = new PhoneDAO(connection);
        AdressDAO adressDAO = new AdressDAO(connection);
        DocumentDAO documentDAO = new DocumentDAO(connection);

        int id = Integer.parseInt(request.getParameter("id"));

        Contact contact = null;
        List<Phone> list = null;
        Adress adress = null;
        List<Document> listDocument = null;

        String errorString = null;

        try {
            contact = contactDAO.findContactById(id);
            list = phoneDAO.queryPhone(id);
            adress = adressDAO.findAdress(id);
            listDocument = documentDAO.querryDocuments(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            errorString = e.getMessage();
        }

        if (errorString != null && contact == null) {
            response.sendRedirect(request.getServletPath() + "/contactList");
            return;
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("contact", contact);
        request.setAttribute("phoneList", list);
        request.setAttribute("adress", adress);
        request.setAttribute("documentList", listDocument);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(EDIT_CONTACT_VIEW);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        DocumentDAO documentDAO = new DocumentDAO(connection);
        PhotoDAO photoDAO = new PhotoDAO(connection);
        AdressDAO adressDAO = new AdressDAO(connection);

        Contact contact = createContact(request, Integer.parseInt(request.getParameter("id")));

        String errorString = null;

        try {
            contactDAO.updateContact(contact);
            adressDAO.updateAdress(contact.getId(), createAdress(request));
            if (request.getParameterValues("documents")!=null){
                documentDAO.updateDocuments(request.getParameterValues("documents"), request.getParameterValues("documentPath"), request.getParameterValues("comments"), contact.getId());
            }
            Part filePart = request.getPart("newPhoto"); // Retrieves <input type="file" name="file">
            if (filePart!=null){
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                photoDAO.updatePhoto(fileContent, fileName, contact.getId());
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);
        request.setAttribute("contact", contact);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(EDIT_CONTACT_VIEW);
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/contactList");
        }
    }
}
