package rsa;

import java.text.DecimalFormat;
import java.time.Duration;

public class Status {
    private final static DecimalFormat formatter = new DecimalFormat("0,000,000,000");
    private Duration duration;
    private boolean success;
    private long counter;

    public Status(long counter, Duration duration, boolean success) {
        this.counter = counter;
        this.duration = duration;
        this.success = success;
    }
    public static String format(long num) {
        return formatter.format(num).replaceAll("\\G(0|,)", " ");
    }
    @Override
    public String toString() {
        long s = this.duration.toMillis()/1000;
        return format(this.counter) + " loops\t" +
                String.format("%d minuts %d.%d seconds\t",
                        s/60, s%60, this.duration.toMillis()%1000
                ) +  ((this.success)? "Succeed!": "d = n Failed!");
    }

    public static String avrageToString(Status [] statuses) {
        Duration avg = statuses[0].duration;
        for(int i = 1; i < statuses.length; i++) {
            avg = statuses[0].duration.plus(statuses[i].duration);
        }
        avg = avg.dividedBy(statuses.length);
        long sum = 0;
        for(Status s: statuses) {
            sum += s.counter;
        }
        long s = avg.toMillis()/1000;
        return format(sum/statuses.length) + " loops\t" +
                String.format("%d minuts %d.%d seconds\t",
                        s/60, s%60, avg.toMillis()%1000
                );
    }

}
