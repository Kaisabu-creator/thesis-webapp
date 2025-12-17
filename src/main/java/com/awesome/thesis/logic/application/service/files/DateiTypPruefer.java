package com.awesome.thesis.logic.application.service.files;

import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

public class DateiTypPruefer {
    private static final Set<String> ERLAUBTE_DATEIENDUNGEN = Set.of("zip", "pdf", "md");

    public static void verify(MultipartFile datei) {
        String dateiName = datei.getOriginalFilename();
        boolean erlaubt = ERLAUBTE_DATEIENDUNGEN.stream()
                .anyMatch(dateiName::endsWith);

        if (!erlaubt) {
            throw new IllegalArgumentException(
                    "Dateityp nicht erlaubt: " + dateiName);
        }

    }
}
