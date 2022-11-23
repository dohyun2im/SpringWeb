package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.Dto.VisitDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "visit")
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vno;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="cno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    public VisitDto toDto() {
        return VisitDto.builder().vno(this.vno)
                                 .category(this.bcategoryEntity.getCategory())
                                 .content(this.content)
                                 .build();
    }
}
