package com.awesome.thesis.persistence.profiles;

import com.awesome.thesis.logic.application.service.profiles.IProfileRepo;
import com.awesome.thesis.logic.domain.model.profil.*;
import com.awesome.thesis.persistence.profiles.dtos.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProfileRepoImpl implements IProfileRepo {
    ProfileDBRepository dbRepository;

    public ProfileRepoImpl(ProfileDBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @Override
    public Profil get(int id) {
        return toProfil(dbRepository.findById(id));
    }

    @Override
    public boolean containsKey(int id) {
        return dbRepository.existsById(id);
    }

    @Override
    public void save(Profil profil) {
        dbRepository.insert(profil.getId(), profil.getName());
    }

    @Override
    public void update(Profil profil) {
        dbRepository.save(toProfilDTO(profil));
    }

    @Override
    public void delete(int id) {
        dbRepository.deleteById(id);
    }

    @Override
    public List<Profil> getAll() {
        return dbRepository.findAll().stream()
                .map(this::toProfil)
                .toList();
    }

    //Mapping Profil -> ProfilDTO
    private ProfilDTO toProfilDTO(Profil profil) {
        return new ProfilDTO(profil.getId(), profil.getName(), translateKontakte(profil.getKontakte()), translateFachgebiete(profil.getFachgebiete()), translateLinks(profil.getLinks()), translateThemen(profil.getThemen()), translateDateien(profil.getDateien()));
    }

    private Set<ProfilDateiValueDTO> translateDateien(Set<ProfilDateiValue> profilDateiValues) {
        return profilDateiValues.stream()
                .map(this::toDateiValueDTO)
                .collect(Collectors.toSet());
    }

    private ProfilDateiValueDTO toDateiValueDTO(ProfilDateiValue profilDateiValue) {
        return new ProfilDateiValueDTO(profilDateiValue.id(), profilDateiValue.name(), profilDateiValue.beschreibung());
    }

    private Set<ProfilThemaValueDTO> translateThemen(Set<ProfilThemaValue> profilThemaValues) {
        return profilThemaValues.stream()
                .map(this::toThemaValueDTO)
                .collect(Collectors.toSet());
    }

    private ProfilThemaValueDTO toThemaValueDTO(ProfilThemaValue profilThemaValue) {
        return new ProfilThemaValueDTO(profilThemaValue.id(), profilThemaValue.name());
    }

    private Set<ProfilLinkDTO> translateLinks(Set<ProfilLink> profilLinks) {
        return profilLinks.stream()
                .map(this::toLinkDTO)
                .collect(Collectors.toSet());
    }

    private ProfilLinkDTO toLinkDTO(ProfilLink profilLink) {
        return new ProfilLinkDTO(profilLink.url(), profilLink.text());
    }

    private Set<ProfilFachgebietDTO> translateFachgebiete(Set<String> fachgebiete) {
        return fachgebiete.stream()
                .map(this::toFachgebietDTO)
                .collect(Collectors.toSet());
    }

    private ProfilFachgebietDTO toFachgebietDTO(String profilFachgebiet) {
        return new ProfilFachgebietDTO(profilFachgebiet);
    }

    private Set<ProfilKontaktDTO> translateKontakte(Set<ProfilKontakt> kontakt) {
        return kontakt.stream()
                .map(this::toKontaktDTO)
                .collect(Collectors.toSet());
    }

    private ProfilKontaktDTO toKontaktDTO(ProfilKontakt profilKontakt) {
        return new ProfilKontaktDTO(profilKontakt.label(), profilKontakt.wert(), profilKontakt.kontaktart().name());
    }

    //Mapping ProfilDTO -> Profil
    private Profil toProfil(ProfilDTO profilDTO) {
        return new Profil(profilDTO.id(), profilDTO.name(), translateKontaktDTOS(profilDTO.kontakte()), translateFachgebietDTOS(profilDTO.fachgebiete()), translateLinkDTOS(profilDTO.links()), translateThemaDTOS(profilDTO.themen()), translateDateiDTOS(profilDTO.dateien()));
    }

    private Set<ProfilDateiValue> translateDateiDTOS(Set<ProfilDateiValueDTO> profilDateiValueDTOS) {
        return profilDateiValueDTOS.stream()
                .map(this::toDateiValue)
                .collect(Collectors.toSet());
    }

    private ProfilDateiValue toDateiValue(ProfilDateiValueDTO profilDateiValueDTO) {
        return new ProfilDateiValue(profilDateiValueDTO.id(), profilDateiValueDTO.name(), profilDateiValueDTO.beschreibung());
    }

    private Set<ProfilThemaValue> translateThemaDTOS(Set<ProfilThemaValueDTO> profilThemaValueDTOS) {
        return profilThemaValueDTOS.stream()
                .map(this::toThemaValue)
                .collect(Collectors.toSet());
    }

    private ProfilThemaValue toThemaValue(ProfilThemaValueDTO profilThemaValueDTO) {
        return new ProfilThemaValue(profilThemaValueDTO.id(), profilThemaValueDTO.name());
    }

    private Set<ProfilLink> translateLinkDTOS(Set<ProfilLinkDTO> profilLinkDTOS) {
        return profilLinkDTOS.stream()
                .map(this::toLink)
                .collect(Collectors.toSet());
    }

    private ProfilLink toLink(ProfilLinkDTO profilLinkDTO) {
        return new ProfilLink(profilLinkDTO.url(), profilLinkDTO.text());
    }

    private Set<ProfilFachgebiet> translateFachgebietDTOS(Set<ProfilFachgebietDTO> fachgebietDTOS) {
        return fachgebietDTOS.stream()
                .map(this::toFachgebiet)
                .collect(Collectors.toSet());
    }

    private ProfilFachgebiet toFachgebiet(ProfilFachgebietDTO profilFachgebietDTO) {
        return new ProfilFachgebiet(profilFachgebietDTO.fachgebiet());
    }

    private Set<ProfilKontakt> translateKontaktDTOS(Set<ProfilKontaktDTO> kontaktDTOS) {
        return kontaktDTOS.stream()
                .map(this::toKontakt)
                .collect(Collectors.toSet());
    }

    private ProfilKontakt toKontakt(ProfilKontaktDTO profilKontaktDTO) {
        return new ProfilKontakt(profilKontaktDTO.label(), profilKontaktDTO.wert(), ProfilKontaktart.valueOf(profilKontaktDTO.kontaktart()));
    }
}
