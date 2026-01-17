package com.awesome.thesis.architecture;

import com.awesome.thesis.ThesisApplication;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class ArchitectureTest {
    private final JavaClasses klassen =
            new ClassFileImporter()
                    .withImportOption(l -> !l.contains("/test/"))
                    .withImportOption(l -> !l.contains("ThesisApplication.class"))
                    .importPackagesOf(ThesisApplication.class);

    @Test
    @DisplayName("Die Thesis Anwendung hat eine Onion Architektur")
    public void onionArchitektur() throws Exception {
        ArchRule rule = onionArchitecture()
                .domainModels("com.awesome.thesis.logic.domain.model..")
                .domainServices("com.awesome.thesis.logic.application.service..")
                .applicationServices("com.awesome.thesis.logic.application.service..")
                .adapter("web", "com.awesome.thesis.controller..")
                .adapter("persistence", "com.awesome.thesis.persistence..")
                .adapter("security", "com.awesome.thesis.configurations..");
        rule.check(klassen);
    }
}
