package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.BaseEntity;
import com.EzenWeb.domain.Dto.BcategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name="Bcategory")
public class BcategoryEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    @Column(nullable = false)
    private String category;

    @OneToMany(mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "bcategoryEntity")
    @Builder.Default
    private List<VisitEntity> visitEntityList = new ArrayList<>();


    public BcategoryDto toDto() {
        return BcategoryDto.builder()
                           .cno(this.cno)
                           .category(this.category)
                           .build();
    }
}
