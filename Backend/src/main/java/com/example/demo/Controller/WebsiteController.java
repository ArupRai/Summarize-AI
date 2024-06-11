package com.example.demo.controller;

import com.example.demo.service.WebsiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebsiteController {

    @Autowired
    private WebsiteInfoService websiteInfoService;

    @PostMapping("/summarize")
    public String summarizeWebsite(@RequestBody String url) {
        return websiteInfoService.summarize(url);
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return websiteInfoService.getHistory();
    }
}
