package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.Dto.memberDto;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column(nullable = false)
    private String memail;
    @Column(nullable = false)
    private String mpassword;
    @Column(nullable = false)
    private String mphone;
    public memberDto toDto() {
        return memberDto.builder()
                        .mno(this.mno)
                        .memail(this.memail)
                        .mpassword(this.mpassword)
                        .mphone(this.mphone)
                        .build();
    }
}
