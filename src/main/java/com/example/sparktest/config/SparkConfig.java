package com.example.sparktest.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("SparkTest")
                .setMaster("local[*]")
                .set("spark.ui.enabled", "false")
                .set("spark.hadoop.fs.defaultFS", "file:///")
                .set("spark.hadoop.fs.file.impl", "org.apache.hadoop.fs.LocalFileSystem")
                .set("spark.hadoop.fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem")
                .set("spark.driver.memory", "2g")
                .set("spark.executor.memory", "1g")
                .set("spark.memory.fraction", "0.8")
                .set("spark.memory.storageFraction", "0.3")
                .set("spark.executor.cores", "2")
                .set("spark.executor.instances", "2")
                .set("spark.network.timeout", "600s")
                .set("spark.executor.heartbeatInterval", "30s")
                .set("spark.dynamicAllocation.enabled", "false")
                .set("spark.shuffle.service.enabled", "false");
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .sparkContext(javaSparkContext().sc())
                .appName("SparkTest")
                .config("spark.sql.shuffle.partitions", "2")
                .config("spark.default.parallelism", "2")
                .getOrCreate();
    }
} 
