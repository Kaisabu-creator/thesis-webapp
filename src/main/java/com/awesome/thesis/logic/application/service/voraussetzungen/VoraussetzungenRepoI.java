package com.awesome.thesis.logic.application.service.voraussetzungen;

import com.awesome.thesis.logic.domain.model.voraussetzungen.Voraussetzung;
import java.util.Set;

/**
 * Die Schnittstelle zwischen Application Service und Persistenz für Voraussetzungen.
 */
public interface VoraussetzungenRepoI {

  /**
   * Fügt eine Voraussetzung hinzu.
   *
   * @param voraussetzung Der Eingabewert.
   */
  void add(Voraussetzung voraussetzung);

  /**
   * Löscht eine Voraussetzung falls vorhanden.
   *
   * @param voraussetzung Der Eingabewert.
   */
  void remove(Voraussetzung voraussetzung);

  /**
   * Fragt nach allen Voraussetzungen in der Datenbank nach.
   *
   * @return Alle Voraussetzungen in der Datenbank.
   */
  Set<Voraussetzung> getAll();

  /**
   * Guckt, ob die Voraussetzung in der Datenbank vorhanden ist.
   *
   * @param voraussetzung Der Wert, der gesucht werden soll.
   * @return True falls die Voraussetzung in der Datenbank ist, sonst false.
   */
  boolean contains(Voraussetzung voraussetzung);
}
