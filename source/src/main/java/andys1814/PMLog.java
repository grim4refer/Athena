package andys1814;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Andys1814
 */
public final class PMLog {

    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("data/pms.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void submit(String senderUsername, String recipientUsername, String message) throws IOException {
        String date = dateFormat.format(Calendar.getInstance(TimeZone.getTimeZone("GMT-8:00")).getTime());
        String line = "[sender=" + senderUsername + ", recipient=" + recipientUsername + ", message=" + message + ", date=" + date + "]";
        writer.println(line);
    }

}
