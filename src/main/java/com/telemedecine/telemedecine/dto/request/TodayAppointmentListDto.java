package com.telemedecine.telemedecine.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodayAppointmentListDto {
    @JsonView(UserView.Basic.class)
    private Long appointmentId;
    @JsonView(UserView.Basic.class)
    private String patientName;
    @JsonView(UserView.Basic.class)
    private String patientEmail;
    @JsonView(UserView.Basic.class)
    private Long shift_horaire_id;
    @JsonView(UserView.Basic.class)
    private LocalTime timeStart;
    @JsonView(UserView.Basic.class)
    private LocalTime timeEnd;
    @JsonView(UserView.Basic.class)
    private boolean status;
}
