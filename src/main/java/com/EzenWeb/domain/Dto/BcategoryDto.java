package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.BcategoryEntity;
import lombok.*;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BcategoryDto {
    private int cno;
    private String category;

    public BcategoryEntity toEntity(){
        return BcategoryEntity.builder()
               .cno(cno)
               .category(category)
               .build();
    }
}
