package com.telemedecine.telemedecine.dto.reponse;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.request.TodayAppointmentListDto;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TodayAppointmentResponseDto {
    @JsonView(UserView.Basic.class)
    private LocalDate date;
    @JsonView(UserView.Basic.class)
    private int size;
    @JsonView(UserView.Basic.class)
    private List<TodayAppointmentListDto> events = new ArrayList<>();
}
