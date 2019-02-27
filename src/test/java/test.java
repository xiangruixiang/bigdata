import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {

    public static void main( String args[] ) throws ParseException {

        /*

        String dateStr = "Fri Mar 02 00:00:08 CST 2018";

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

//java.util.Date对象
        Date date = (Date) sdf.parse(dateStr);

//2009-09-16
        String formatStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(formatStr);

//2009-09-16 11:26:23
        String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(formatStr2);

        */

        System.out.println(System.currentTimeMillis());


    }
}
