package com.example.sparktest.jobs;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;

import com.example.sparktest.model.WordCount;

public class WordCountJob {

    public static List<WordCount> run(JavaSparkContext sparkContext, List<String> text) {
        try {
            var rdd = sparkContext.parallelize(text);
            var words = rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
            var wordPairs = words.mapToPair(word -> new scala.Tuple2<>(word, 1));
            var wordCounts = wordPairs.reduceByKey(Integer::sum);
            return wordCounts.collect().stream()
                    .map(tuple -> new WordCount(tuple._1(), tuple._2()))
                    .toList();
        } catch (Exception e)  {
            System.out.println(e.getMessage());
        }
        
        return Arrays.asList(new WordCount("Error", 0));
    }
}
