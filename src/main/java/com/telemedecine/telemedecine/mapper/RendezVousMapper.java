package com.telemedecine.telemedecine.mapper;


import com.telemedecine.telemedecine.domain.RendezVous;

import com.telemedecine.telemedecine.dto.reponse.AvailabilityMedecinResponseDto;
import com.telemedecine.telemedecine.dto.RendezVousDto;
import com.telemedecine.telemedecine.dto.request.TodayAppointmentListDto;
import com.telemedecine.telemedecine.dto.reponse.TodayAppointmentResponseDto;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RendezVousMapper {
    private RendezVousMapper(){}
    public static RendezVousDto toDto(RendezVous rendezVous) {
        return RendezVousDto.builder()
                .id(rendezVous.getId())
                .date(rendezVous.getDate())
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
                .date(rendezVousDto.getDate())

                .build()
                ;


    }
    public TodayAppointmentResponseDto toDto(List<RendezVous> appointmentList, RendezVous appointement, int size){

        var TodayAppointmentResponse = new TodayAppointmentResponseDto();

        TodayAppointmentResponse.setDate(appointement.getDate());
        TodayAppointmentResponse.setSize(size);
        TodayAppointmentResponse.setEvents(
                appointmentList.stream()
                        .sorted((a1, a2) -> {
                            System.out.printf("sort: %s; %s\n", a1, a2);
                            return a1.getShiftHoraire().getId().compareTo(a2.getShiftHoraire().getId());
                        })
                        .map(a -> this.test(a))

                        .collect(Collectors.toList())
        );

        return TodayAppointmentResponse;
    }


    public TodayAppointmentListDto test(RendezVous appointement){

        var todayAppointmentListDto = new TodayAppointmentListDto();
        todayAppointmentListDto.setAppointmentId(appointement.getId());
        todayAppointmentListDto.setPatientEmail(appointement.getPatient().getAdresse());
        todayAppointmentListDto.setPatientName(appointement.getPatient().getNom());
        todayAppointmentListDto.setStatus(appointement.isActive());
        todayAppointmentListDto.setShift_horaire_id(appointement.getShiftHoraire().getId());
        todayAppointmentListDto.setTimeStart(appointement.getShiftHoraire().getTimeStart());
        todayAppointmentListDto.setTimeEnd(appointement.getShiftHoraire().getTimeEnd());
        return todayAppointmentListDto;
    }
    public AvailabilityMedecinResponseDto toAvailabilityMedecinDto(RendezVous appointement){
        var availabilityMedecin = new AvailabilityMedecinResponseDto();
        availabilityMedecin.setId(appointement.getShiftHoraire().getId());
        availabilityMedecin.setShiftHoraire(appointement.getShiftHoraire().getTimeStart() +" - "+appointement.getShiftHoraire().getTimeEnd());
        availabilityMedecin.setAvailable(false);
        return availabilityMedecin;
    }

}
