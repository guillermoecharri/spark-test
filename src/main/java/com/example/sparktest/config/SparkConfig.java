package com.example.sparktest.config;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("SparkTest")
                .set("spark.driver.host", "host.docker.internal")
                .set("spark.driver.bindAddress", "0.0.0.0")
                .set("spark.ui.enabled", "false")
                .setMaster("spark://localhost:7077");
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .config(sparkConf())
                .getOrCreate();
    }

} 
