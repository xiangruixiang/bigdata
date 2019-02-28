import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        //System.out.println(System.currentTimeMillis());

        List<String> dataList = new ArrayList<>();

        dataList.add("{\"updated_at\":\"Fri Mar 02 01:56:45 CST 2018\",\"user_id\":\"594d5e8f05bd46c50b24274f\",\"subtag\":\"Delivered_001\",\"tracking_number\":\"9361289675090543469639\",\"created_at\":\"Fri Mar 02 01:56:44 CST 2018\",\"insert_time\":1551232808,\"tag\":\"Delivered\"}");
        dataList.add("1242343242");

        for (String message: dataList){
            System.out.println(message);
        }
        dataList.clear();

        System.out.println("clear");
        dataList.add("123");
        for (String message: dataList){
            System.out.println(message);
        }



    }
}
