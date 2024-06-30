package com.example.demo.Controller;

import com.example.demo.Service.WebsiteInfoService;
import com.example.demo.dto.SummarizeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.example.HistoryEntry; // Import the HistoryEntry case class

@RestController
@RequestMapping("/api")
public class WebsiteController {
    private static final Logger logger = LoggerFactory.getLogger(WebsiteController.class);
    private final WebsiteInfoService websiteInfoService;

    public WebsiteController(WebsiteInfoService websiteInfoService) {
        this.websiteInfoService = websiteInfoService;
    }

    @PostMapping("/summarize")
    public ResponseEntity<String> summarize(@RequestBody SummarizeRequest request) {
        logger.info("Received request to summarize text");
        String summary = websiteInfoService.summarize(request.getText());
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryEntry>> getHistory() {
        List<HistoryEntry> history = websiteInfoService.getHistory();
        return ResponseEntity.ok(history);
    }
}
