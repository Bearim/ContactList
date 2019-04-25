package servlet;

import dao.ContactDAO;
import org.apache.log4j.Logger;
import utils.JdbcUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteContact"})
public class DeleteContactServlet extends HttpServlet {
    private final static String CREATE_CONTACT_VIEW = "/WEB-INF/views/deleteContactErrorView.jsp";
    private final static Logger log = Logger.getLogger(DeleteContactServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);

        int id = Integer.parseInt(request.getParameter("id"));

        String errorString = null;

        try {
            contactDAO.deleteContact(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            errorString = e.getMessage();
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(CREATE_CONTACT_VIEW);
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/contactList");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
