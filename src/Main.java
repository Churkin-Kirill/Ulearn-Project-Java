import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Remittance> remittances = new Parser("Переводы.csv").parse().getRemittances();

        for (Remittance remittance : remittances) {
            System.out.println(remittance.toString());
        }

        DataBase dataBase = new DataBase("Remittances");
        dataBase.createDB();

        createTableRemittances(dataBase);

        writeRemittancesToDB(dataBase, remittances);

        Queries queries = new Queries("Remittances");

        Map<String, Double> dataForHistogram = queries.executeQueryOne();
        new Histogram().drawHistogram(dataForHistogram);

        queries.executeQueryTwo();
        queries.executeQueryThree();
    }

    private static void createTableRemittances(DataBase dataBase) {
        String[] attributesInfo = {
                "Series_reference VARCHAR",
                "Period VARCHAR",
                "Data_value VARCHAR",
                "Suppressed VARCHAR",
                "STATUS VARCHAR",
                "UNITS VARCHAR",
                "Magnitude VARCHAR",
                "Subject VARCHAR",
                "Group_ VARCHAR",
                "Series_title_1 VARCHAR",
                "Series_title_2 VARCHAR",
                "Series_title_3 VARCHAR",
                "Series_title_4 VARCHAR",
                "Series_title_5 VARCHAR"};
        dataBase.createTable("Remittances", attributesInfo);
    }

    private static void writeRemittancesToDB(DataBase dataBase, ArrayList<Remittance> remittances) {
        for (Remittance remittance : remittances) {
            List<String> values = Arrays.stream(remittance.getAllValues())
                    .map(value -> String.format("'%s'", value))
                    .collect(Collectors.toList());
            dataBase.insertToTable("Remittances", values);
        }
    }
}