package com.example.Spring_Boot_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sum")
public class SummarizeLinkApi {

    @GetMapping("/me")
    public String getSummary(){
        return "Summarize Link API";
    }
}
