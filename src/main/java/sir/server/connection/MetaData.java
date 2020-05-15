package sir.server.connection;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaData {

    private static Connection connection;
    private static ActionsCollector actionsCollector;


   public MetaData () {
        connection = ConnectionPool.connection;
        actionsCollector = new ActionsCollector();
    }

    public String getTableName() {
        try {
            ResultSetMetaData resultSetMetaData = Result.resultSet.getMetaData();
            return resultSetMetaData.getTableName(1);
        } catch (SQLException e) {
            actionsCollector.add(e.getMessage());
        }
        return null;
    }


}