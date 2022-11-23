package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.VisitEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VisitDto {
    private int vno;
    private int cno;
    private String category;
    private String content;

    public VisitEntity toEntity() {
        return VisitEntity.builder().vno(this.vno)
                                    .content(this.content)
                                    .build();
    }
}
