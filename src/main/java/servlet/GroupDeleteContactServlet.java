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

@WebServlet(urlPatterns = {"/newDelete"})
public class GroupDeleteContactServlet extends HttpServlet {
    private final static String DELETE_CONTACT_ERROR_VIEW = "/WEB-INF/views/deleteContactErrorView.jsp";
    private final static Logger log = Logger.getLogger(GroupDeleteContactServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);

        String[] list = request.getParameterValues("check");

        String errorString = null;
        if (list!=null) {
            for (String s : list) {
                try {
                    contactDAO.deleteContact(Integer.parseInt(s));
                } catch (Exception e) {
                    log.error(e.getMessage());
                    errorString = e.getMessage();
                }
            }
        }

        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(DELETE_CONTACT_ERROR_VIEW);
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
