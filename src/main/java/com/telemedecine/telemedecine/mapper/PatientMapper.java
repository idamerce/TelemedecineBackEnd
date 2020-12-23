package com.telemedecine.telemedecine.mapper;


import com.telemedecine.telemedecine.domain.Patient;

import com.telemedecine.telemedecine.dto.PatientDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientDto toDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .nom(patient.getNom())
                .prenom(patient.getPrenom())
                .cin(patient.getCin())
                .adresse(patient.getAdresse())
                .dateNaissance(patient.getDateNaissance())
                .build()
                ;
    }
    public static List<PatientDto> toDtos(List<Patient> patients) {
        return patients.stream().filter(Objects::nonNull)
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Patient toEntity(PatientDto patientDto) {
        return  Patient.builder()
                .id(patientDto.getId())
                .nom(patientDto.getNom())
                .prenom(patientDto.getPrenom())
                .cin(patientDto.getCin())
                .adresse(patientDto.getAdresse())
                .dateNaissance(patientDto.getDateNaissance())
                .build()
                ;


    }
}
