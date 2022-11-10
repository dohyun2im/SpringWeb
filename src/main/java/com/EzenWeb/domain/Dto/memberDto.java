package com.EzenWeb.domain.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class memberDto {
    private String name;
    private String email;
    private String organization;
}
