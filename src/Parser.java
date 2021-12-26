import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class Parser {
    private final String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public Remittances parse() {
        Remittances remittances = new Remittances();

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
            lines.remove(0);
            lines.stream()
                    .map(line -> line.replace("\"", "").split(",", -1))
                    .map(this::createRemittance)
                    .forEach(remittances::add);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

        return remittances;
    }

    private Remittance createRemittance(String[] fragments) {
        DateFormat format = new SimpleDateFormat("yyyy.MM");
        format.setTimeZone(TimeZone.getTimeZone("YEKT"));

        try {
            return new Remittance(
                    fragments[0],
                    (format.parse(fragments[1])),
                    fragments[2],
                    fragments[3],
                    fragments[4],
                    fragments[5],
                    Integer.parseInt(fragments[6]),
                    fragments[7],
                    fragments[8],
                    fragments[9],
                    fragments[10],
                    fragments[11],
                    fragments[12],
                    fragments[13]);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}