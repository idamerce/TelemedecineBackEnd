package com.telemedecine.telemedecine.mapper;

import com.telemedecine.telemedecine.domain.Ville;
import com.telemedecine.telemedecine.dto.VilleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VilleMapper {
    private VilleMapper() {}

    public static VilleDto toDto(Ville ville) {
        return VilleDto.builder()
                .id(ville.getId())
                .nom(ville.getNom())
                .build();
    }

    public static List<VilleDto> toDtos(List<Ville> villes) {
        return villes.stream().filter(Objects::nonNull)
                .map(VilleMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Ville toEntity(VilleDto villeDto) {
        return Ville.builder()
                .id(villeDto.getId())
                .nom(villeDto.getNom())
                .build();
    }
}
