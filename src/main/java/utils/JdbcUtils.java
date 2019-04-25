package utils;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;

public class JdbcUtils {
    private static BasicDataSource dataSource;
    private static Connection connection;

    public static BasicDataSource createDataSourse() {
        if (dataSource == null)
        {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/ContactList?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
            ds.setUsername("root");
            ds.setPassword("qwerty");

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
                BasicDataSource dataSource = createDataSourse();
                connection = dataSource.getConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }

}
