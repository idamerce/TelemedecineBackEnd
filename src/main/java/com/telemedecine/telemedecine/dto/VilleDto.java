package com.telemedecine.telemedecine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VilleDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String nom;
}
