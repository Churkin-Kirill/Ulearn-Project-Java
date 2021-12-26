import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class DataBase {
    private final String DBName;

    public DataBase(String DBName) {
        this.DBName = DBName;
    }

    public void createDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s.db", DBName));
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    public void createTable(String tableName, String[] attributesInfo) {
        executeQuery(String.format(
                "CREATE TABLE %s (%s);",
                tableName,
                String.join(", ", attributesInfo)));
    }

    public void insertToTable(String tableName, List<String> values) {
        executeQuery(String.format(
                "INSERT INTO %s VALUES (%s);",
                tableName,
                String.join(", ", values)));
    }

    private void executeQuery(String sqlQuery) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s.db", DBName));
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);
            statement.executeUpdate(sqlQuery);
            statement.close();
            connection.commit();
            connection.close();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
