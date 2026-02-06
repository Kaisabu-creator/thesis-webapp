package com.awesome.thesis.persistence.themen;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awesome.thesis.logic.domain.model.themen.Thema;
import com.awesome.thesis.persistence.themen.dtos.ThemaDateiValueDto;
import com.awesome.thesis.persistence.themen.dtos.ThemaDto;
import com.awesome.thesis.persistence.themen.dtos.ThemaFachgebietDto;
import com.awesome.thesis.persistence.themen.dtos.ThemaLinkDto;
import com.awesome.thesis.persistence.themen.dtos.ThemaVoraussetzungDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the ThemaRepoImpl Class.
 */
public class ThemaRepoImplTest {

  private ThemenDbRepository db;
  private ThemaRepoImpl repo;

  @BeforeEach
  void setUp() {
    db = mock(ThemenDbRepository.class);
    repo = new ThemaRepoImpl(db);
  }

  private ThemaDto createThemaDto() {
    return new ThemaDto(
        2, 0, "titel", "beschreibung",
        1,
        Set.of(new ThemaLinkDto("https://www.google.com/", "hallo")),
        Set.of(new ThemaVoraussetzungDto("vor")),
        Set.of(new ThemaFachgebietDto("fach")),
        Set.of(new ThemaDateiValueDto("id", "name", "beschreibung")));
  }

  @Test
  @DisplayName("get returns the correct element")
  void test_1() {
    when(db.findById(anyInt())).thenReturn(createThemaDto());

    Thema thema = repo.get(2);
    assertThat(thema.getId()).isEqualTo(2);
    assertThat(thema.getTitel()).isEqualTo("titel");
    assertThat(thema.getBeschreibung()).isEqualTo("beschreibung");
    assertThat(thema.getProfilId()).isEqualTo(1);
  }

  @Test
  @DisplayName("getThemen retuns correct Themas")
  void test_2() {
    when(db.findAll()).thenReturn(
        List.of(createThemaDto()
        )
    );

    List<Thema> themen = repo.getThemen();
    assertThat(themen.getFirst().getId()).isEqualTo(2);
  }

  @Test
  @DisplayName("containsKey returns the correct boolean")
  void test_3() {
    when(db.existsById(2)).thenReturn(true);
    boolean exists = repo.containsKey(2);
    assertThat(exists).isEqualTo(true);
  }

  @Test
  @DisplayName("save saves the thema in the database")
  void test_4() {
    when(db.save((ThemaDto) any())).thenReturn(createThemaDto()
    );

    Thema thema = repo.save(new Thema(
        2, 0, "titel", "beschreibung",
        1, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>())
    );

    assertThat(thema.getId()).isEqualTo(2);
    assertThat(thema.getTitel()).isEqualTo("titel");
  }

  @Test
  @DisplayName("delete deletes the thema from the database")
  void test_5() {
    repo.delete(1);
    verify(db).deleteById(1);
  }
}
