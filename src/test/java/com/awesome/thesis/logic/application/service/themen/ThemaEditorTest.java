package com.awesome.thesis.logic.application.service.themen;

import com.awesome.thesis.logic.domain.model.links.Link;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import com.awesome.thesis.persistence.themen.IDatabaseThema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThemaEditorTest {
    @Test
    @DisplayName("When a new Link gets added, its saved in the repository")
    void test_1() {
        Thema thema = new Thema("Test", "Beschreibung");
        IThemaRepo repo = mock(IThemaRepo.class);
        when(repo.get("a")).thenReturn(thema);
        ThemaEditor editor = new ThemaEditor(repo);
        Link link = mock(Link.class);
        editor.addLink("a", link);
        assertThat(thema.getLinks()).contains(link);
    }


}
