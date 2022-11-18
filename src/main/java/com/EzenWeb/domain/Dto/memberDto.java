package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.MemberEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class memberDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mphone;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
               .mno(this.mno)
               .memail(this.memail)
               .mpassword(this.mpassword)
               .mphone(this.mphone)
               .build();
    }
}
