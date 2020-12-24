package com.telemedecine.telemedecine.mapper;


import com.telemedecine.telemedecine.domain.RendezVous;

import com.telemedecine.telemedecine.dto.RendezVousDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RendezVousMapper {
    public static RendezVousDto toDto(RendezVous rendezVous) {
        return RendezVousDto.builder()
                .id(rendezVous.getId())
                .name(rendezVous.getName())
                .description(rendezVous.getDescription())
                .date(rendezVous.getRendezvousDateTime())
                .build()
                ;
    }
    public static List<RendezVousDto> toDtos(List<RendezVous> rendezVousList) {
        return rendezVousList.stream().filter(Objects::nonNull)
                .map(RendezVousMapper::toDto)
                .collect(Collectors.toList());
    }

    public static RendezVous toEntity(RendezVousDto rendezVousDto) {
        return  RendezVous.builder()
                .id(rendezVousDto.getId())
                .description(rendezVousDto.getDescription())
                .rendezvousDateTime(rendezVousDto.getDate())
                .build()
                ;


    }
}
