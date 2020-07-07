package sir.server.connection;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import sir.client.Credentials;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionPool {

    public static Connection connection;
    private static final Map<String, Connection> pool = new HashMap<>();




    public static void add(Connection conn, Tab tab) {
        pool.put(tab.getId(), conn);
        connection = conn;
    }


    public static void switchConnection(Tab tab) {
        if (connection != null) {
            String server = tab.getId();
            if (pool.containsKey(server)) {
                connection = pool.get(server);
            }
        }
    }

    public static void removeFromPool (Tab tab) {
        pool.remove(tab.getId());
    }


    public static void getInfo(Label name, Label host, Label user) {
            try {
                DatabaseMetaData dbm = connection.getMetaData();
                name.setText(dbm.getDatabaseProductName() + ":" + Credentials.getServerName());
                user.setText(dbm.getUserName());
                host.setText(Credentials.getIp() + ":" + Credentials.getPort());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }




    }


