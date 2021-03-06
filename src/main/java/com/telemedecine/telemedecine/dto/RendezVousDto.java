package com.telemedecine.telemedecine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RendezVousDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private LocalDate date;
    @JsonView(UserView.Basic.class)
    private String location;
}
