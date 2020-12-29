package com.telemedecine.telemedecine.utils;

import com.telemedecine.telemedecine.dto.reponse.AvailabilityMedecinResponseDto;
import com.telemedecine.telemedecine.repository.ShiftHoraireRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweakResponse {
    @Autowired
    private ShiftHoraireRepository shiftHoraireRepository;

    public List<AvailabilityMedecinResponseDto> listAllAvailiblityByStatus(List<AvailabilityMedecinResponseDto> availabilityMedecinList){
        int shiftNb = this.shiftHoraireRepository.findAll().size()+1;

        for(long i = 1 ; i < shiftNb; i++){
            Long j = Long.valueOf(i);
            var shift = this.shiftHoraireRepository.getOne(j);
            AvailabilityMedecinResponseDto dummy = new AvailabilityMedecinResponseDto(shift.getId(), shift.getTimeStart() +" - "+shift.getTimeEnd(), true);
            if (!availabilityMedecinList.contains(dummy))
            {
                availabilityMedecinList.add(dummy);
            }
        }
//        return availabilityMedecinList;
        return availabilityMedecinList.stream()
                .sorted(Comparator.comparing(AvailabilityMedecinResponseDto::getId))
                .collect(Collectors.toList());
    }
}
