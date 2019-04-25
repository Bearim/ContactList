package utils;

import dao.ContactDAO;
import org.apache.log4j.Logger;

import java.util.TimerTask;

import static utils.EmailUtils.sendBirthDayContacts;
import static utils.JdbcUtils.getConnection;

public class ScheduledTaskUtils extends TimerTask {
    private final static Logger log = Logger.getLogger(ScheduledTaskUtils.class);

    ContactDAO contactDAO = new ContactDAO(getConnection());
    @Override
    public void run() {
        try {
            sendBirthDayContacts(contactDAO.querryBirthDateContacts());
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
