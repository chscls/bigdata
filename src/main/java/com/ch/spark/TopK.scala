package com.ch.spark

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object TopK {
  def main(args: Array[String]) {
    val inputFile =  "file:///D:/word.txt"


    val xx = 1 to 10 by 2
    println(xx)

    System.setProperty("hadoop.home.dir", "D:/hadoop-3.0.3")
    val conf = new SparkConf().setAppName("TopK").setMaster("local")//.setJars(List("C:/Users/ch/eclipse-workspace/bigdata/out/artifacts/bigdata_jar/bigdata.jar"))
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)


    val convert = wordCount.map {
      case (key, value) => (value, key)
    }.sortByKey(false, 4)
    convert.top(6).foreach(a => System.out.println(a))
    sc.stop()
  }

}