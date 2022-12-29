package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.BaseEntity;
import com.EzenWeb.domain.Dto.memberDto;
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
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column(nullable = false)
    private String memail;
    @Column
    private String mpassword;
    @Column
    private String mphone;

    @OneToMany(mappedBy = "memberEntity")
    @Builder.Default
    private List<BoardEntity> boardlist = new ArrayList<>();
    @OneToMany(mappedBy = "RoomEntity")
    @Builder.Default
    private List<RoomEntity> roomlist = new ArrayList<>();
    @Column
    private String role;

    public memberDto toDto() {
        return memberDto.builder()
                        .mno(this.mno)
                        .memail(this.memail)
                        .mpassword(this.mpassword)
                        .mphone(this.mphone)
                        .build();
    }
}
