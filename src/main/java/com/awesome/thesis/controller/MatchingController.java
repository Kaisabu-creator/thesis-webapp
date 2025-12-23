package com.awesome.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchingController {
    @GetMapping("/matching")
    public String matching() throws Exception {
        return "matching";
    }
}
