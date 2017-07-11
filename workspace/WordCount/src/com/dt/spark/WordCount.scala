package com.dt.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2016/3/29.
  */
object WordCount {
  def main(args: Array[String]){ // args变量名，Array类型，String泛型

    /**
      * 第1步：创建spark的配置对象sparkConf，设置spark程序的运行时配置信息
      * 	例如通过setMaster来设置程序要连接的spark集群的Master的URL
      * 	local本地模式无需集群v
      */
    val conf = new SparkConf();
    conf.setAppName("First Spark IN IDEA") // 设置应用程序名称，在程序运行的监控界面可看见
//    conf.setMaster("local")
    conf.setMaster("spark:autoMaster:7077")

    /**
      * 第2步：创建SparkContext对象
      * 是所有功能得入口，无论是java、scala、Python、R等都需要一个SparkContext
      * 核心作用：初始化spark应用程序运行时所需要的核心组件、包括DAGScheduler（高层调度器）、TaskScheduler（调度模式）、SchedulerBacend（）
      * 同时负责spark程序往master注册程序等
      */
    val sc = new SparkContext(conf)

    /**
      * 第3步：RDD，根据具体数据来源（HDFS、HBase、Local、FS、DB、S3等）通过SparkContext来创建RDD
      * RDD创建的三种方式：根据数据来源创建、根据scala集合、有其他的RDD操作
      * 数据会被RDD划分为一系列的Partitions，分配到每个Partition的数据属于一个Task的处理范畴
      */
//    val lines = sc.textFile("F://hadoop//spark//resources//spark-1.6.0-bin-hadoop2.6//CHANGES.txt", 2) // 2参最小的并行度(分片Partition)，val lines: RDD[String]表示类型推断
    val lines = sc.textFile("hdfs://autoMaster:9000/data_tmp") // 读取hdfs上的文件并切分成不同的分片Partition


    /**
      * 第4步：对初始的RDD进行Transformation级别的处理，例如map、filter等高阶函数的编程，来进行具体数据计算
      * 4.1：拆分合并，将每行字符串拆分成单个单词
      */
    val words = lines.flatMap { line => line.split(" ") } // 对每行字符串进行拆分，并将所有结果合并为一个大的单词集合

    /** 4.2:计数，在拆分基础上对每个单词实例计数为1，也就是word => (word,1) */
    val pairs = words.map { word => (word,1) } // map对每个单词进行操作，操作变成一个tiop（后面的括号表明它是一个tiop），tiop里面两个参数，一个单词本身，一个就是计数为1

    /** 4.3:统计，在每个单词实例计数为1的基础上，统计其总次数 */
//    val wordCounts = pairs.reduceByKey(_+_) // 对相同的key进行value的累加（包括local和Reducer级别同时Reduce）  //  普通统计单词次数
    val wordCounts = pairs.reduceByKey(_+_).map(pair => (pair._2,pair._1)).sortByKey(false).map(pair => (pair._2,pair._1)) // 对相同的key进行value的累加（包括local和Reducer级别同时Reduce）  //  广告点击排名（排序），首先将key和value互换：.map(pair => (pair._2,pair._1))

//    wordCounts.foreach(wordNumberPair => println(wordNumberPair._1 + " : " + wordNumberPair._2)) // 打印
    wordCounts.collect.foreach(wordNumberPair => println(wordNumberPair._1 + " : " + wordNumberPair._2)) // 打印  //  collect是把所有运行的结果都手机到driver上，它本身是一个数组

    sc.stop()

  }
}
