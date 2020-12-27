package com.telemedecine.telemedecine.mapper;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.dto.MedecinDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedecinMapper {
    public static MedecinDto toDto(Medecin medecin) {
        return MedecinDto.builder()
                .id(medecin.getId())
                .nom(medecin.getNom())
                .prenom(medecin.getPrenom())
                .cin(medecin.getCin())
                .adresse(medecin.getAdresse())
                .dateNaissance(medecin.getDateNaissance())
                .build()
                ;
    }
    public static List<MedecinDto> toDtos(List<Medecin> medecins) {
        return medecins.stream().filter(Objects::nonNull)
                .map(MedecinMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Medecin toEntity(MedecinDto medecinDto) {
        return  Medecin.builder()
                .id(medecinDto.getId())
                .nom(medecinDto.getNom())
                .prenom(medecinDto.getPrenom())
                .cin(medecinDto.getCin())
                .adresse(medecinDto.getAdresse())
                .dateNaissance(medecinDto.getDateNaissance())
                .build()
                ;

    }

}
