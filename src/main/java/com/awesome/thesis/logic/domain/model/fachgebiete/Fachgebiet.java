package com.awesome.thesis.logic.domain.model.fachgebiete;

import com.awesome.thesis.annotations.AggregateRoot;
import java.util.Objects;

/**
 * Diese Klasse ist das Aggregat Root des Fachgebiet-Aggregates und
 * speichert das Fachgebiet in einem String.
 */
@AggregateRoot
public class Fachgebiet {
  private final String name;
  
  public Fachgebiet(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Fachgebiet that = (Fachgebiet) o;
    return Objects.equals(name, that.name);
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}
