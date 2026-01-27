package com.awesome.thesis.persistence.fachgebiete;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.awesome.thesis.logic.domain.model.fachgebiete.Fachgebiet;
import com.awesome.thesis.persistence.fachgebiete.dto.FachgebietDto;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.OptimisticLockingFailureException;

class FachgebieteRepoImplTest {
  private FachgebieteDbRepository dbRepository;
  
  @BeforeEach
  void dependencies() {
    dbRepository = mock(FachgebieteDbRepository.class);
  }
  
  @Test
  @DisplayName("Fachgebiete können hinzugefügt werden und werden richtig gemapped")
  void test_add() {
    //Arrange
    Fachgebiet fachgebiet = new Fachgebiet("test");
    FachgebieteRepoImpl repo = new FachgebieteRepoImpl(dbRepository);
    
    //Act
    repo.add(fachgebiet);
    
    //Assert
    verify(dbRepository).save(new FachgebietDto("test", null));
  }
  
  @Test
  @DisplayName("Fachgebiete kann mit Locking Exception umgehen")
  void test_add_Locking() {
    //Arrange
    when(dbRepository.save(any())).thenThrow(new OptimisticLockingFailureException("exception"));
    Fachgebiet fachgebiet = new Fachgebiet("test");
    FachgebieteRepoImpl repo = new FachgebieteRepoImpl(dbRepository);
    
    //Act
    repo.add(fachgebiet);
  }
  
  @Test
  @DisplayName("Fachgebiete löscht Fachgebiete mit Id")
  void test_delete() {
    //Arrange
    FachgebieteRepoImpl repo = new FachgebieteRepoImpl(dbRepository);
    
    //Act
    repo.delete("test");
    
    //Assert
    verify(dbRepository).deleteById("test");
  }
  
  @Test
  @DisplayName("getAll funktioniert und mapped richtig zu Fachgebiet")
  void test_getAll() {
    //Arrange
    when(dbRepository.findAll()).thenReturn(Set.of(new FachgebietDto("test", 0)));
    FachgebieteRepoImpl repo = new FachgebieteRepoImpl(dbRepository);
    
    //Act + Assert
    assertThat(repo.getAll()).contains(new Fachgebiet("test", 0));
  }
  
  @Test
  @DisplayName("Fachgebiete findet Fachgebiete mit Id")
  void test_contains() {
    //Arrange
    FachgebieteRepoImpl repo = new FachgebieteRepoImpl(dbRepository);
    
    //Act
    repo.contains("test");
    
    //Assert
    verify(dbRepository).existsById("test");
  }
}