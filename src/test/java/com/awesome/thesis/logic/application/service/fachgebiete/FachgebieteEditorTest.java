package com.awesome.thesis.logic.application.service.fachgebiete;

import com.awesome.thesis.logic.application.service.profiles.IProfileRepo;
import com.awesome.thesis.logic.application.service.themen.IThemaRepo;
import com.awesome.thesis.logic.domain.model.fachgebiete.Fachgebiet;
import com.awesome.thesis.logic.domain.model.profil.Profil;
import com.awesome.thesis.logic.domain.model.themen.Thema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class FachgebieteEditorTest {
    @Test
    @DisplayName("A new fachgebiet can be added")
    void test_add() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        when(repo.contains(anyString())).thenReturn(false);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.add("test");

        //Assert
        verify(repo).add(new Fachgebiet("test"));
    }

    @Test
    @DisplayName("An existing fachgebiet can't be added")
    void test_addExists() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        when(repo.contains(anyString())).thenReturn(true);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.add("test");

        //Assert
        verify(repo, never()).add(any());
    }

    @Test
    @DisplayName("getAll gets Fachgebiete from repo")
    void test_getAll() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.getAll();

        //Assert
        verify(repo).getAll();
    }

    @Test
    @DisplayName("An unused fachgebiet can be removed")
    void test_remove() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        when(profilRepo.getAll()).thenReturn(new ArrayList<>());
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        when(themaRepo.getThemen()).thenReturn(new ArrayList<>());
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.remove("test");

        //Assert
        verify(repo).delete("test");
    }

    @Test
    @DisplayName("An used fachgebiet by profile can't be removed")
    void test_removeFachgebietProfil() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        Profil profil = mock(Profil.class);
        when(profil.hasFachgebiet(any())).thenReturn(true);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        when(profilRepo.getAll()).thenReturn(List.of(profil));
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        when(themaRepo.getThemen()).thenReturn(new ArrayList<>());
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.remove("test");

        //Assert
        verify(repo, never()).delete(any());
    }

    @Test
    @DisplayName("An used fachgebiet by themen can't be removed")
    void test_removeFachgebietThema() {
        //Arrange
        IFachgebieteRepo repo = mock(IFachgebieteRepo.class);
        Thema thema = mock(Thema.class);
        when(thema.hasFachgebiet(any())).thenReturn(true);
        IProfileRepo profilRepo = mock(IProfileRepo.class);
        when(profilRepo.getAll()).thenReturn(new ArrayList<>());
        IThemaRepo themaRepo = mock(IThemaRepo.class);
        when(themaRepo.getThemen()).thenReturn(List.of(thema));
        FachgebieteEditor editor = new FachgebieteEditor(repo, profilRepo, themaRepo);

        //Act
        editor.remove("test");

        //Assert
        verify(repo, never()).delete(any());
    }
}