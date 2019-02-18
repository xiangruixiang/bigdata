package aftership.bigdata.com.SparkSql

import java.io.File
import aftership.bigdata.com.function.functions
import org.apache.spark.sql.SparkSession
import java.io.FileInputStream
import java.util.{Date, Properties}

object Tracking {

  val call = new functions()

  def main(args: Array[String]): Unit = {

    if (args.length == 0) {
      println("Please input a date parameter,example: 2018-03-01")
      System.exit(1)
    }

    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    println("warehouseLocation location is： "  + warehouseLocation)

    println("custom log: ********** initialize spark ************")
    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()


    // read properties file
    println("custom log: ********** read sql properties file ************")
    val sqlProperties = new Properties()
    sqlProperties.load(new FileInputStream("///home/rx.xiang/properties/sql.properties"))


    println("custom log: ********** crate a temp day with only one day values ************")
    val day_values_table_temp = sqlProperties.getProperty("day_values_table")

    val day_values_table = day_values_table_temp.replace("Target_Date_parameters", "'" + args(0) + "'")

    val tracking_day_table = spark.sql(day_values_table)

    //create a view
    tracking_day_table.createOrReplaceTempView("tracking_day_table_view")

    //add the view to memory
    spark.catalog.cacheTable("tracking_day_table_view")

    val tracking_delivery_status = sqlProperties.getProperty("tracking_delivery_status")

    val tracking_delivery_substatus = sqlProperties.getProperty("tracking_delivery_substatus")

    val tracking_transit_day = sqlProperties.getProperty("tracking_transit_day")


    //execute sql
    println("custom log: ********** execute sql is :" + tracking_delivery_status)
    val tracking_delivery_status_result = spark.sql(tracking_delivery_status)

    //load GreenPlum config
    val tracking_delivery_status_options = Map(
      "url" -> "jdbc:postgresql://aftership-006.datenode:5432/test", // JDBC url
      "user" -> "gpadmin",
      "password" -> "gpadmin",
      "driver" -> "org.postgresql.Driver",// JDBC driver
      "dbtable" -> "tracking_delivery_status") // Table name

    //write data to GreenPlum
    println("custom log: ********** Insert data to tables :" + tracking_delivery_status)
    tracking_delivery_status_result.write.mode("append").format("jdbc").options(tracking_delivery_status_options).save



    //execute sql
    println("custom log: ********** execute sql is :" + tracking_delivery_substatus)
    val tracking_delivery_substatus_result = spark.sql(tracking_delivery_substatus)

    //load GreenPlum config
    val tracking_delivery_substatus_options = Map(
      "url" -> "jdbc:postgresql://aftership-006.datenode:5432/test", // JDBC url
      "user" -> "gpadmin",
      "password" -> "gpadmin",
      "driver" -> "org.postgresql.Driver",// JDBC driver
      "dbtable" -> "tracking_delivery_substatus") // Table name

    println("custom log: ********** Insert data to tables :" + tracking_delivery_substatus)
    tracking_delivery_substatus_result.write.mode("append").format("jdbc").options(tracking_delivery_substatus_options).save


    //execute sql
    println("custom log: ********** execute sql is :" + tracking_transit_day)
    val tracking_transit_day_result = spark.sql(tracking_transit_day)


    //load GreenPlum config
    val tracking_transit_day_options = Map(
      "url" -> "jdbc:postgresql://aftership-006.datenode:5432/test", // JDBC url
      "user" -> "gpadmin",
      "password" -> "gpadmin",
      "driver" -> "org.postgresql.Driver",// JDBC driver
      "dbtable" -> "tracking_transit_day") // Table name

    println("custom log: ********** Insert data to tables :" + tracking_transit_day)
    tracking_transit_day_result.write.mode("append").format("jdbc").options(tracking_transit_day_options).save

    //clear memory
    spark.catalog.uncacheTable("tracking_day_table_view")

    // save to HDFS
    // result.toJavaRDD.saveAsTextFile("hdfs://aftership-001.namenode:8020/user/rx.xiang/program/tracking_delivery_status" + new SimpleDateFormat("yyyy-MM-dd").format(new Date))

    spark.stop()

  }

}