package com.awesome.thesis.persistence.themen.dtos;

import com.awesome.thesis.logic.domain.model.themen.ThemaDateiValue;
import com.awesome.thesis.logic.domain.model.themen.ThemaFachgebiet;
import com.awesome.thesis.logic.domain.model.themen.ThemaLink;
import com.awesome.thesis.logic.domain.model.themen.ThemaVoraussetzung;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
@Table("thema")
public record ThemaDTO(@Id Integer id,
                       String titel,
                       String beschreibung,
                       int profilID,
                       Set<ThemaLinkDTO> links,
                       Set<ThemaVoraussetzungDTO> voraussetzungen,
                       Set<ThemaFachgebietDTO> fachgebiete,
                       Set<ThemaDateiValueDTO> dateien) {
}
