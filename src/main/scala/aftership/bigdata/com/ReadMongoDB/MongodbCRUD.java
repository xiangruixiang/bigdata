package aftership.bigdata.com.ReadMongoDB;

import com.alibaba.fastjson.JSON;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.conversions.Bson;
import org.joda.time.format.ISODateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


public class MongodbCRUD {

    static final String GCPprojectId = "aftership-team-data";
    static final String GCPtopic = "realtime";

    public static void main( String args[] ){

        String searchTime;

        try{
            //connect to mongodb  104.196.146.203
            MongoClient mongoClient = new MongoClient( "104.196.146.203" , 27017 );

            //connect to databases
            MongoDatabase mongoDatabase = mongoClient.getDatabase("aftership_archived");
           // System.out.println("Connect to database successfully");

            //choose tables
            MongoCollection<Document> collection = mongoDatabase.getCollection("trackings_2018_03");
            // System.out.println("choose collection successfully ");

            searchTime = "2018-03-02 01:32:21";  //init date
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//set date format
            SimpleDateFormat timer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//set date format
            Date date =df.parse(searchTime);   //convert to date

            Map<String,Object> mapMessage = new HashMap<String,Object>();
            String sourceTimeBegin="";
            String sourceTimeEnd="";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'");
            BasicDBObject query = new BasicDBObject();

            while (true){

                System.out.println("begin time is :" + timer.format(new Date()));

                date.setTime(date.getTime() + 1000);
                searchTime = df.format(date);

                sourceTimeBegin = searchTime.toString() + ".000Z";
                sourceTimeEnd = searchTime.toString() + ".999Z";

                System.out.println("Search time is:" + sourceTimeBegin);

                // add query conditions
              /*  Pattern pattern = Pattern.compile("^.*" + searchTime + ".*$", Pattern.CASE_INSENSITIVE);
                BasicDBObject query = new BasicDBObject();
                query.put("updated_at", pattern);  2018-03-31 00:03:34.360Z  2018-03-31 02:00:00.000Z
*/

                Date startDate = sdf.parse(sourceTimeBegin);
                Date endDate = sdf.parse(sourceTimeEnd);

                query.put("updated_at", BasicDBObjectBuilder.start("$gte", startDate).add("$lt",endDate).get());


                //execute query
                FindIterable<Document> findIterable = collection.find(query
                )                  //search condition
                .projection(fields(include("tracking_number", "user_id", "updated_at","created_at"
                        , "subtag", "tag"
                                ),
                                excludeId()));

                MongoCursor<Document> mongoCursor = findIterable.iterator();

                //loop output data
                while(mongoCursor.hasNext()){
                    Document json = mongoCursor.next();
                    mapMessage.put("tracking_number", json.getString("tracking_number"));
                    mapMessage.put("user_id", json.get("user_id").toString());
                    mapMessage.put("updated_at", json.get("updated_at").toString());
                    mapMessage.put("created_at", json.get("created_at").toString());
                    mapMessage.put("subtag", json.get("subtag").toString());
                    mapMessage.put("tag", json.get("tag").toString());
                    mapMessage.put("insert_time",System.currentTimeMillis()/1000);

                    String jsonStr = JSON.toJSONString(mapMessage);
                    mapMessage.clear();

                    System.out.println("输出的结果是：" + jsonStr);

                    GoogleOperatoin.publishMessages(jsonStr, GCPprojectId, GCPtopic);

                }

                query.clear();
                System.out.println("end time is :" + timer.format(new Date()));

            }
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


}
