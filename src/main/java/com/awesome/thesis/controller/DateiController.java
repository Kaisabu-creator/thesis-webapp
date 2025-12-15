package com.awesome.thesis.controller;

import com.awesome.thesis.logic.application.service.files.DateiService;
import com.awesome.thesis.logic.domain.model.files.DateiInfos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DateiController {

    private final DateiService dateiService;

    public DateiController(DateiService dateiService) {
        this.dateiService = dateiService;
    }

    @GetMapping("/upload")
    public String showForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String annehmen(@RequestParam("datei") MultipartFile multipartFile,
                           @RequestParam(value = "beschreibung", required = false) String beschreibung,
                           Model model) {
        try {
            DateiInfos infos = dateiService.infosErstellen(multipartFile, beschreibung);

            model.addAttribute("dateiInfos", infos);
            model.addAttribute("nachricht", infos.getTitle() + " wurde erfolgreich hochgeladen.");

            return "upload";
        } catch (IllegalArgumentException e) {
            model.addAttribute("nachricht", e.getMessage());
            return "upload";
        }
    }
}
