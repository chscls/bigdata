package com.ch.spark
import scala.util.control.Breaks._
import org.apache.spark.{SparkConf, SparkContext}

object MiddleNum {
  def main(args: Array[String]) {
    val inputFile = "file:///D:/num.txt"


    System.setProperty("hadoop.home.dir", "D:/hadoop-3.0.3")
    val conf = new SparkConf().setAppName("MiddleNum").setMaster("local")
    //
    val sc = new SparkContext(conf)
    val words = sc.textFile(inputFile).flatMap(x => x.split(' ')).map(x => x.toInt)
    val number = words.map(word =>(word/4,word)).sortByKey()
    val pariCount = words.map(word => (word/4,1)).reduceByKey(_+_).sortByKey()
    val count = words.count().toInt
    var mid =0
    if(count%2 != 0)
    {
      mid = count/2+1
    }else
    {
      mid = count/2
    }

    var temp =0
    var temp1= 0
    var index = 0
    val tongNumber = pariCount.count().toInt

    var foundIt = false
    for(i <- 0 to tongNumber-1 if !foundIt)
    {
      temp = temp + pariCount.collectAsMap()(i)
      temp1 = temp - pariCount.collectAsMap()(i)
      if(temp >= mid)
      {
        index = i
        foundIt = true
      }
    }
    val tonginneroffset = mid - temp1

    val median = number.filter(_._1==index).takeOrdered(tonginneroffset)
    sc.setLogLevel("ERROR")
    println(median(tonginneroffset-1)._2)
    sc.stop()

  }

}
