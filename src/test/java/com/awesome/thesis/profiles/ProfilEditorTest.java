package com.awesome.thesis.profiles;

import com.awesome.thesis.profiles.profil.Profil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProfilEditorTest {
    @Test
    @DisplayName("a non existing Profil that's saved to the database gets an id")
    void testId() {
        //Arrange
        Profil profil = new Profil("test");
        Profile profile = mock(Profile.class);
        when(profile.containsKey(anyLong())).thenReturn(true);
        when(profile.save(any(Profil.class))).thenReturn(1L);
        ProfilEditor editor = new ProfilEditor(profile);

        //Act
        editor.add(profil);

        //Assert
        assertThat(profil.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("an existing Profil that's saved gets updated")
    void testUpdate() {
        //Arrange
        Profil profil = new Profil("test");
        profil.setId(1L);
        Profile profile = mock(Profile.class);
        when(profile.containsKey(anyLong())).thenReturn(true);
        ProfilEditor editor = new ProfilEditor(profile);

        //Act
        editor.add(profil);

        //Assert
        verify(profile).update(anyLong(), any(Profil.class));
    }

    @Test
    @DisplayName("an existing Profil can be loaded from the database")
    void testGet() {
        //Arrange
        Profile profile = mock(Profile.class);
        when(profile.containsKey(anyLong())).thenReturn(true);
        ProfilEditor editor = new ProfilEditor(profile);

        //Act
        editor.get(1L);

        //Assert
        verify(profile).get(1L);
    }
}