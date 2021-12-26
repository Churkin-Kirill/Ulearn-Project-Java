import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Queries {
    private final String DBName;

    public Queries(String DBName) {
        this.DBName = DBName;
    }

    public Map<String, Double> executeQueryOne() {
        Map<String, Double> dataForHistogram = new HashMap<>();

        String sqlQuery = "" +
                "SELECT Period AS 'Период', ROUND(SUM(Data_value), 1) AS 'Сумма' " +
                "FROM Remittances " +
                "WHERE UNITS = 'Dollars' AND Period LIKE '2020%' AND Data_value != \"\" " +
                "GROUP BY Period;";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s.db", DBName));
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlQuery);

            System.out.println("Период    Сумма");

            while (queryResult.next()) {
                dataForHistogram.put(
                        queryResult.getString("Период"),
                        Double.valueOf(queryResult.getString("Сумма")));

                System.out.printf(
                        "%s   %s%n",
                        queryResult.getString("Период"),
                        queryResult.getString("Сумма"));
            }

            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return dataForHistogram;
    }

    public void executeQueryTwo() {
        String sqlQuery = "" +
                "SELECT Period AS 'Период', " +
                "ROUND(AVG(Data_value), 1) AS 'Средняя сумма перевода', " +
                "COUNT(Data_value) AS 'Количество переводов' " +
                "FROM Remittances " +
                "WHERE UNITS = 'Dollars' AND Data_value != \"\" " +
                "GROUP BY Period;";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s.db", DBName));
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlQuery);

            System.out.println("Период    Средняя сумма перевода   Количество переводов");

            while (queryResult.next()) {
                System.out.printf(
                        "%s   %-10s               %s%n",
                        queryResult.getString("Период"),
                        queryResult.getString("Средняя сумма перевода"),
                        queryResult.getString("Количество переводов"));
            }

            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void executeQueryThree() {
        String sqlQuery = "" +
                "SELECT SUBSTR(Period, 0, 5) AS 'Период', " +
                "MIN(Data_value) AS 'Минимальный перевод', " +
                "MAX(Data_value) AS 'Максимальный перевод' " +
                "FROM Remittances " +
                "WHERE UNITS = 'Dollars' AND Data_value != \"\" AND SUBSTR(Period, 0, 5) IN ('2014', '2016', '2020') " +
                "GROUP BY SUBSTR(Period, 0, 5);";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s.db", DBName));
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(sqlQuery);

            System.out.println("Период   Минимальный перевод   Максимальный перевод");

            while (queryResult.next()) {
                System.out.printf(
                        "%s     %-10s            %s%n",
                        queryResult.getString("Период"),
                        queryResult.getString("Минимальный перевод"),
                        queryResult.getString("Максимальный перевод"));
            }

            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}