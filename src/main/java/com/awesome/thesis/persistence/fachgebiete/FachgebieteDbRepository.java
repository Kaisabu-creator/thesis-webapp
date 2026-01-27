package com.awesome.thesis.persistence.fachgebiete;

import com.awesome.thesis.persistence.fachgebiete.dto.FachgebietDto;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

/**
 * Diese Klasse ist das technische Repository für das {@link FachgebietDto}.
 */
public interface FachgebieteDbRepository extends CrudRepository<FachgebietDto, String> {
  @NonNull
  Set<FachgebietDto> findAll();
}
