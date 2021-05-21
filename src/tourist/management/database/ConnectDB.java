package tourist.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final ConnectDB instance = new ConnectDB();
    public static Connection connection = null;

    public static ConnectDB getInstance() {
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlserver://127.0.0.1:1433;databasename=QLDuLich";
        String user = "sa";
        String password = "sapassword";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
