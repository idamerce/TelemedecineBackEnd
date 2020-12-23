package com.telemedecine.telemedecine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.views.UserView;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SpecialiteDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String designation;
}
