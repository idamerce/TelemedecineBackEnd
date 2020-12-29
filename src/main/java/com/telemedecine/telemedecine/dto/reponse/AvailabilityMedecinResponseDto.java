package com.telemedecine.telemedecine.dto.reponse;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AvailabilityMedecinResponseDto {

    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String shiftHoraire;
    @JsonView(UserView.Basic.class)
    private boolean available;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final AvailabilityMedecinResponseDto other = (AvailabilityMedecinResponseDto) obj;

        if (this.getId().intValue() == other.getId().intValue()) {
            return true;
        }else{
            return false;
        }

    }
}
