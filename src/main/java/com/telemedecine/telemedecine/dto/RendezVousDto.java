package com.telemedecine.telemedecine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RendezVousDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String name;
    @JsonView(UserView.Basic.class)
    private String description;
    @JsonView(UserView.Basic.class)
    private Date date;
}
