package com.awesome.thesis.controller.admin;

import com.awesome.thesis.logic.application.service.profiles.ProfilEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminProfilCreator {
    @Autowired
    ProfilEditor editor;

    @GetMapping("createProfile")
    public String createProfile(Model model) {
        model.addAttribute("profile", editor.getAll());
        return "admin/profileAdmin";
    }
}
