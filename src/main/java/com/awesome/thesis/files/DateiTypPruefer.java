package com.awesome.thesis.files;

import org.springframework.web.multipart.MultipartFile;

public class DateiTypPruefer {

    public static boolean verify(MultipartFile datei){
        String dateiName = datei.getOriginalFilename();
        return (dateiName.endsWith(".zip") || dateiName.endsWith(".pdf") || dateiName.endsWith(".md"));
    }
}
