package com.awesome.thesis.logic.application.service.themen;

import com.awesome.thesis.logic.domain.model.links.Link;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import com.awesome.thesis.persistence.themen.FakeDatabaseThemaImpl;
import com.awesome.thesis.persistence.themen.IDatabaseThema;
import com.awesome.thesis.persistence.themen.ThemaRepoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThemaRepoTest {
    @Test
    @DisplayName("If a Thema with the given id exists, it will be returned")
    void test_1() {

        //Arrange
        Thema thema = new Thema("Programmierpraktikum 2", "Beispieltext");
        thema.addUrl(new Link("https://www.google.com/", "Google als Beispiel"));
        thema.addUrl(new Link("https://www.youtube.com/", "Youtube als Beispiel"));
        IDatabaseThema database = new FakeDatabaseThemaImpl();
        String id = database.save(thema);

        //Act
        ThemaRepoImpl repository = new ThemaRepoImpl(database);

        //Assert
        assertThat(repository.getThema(id)).isEqualTo(thema);
    }
    @Test
    @DisplayName("If no Thema with given id exists, a NoSuchElementException gets thrown")
    void test_2() {
        IDatabaseThema database = new FakeDatabaseThemaImpl();
        ThemaRepoImpl repository = new ThemaRepoImpl(database);
        assertThrows(NoSuchElementException.class, () -> repository.getThema("falsche id"));
    }
}
