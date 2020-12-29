package com.telemedecine.telemedecine.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StateAppointmentRequestDto {

    @JsonView(UserView.Basic.class)
    private Long appointmentId;
    @JsonView(UserView.Basic.class)
    private boolean status;
}
