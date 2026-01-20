package com.awesome.thesis.persistence.fachgebiete;

import com.awesome.thesis.persistence.fachgebiete.dto.FachgebietDto;
import java.util.Set;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

/**
 * Diese Klasse ist das technische Repository für das {@link FachgebietDto}.
 */
public interface FachgebieteDbRepository extends CrudRepository<FachgebietDto, String> {
  @NonNull
  Set<FachgebietDto> findAll();
  
  @Modifying
  @Query("insert into fachgebiet values (:name)")
  void insert(@Param("name") String name);
}
