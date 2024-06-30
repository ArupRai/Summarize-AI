package com.example.demo.Service;

import org.example.ScalaService;
import org.example.HistoryEntry; // Import the HistoryEntry case class
import org.springframework.stereotype.Service;

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

    public List<HistoryEntry> getHistory() {
        return scalaService.getHistory();
    }
}
