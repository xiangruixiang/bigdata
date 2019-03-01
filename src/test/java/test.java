import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class test {

    public static void main( String args[] ) throws ParseException {


 /*       String dateStr = "Fri Mar 02 00:00:08 CST 2018";

        SimpleDateFormat USDateTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

        Date date = USDateTime.parse(dateStr);

        String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(formatStr2);
*/

        System.out.println(Long.valueOf(System.currentTimeMillis()));



    }
}
