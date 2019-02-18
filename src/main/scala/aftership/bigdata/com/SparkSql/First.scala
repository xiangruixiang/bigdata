package aftership.bigdata.com.SparkSql

import aftership.bigdata.com.function.functions
import java.io.File
import org.apache.spark.sql.{Row, SaveMode, SparkSession}

object First {

  val call = new functions()

  def main(args: Array[String]): Unit = {

    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    println("warehouseLocation location is： "  + warehouseLocation)

    println("custom log: ********** read database properties ************")


    println("custom log: ********** initialize spark ************")
    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()


    // read table
    println("custom log: ********** read necessary tables ************")
   // val veiw_table = spark.sql("select * from test.tracking")

    //change table as view
    println("custom log: ********** change tables as views ************")

    val sqlTest = "select id as user_id, name as tag_status from default.test"
    val result = spark.sql(sqlTest)

    result.show()


/*
    result.write.mode("append")
      .format("jdbc")
      .option("url", "jdbc:postgresql://35.237.105.3:5432")
      .option("dbtable", "test.tracking")
      .option("user", "gpadmin")
      .option("password", "gpadmin")
      .option("driver","org.postgresql.Driver")
      .save()
      */

    val options = Map(
      "url" -> "jdbc:postgresql://aftership-006.datenode:5432/test", // JDBC url
      "user" -> "gpadmin",
      "password" -> "gpadmin",
      "driver" -> "org.postgresql.Driver",// JDBC driver
      "dbtable" -> "tracking_delivery_status") // Table name

    result.write.mode("append").format("jdbc").options(options).save

    spark.stop()

  }

}