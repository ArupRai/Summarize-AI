package com.example.demo.Service;

import org.example.ScalaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteInfoService {

    private final ScalaService scalaService;

    public WebsiteInfoService(ScalaService scalaService) {
        this.scalaService = scalaService;
    }

    public String summarize(String url) {

        return scalaService.summarize(url);
    }

    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        scalaService.getHistory().foreach(history::add);
        return history;
    }
}
