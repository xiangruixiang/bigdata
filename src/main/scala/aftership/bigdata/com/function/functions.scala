package aftership.bigdata.com.function

import java.text.SimpleDateFormat
import java.util.Calendar

class functions {


    //return NOW datetime
    def getNow: String ={
      var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      var cal:Calendar=Calendar.getInstance()

      var getNow=dateFormat.format(cal.getTime())
      return getNow
    }


    //return yesterday date
    def getLastDay: String ={
      var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
      var cal:Calendar=Calendar.getInstance()
      cal.add(Calendar.DATE,-1)
      var yesterday=dateFormat.format(cal.getTime())
      return yesterday
    }

    //return CurrentDay date
    def getCurrentDay: String ={
      var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
      var cal:Calendar=Calendar.getInstance()

      var CurrentDay=dateFormat.format(cal.getTime())
      return CurrentDay
    }



    //return CurrentHour time
    def getCurrentHour: String ={
      var  dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
      var cal:Calendar=Calendar.getInstance()

      var CurrentDay=dateFormat.format(cal.getTime())
      return CurrentDay.substring(11,13)
    }



}
