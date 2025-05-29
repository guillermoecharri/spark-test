package com.example.sparktest.jobs;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Encoders;
import static org.apache.spark.sql.functions.*;

import com.example.sparktest.model.WordCount;

public class WordCountJob {
    
    public static List<WordCount> run(SparkSession spark, List<String> text) {
        try {
            // First split all words and create a flat list
            List<String> words = new ArrayList<>();
            for (String line : text) {
                words.addAll(Arrays.asList(line.split(" ")));
            }
            
            // Create Dataset of words using Encoders
            Dataset<String> wordsDS = spark.createDataset(words, Encoders.STRING());
            
            // Convert to DataFrame and count words
            Dataset<Row> wordCounts = wordsDS.toDF("word")
                .groupBy("word")
                .agg(count("*").as("count"))
                .orderBy(desc("count"));
            
            // Convert to list of WordCount objects
            List<WordCount> results = new ArrayList<>();
            for (Row row : wordCounts.collectAsList()) {
                results.add(new WordCount(row.getString(0), (int)row.getLong(1)));
            }
            return results;
                
        } catch (Exception e) {
            System.out.println("Error in word count job: " + e.getMessage());
            e.printStackTrace();
            return Arrays.asList(new WordCount("Error", 0));
        }
    }
}
