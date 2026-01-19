package com.awesome.thesis.persistence.themen;

import com.awesome.thesis.logic.domain.model.themen.Thema;
import com.awesome.thesis.persistence.themen.dtos.ThemaDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThemenDBRepository extends CrudRepository<ThemaDTO, Integer> {
    Thema save(Thema thema);
    ThemaDTO findById(int id);

    List<ThemaDTO> findAll();

}
