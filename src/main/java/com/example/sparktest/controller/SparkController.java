package com.example.sparktest.controller;

import com.example.sparktest.jobs.WordCountJob;
import com.example.sparktest.model.WordCount;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/spark")
public class SparkController {

    private final JavaSparkContext sparkContext;

    public SparkController(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    @GetMapping("/word-count")
    public List<WordCount> wordCount() {
        List<String> text = Arrays.asList(
            "Hello Spark",
            "Hello World",
            "Hello Spring",
            "Hello Spark"
        );

        return WordCountJob.run(sparkContext, text);
    }
} 