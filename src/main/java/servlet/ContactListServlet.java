package servlet;

import dao.ContactDAO;
import domain.Contact;
import org.apache.log4j.Logger;
import utils.JdbcUtils;
import utils.ScheduledTaskUtils;

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
import java.util.Timer;

@WebServlet(urlPatterns = {"/contactList"})
public class ContactListServlet extends HttpServlet {
    private final static String CONTACT_LIST_VIEW = "/WEB-INF/views/contactListView.jsp";
    private final static Logger log = Logger.getLogger(ContactListServlet.class);
    private static Timer time;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);

        if(time==null) {
            time = new Timer();
            ScheduledTaskUtils st = new ScheduledTaskUtils();
            time.schedule(st, 0, 86400000);
        }

        String errorString = null;
        List<Contact> list = null;
        try{
            list = contactDAO.queryContact();
        }catch (Exception e){
            log.error(e.getMessage());
            errorString = e.getMessage();
        }
        request.setAttribute("errorSring", errorString);
        request.setAttribute("contactList", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher(CONTACT_LIST_VIEW);
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
