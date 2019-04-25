package servlet;

import com.sun.xml.internal.bind.v2.model.core.ID;
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
import java.util.ArrayList;
import java.util.List;

import static utils.EmailUtils.*;

@WebServlet(name = "sendEmailServlet", urlPatterns = {"/sendEmail"})
public class SendEmailServlet extends HttpServlet {
    private final static String SEND_EMAIL_VIEW = "/WEB-INF/views/sendEmailView.jsp";
    private final static Logger log = Logger.getLogger(SendEmailServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = JdbcUtils.getConnection();
        ContactDAO contactDAO = new ContactDAO(connection);
        String[] to = request.getParameterValues("check");
        if(to==null){
            response.sendRedirect(request.getContextPath()+"/contactList");
            return;
        }
        List<String> list = new ArrayList<String>();
        for(String s : to) {
            try {
                if (!contactDAO.findContactById(Integer.parseInt(s)).geteMail().equals("")) {
                    list.add(contactDAO.findContactById(Integer.parseInt(s)).geteMail());
                }
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }

        if(list.size()==0){
            response.sendRedirect(request.getContextPath()+"/contactList");
            return;
        }

        request.setAttribute("IDs", to);
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher(SEND_EMAIL_VIEW);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] to = request.getParameterValues("to");
        int[] arr = makeIntArr((String[]) request.getSession().getAttribute("IDs"));
        String select = request.getParameter("select");

        String subject = request.getParameter("subject");
        String messageText = request.getParameter("messageText");
        String teplateText = null;

        String errorString = null;
        int i = 0;
        for(String s : to) {
            try {
                if(select.length()>=2){
                teplateText = renderTemplate(messageText, new ContactDAO(JdbcUtils.getConnection()).findContactById(arr[i]).getName());
                }
                i++;
                sendEmail(s, subject, teplateText);
            } catch (Exception e) {
                log.error(e.getMessage());
                errorString = e.getMessage();
            }
        }

        request.setAttribute("errorString", errorString);

        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher(SEND_EMAIL_VIEW);
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/contactList");
        }

    }
}
