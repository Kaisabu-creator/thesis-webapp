package com.awesome.thesis;

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

    @GetMapping("/")
    public String showForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String annehmen(@RequestParam ("datei") MultipartFile multipartFile, Model model) {
        try {
            String name = multipartFile.getOriginalFilename();
            multipartFile.transferTo(Path.of("./upload_"+name));
            model.addAttribute("nachricht", name + " wurde erfolgreich hochgeladen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "upload";
    }
}
