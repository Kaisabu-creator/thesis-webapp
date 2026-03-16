package com.awesome.thesis.logic.domain.model.profil;

import com.awesome.thesis.annotations.AggregateValue;

/**
 * Dieser Record dient der fachlichen Speicherung von einem Snapshot einer Datei.
 *
 * @param id Schlüssel von Datei
 * @param name Name der Datei
 */
@AggregateValue
public record ProfilDateiValue(String id, String name) {
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ProfilDateiValue other)) {
      return false;
    }
    return this.id.equals(other.id);
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
