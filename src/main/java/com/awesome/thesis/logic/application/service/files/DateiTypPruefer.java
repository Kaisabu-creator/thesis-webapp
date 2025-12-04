package com.awesome.thesis.logic.application.service.files;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DateiTypPruefer {

    public static boolean verify(MultipartFile datei){
        String dateiName = datei.getOriginalFilename();
        return (dateiName.endsWith(".zip") || dateiName.endsWith(".pdf") || dateiName.endsWith(".md"));
    }
}
