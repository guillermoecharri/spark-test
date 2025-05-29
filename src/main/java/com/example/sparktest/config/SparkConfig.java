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
                // Disable Spark UI, otherwise we will need to include some other dependencies
                // The Spark Master's UI will still be available and that is more useful
                .set("spark.ui.enabled", "false")
                // .set("spark.dynamicAllocation.enabled", "false")
                // .set("spark.executor.memory", "1g")
                // .set("spark.driver.memory", "1g")
                // Configure networking for Docker
                .set("spark.driver.host", "host.docker.internal")
                .set("spark.driver.bindAddress", "0.0.0.0")
                // Add application JAR for worker nodes - using the mounted path in containers
                // TODO: Make this dynamic
                .setJars(new String[]{"C:\\Git\\spark-test\\target\\spark-test-1.0-SNAPSHOT.jar"})
                .setMaster("spark://localhost:7077");
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
                .getOrCreate();
    }
} 
