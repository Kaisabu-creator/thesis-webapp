package com.awesome.thesis.controller;

import com.awesome.thesis.logic.domain.model.files.DateiInfos;
import com.awesome.thesis.logic.application.service.files.DateiTypPruefer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Controller
public class DateiController {

    @GetMapping("/upload")
    public String showForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String annehmen(@RequestParam("datei") MultipartFile multipartFile,
                           @RequestParam(value = "beschreibung", required = false) String beschreibung,
                           Model model) {
        if (!DateiTypPruefer.verify(multipartFile)) {
            model.addAttribute("nachricht", "ungültiger Dateityp! Datei muss mit .zip, .pdf, oder .md enden.");
            return "upload";
        }
        try {
            String name = multipartFile.getOriginalFilename();

            DateiInfos dateiInfos = new DateiInfos(name, beschreibung);
            model.addAttribute("dateiInfos", dateiInfos);

            multipartFile.transferTo(Path.of("./upload_" + name));
            model.addAttribute("nachricht", name + " wurde erfolgreich hochgeladen.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "upload";
    }
}
