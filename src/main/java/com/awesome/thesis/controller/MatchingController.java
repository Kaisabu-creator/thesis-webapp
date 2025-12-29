package com.awesome.thesis.controller;

import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchingController {
    @Autowired
    ProfilEditor profilEditor;

    @GetMapping("/matching")
    public String matching(Model model) throws Exception {
        model.addAttribute("profile", profilEditor.getAll());
        return "matching";
    }
}
