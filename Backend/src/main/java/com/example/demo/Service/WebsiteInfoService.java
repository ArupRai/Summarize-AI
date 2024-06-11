package com.example.demo.service;

import org.springframework.stereotype.Service;
import scala.collection.JavaConverters;
import scala.collection.Seq;
import scala.collection.immutable.List;
import org.example.ScalaLibrary;

@Service
public class WebsiteInfoService {

    private List<String> history = List.empty();

    public String summarize(String url) {
        // Assuming ScalaLibrary is your Scala class
        ScalaLibrary scalaLibrary = new ScalaLibrary();
        String summary = scalaLibrary.summarize(url);
        history = history.append(url);
        return summary;
    }

    public java.util.List<String> getHistory() {
        Seq<String> historySeq = history;
        return JavaConverters.seqAsJavaList(historySeq);
    }
}
