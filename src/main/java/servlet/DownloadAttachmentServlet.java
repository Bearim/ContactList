package servlet;

import dao.DocumentDAO;
import domain.Document;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import static utils.JdbcUtils.getConnection;

@WebServlet(urlPatterns = {"/downloadAttachment"})
public class DownloadAttachmentServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(DownloadAttachmentServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = getConnection();
        DocumentDAO documentDAO = new DocumentDAO(connection);
        int id = Integer.parseInt(request.getParameter("id"));
        String fileName = request.getParameter("fileName");
        String errorString = null;
        Document document;

        try {
            document = documentDAO.getDocumentFromBD(id, fileName);

            if(document==null){
                errorString += " Attachment is null";
            }

            String contentType = this.getServletContext().getMimeType(fileName);
            response.setHeader("Content-Type", contentType);

            response.setHeader("Content-Length", String.valueOf(document.getFileData().length()));

            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

            InputStream is = document.getFileData().getBinaryStream();
            byte[] bytes = new byte[1024];
            int bytesRead;

            while ((bytesRead = is.read(bytes)) != -1) {
                response.getOutputStream().write(bytes, 0, bytesRead);
            }
            is.close();
        }catch (Exception e){
            log.error(e.getMessage());
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);

        response.sendRedirect(request.getContextPath() + "/contactList?"+id);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }
}
