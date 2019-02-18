package aftership.bigdata.com.Onboarding;

import org.apache.http.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;



public class SimpleHttpClientDemo {


    public static String CreateTracking(String url, String keyAPI,String param) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        //add header
        httpPost.addHeader("Content-type", "application/json");
        httpPost.setHeader("aftership-api-key", keyAPI);

        // set charaters
        StringEntity se = new StringEntity(param, "UTF-8");
        se.setContentType("text/json");
        se.setContentEncoding(new BasicHeader("Content-type", "application/json"));
        httpPost.setEntity(se);

        // send request
        HttpResponse httpResponse = new DefaultHttpClient().execute(httpPost);
        String resp = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        return resp;
    }



    public static void main(String[] args) throws ParseException, IOException {

        //API endpoint
        String url = "https://api.aftership.com/v4/trackings";

        // API key
        String keyAPI = "75576387-3cc8-4169-a20c-2a3ffc369d7f";

        JSONObject trackingJSON = new JSONObject();
        JSONObject globalJSON = new JSONObject();

        //add parameters
        trackingJSON.put("slug","dhl");
        trackingJSON.put("tracking_number","2164567230");

        JSONArray emailsJSON = new JSONArray();
        emailsJSON.put("rx.xiang@aftership.com");
        emailsJSON.put("xiangruixiang@live.cn");

        trackingJSON.put("emails", emailsJSON);

        globalJSON.put("tracking", trackingJSON);

        // create a tracking
        String response = CreateTracking(url, keyAPI, globalJSON.toString());

        //output result
        System.out.println("Search result is :" + response);

    }


}
