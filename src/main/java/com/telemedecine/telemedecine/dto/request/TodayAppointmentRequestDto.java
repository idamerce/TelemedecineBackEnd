package com.telemedecine.telemedecine.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodayAppointmentRequestDto {
    @JsonView(UserView.Basic.class)
    private Long medecinId;
    @JsonView(UserView.Basic.class)
    private String date;
}
