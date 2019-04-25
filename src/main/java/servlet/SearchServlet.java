package servlet;

import dao.interfaceDAO.ContactDAO;
import domain.Contact;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static utils.JdbcUtils.getConnection;

@WebServlet(urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    private final static String CONTACT_LIST_VIEW = "WEB-INF/views/contactListView.jsp";
    private final static Logger log = Logger.getLogger(SearchServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = getConnection();
        ContactDAO contactDAO = new dao.ContactDAO(connection);
        String querry = request.getParameter("search");
        List<Contact> list = null;

        String errorString = null;
        if(querry.equals("")){
            response.sendRedirect(request.getContextPath() + "/contactList");
            return;
        }
        try{
            list = contactDAO.querySearch(querry);
        }catch (Exception e){
            log.error(e.getMessage());
            errorString = e.getMessage();
        }

        request.setAttribute("querry", querry);
        request.setAttribute("errorSring", errorString);
        request.setAttribute("contactList", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher(CONTACT_LIST_VIEW);
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
