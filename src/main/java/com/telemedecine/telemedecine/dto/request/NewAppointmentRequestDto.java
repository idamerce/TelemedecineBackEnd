package com.telemedecine.telemedecine.dto.request;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NewAppointmentRequestDto {

    @JsonView(UserView.Basic.class)
    private String name;
    @JsonView(UserView.Basic.class)
    private String phoneNumber;
    @JsonView(UserView.Basic.class)
    private String date;
    @JsonView(UserView.Basic.class)
    private Long shiftTimeId;
    @JsonView(UserView.Basic.class)
    private Long medecinId;
    @JsonView(UserView.Basic.class)
    private Long specialiteId;
    @JsonView(UserView.Basic.class)
    private boolean isValide = false;
}
