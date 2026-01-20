package com.awesome.thesis.logic.application.service.themen;

import com.awesome.thesis.logic.domain.model.themen.Thema;
import java.util.List;

/**
 * Interface für fachliches Repository von {@link Thema}.
 */
public interface ThemaRepoI {

  void save(Thema thema);

  void delete(Integer id);

  List<Thema> getThemen();

  Thema get(int id);

  void update(Integer key, Thema thema);

  boolean containsKey(Integer id);

}
