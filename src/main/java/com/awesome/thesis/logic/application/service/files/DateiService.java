package com.awesome.thesis.logic.application.service.files;

import com.awesome.thesis.logic.domain.model.files.DateiInfos;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DateiService {

    public DateiInfos infosErstellen(MultipartFile datei, String beschreibung){
        DateiTypPruefer.verify(datei);

        String name = datei.getOriginalFilename();

        return new DateiInfos(name, beschreibung);
    }
}
