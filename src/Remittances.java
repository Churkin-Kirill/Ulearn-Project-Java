import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Remittances {
    private final ArrayList<Remittance> remittances = new ArrayList<>();

    public ArrayList<Remittance> getRemittances() {
        return remittances;
    }

    public void add(Remittance remittance) {
        remittances.add(remittance);
    }
}

class Remittance {
    private final String seriesReference;
    private final Date period;
    private final String dataValue;
    private final String suppressed;
    private final String status;
    private final String units;
    private final int magnitude;
    private final String subject;
    private final String group;
    private final String seriesTitle1;
    private final String seriesTitle2;
    private final String seriesTitle3;
    private final String seriesTitle4;
    private final String seriesTitle5;

    public Remittance (String seriesReference,
                       Date period,
                       String dataValue,
                       String suppressed,
                       String status,
                       String units,
                       int magnitude,
                       String subject,
                       String group,
                       String seriesTitle1,
                       String seriesTitle2,
                       String seriesTitle3,
                       String seriesTitle4,
                       String seriesTitle5) {
        this.seriesReference = seriesReference;
        this.period = period;
        this.dataValue = dataValue;
        this.suppressed = suppressed;
        this.status = status;
        this.units = units;
        this.magnitude = magnitude;
        this.subject = subject;
        this.group = group;
        this.seriesTitle1 = seriesTitle1;
        this.seriesTitle2 = seriesTitle2;
        this.seriesTitle3 = seriesTitle3;
        this.seriesTitle4 = seriesTitle4;
        this.seriesTitle5 = seriesTitle5;
    }

    public String getSeriesReference() {
        return seriesReference;
    }

    public Date getPeriod() {
        return period;
    }

    public String getDataValue() {
        return dataValue;
    }

    public String getSuppressed() {
        return suppressed;
    }

    public String getStatus() {
        return status;
    }

    public String getUnits() {
        return units;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public String getSubject() {
        return subject;
    }

    public String getGroup() {
        return group;
    }

    public String getSeriesTitle1() {
        return seriesTitle1;
    }

    public String getSeriesTitle2() {
        return seriesTitle2;
    }

    public String getSeriesTitle3() {
        return seriesTitle3;
    }

    public String getSeriesTitle4() {
        return seriesTitle4;
    }

    public String getSeriesTitle5() {
        return seriesTitle5;
    }

    public String[] getAllValues() {
        return new String[] {
                seriesReference,
                (new SimpleDateFormat("yyyy.MM")).format(period),
                dataValue,
                suppressed,
                status,
                units,
                String.valueOf(magnitude),
                subject,
                group,
                seriesTitle1,
                seriesTitle2,
                seriesTitle3,
                seriesTitle4,
                seriesTitle5
        };
    }

    public String toString() {
        return String.join(" | ", getAllValues());
    }
}