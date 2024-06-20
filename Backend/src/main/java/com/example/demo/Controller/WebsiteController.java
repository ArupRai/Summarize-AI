package com.example.demo.Controller;

import com.example.demo.Service.WebsiteInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebsiteController {

    private final WebsiteInfoService websiteInfoService;

    public WebsiteController(WebsiteInfoService websiteInfoService) {
        this.websiteInfoService = websiteInfoService;
    }

    @PostMapping("/summarize")
    public String summarize(@RequestBody String url) {
        return websiteInfoService.summarize(url);
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return websiteInfoService.getHistory();
    }
}
