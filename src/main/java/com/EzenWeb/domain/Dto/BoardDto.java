package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.BoardEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private MultipartFile bfile;
    private int mno;
    private int cno;
    private String filename;
    private String category;
    private String memail;


    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
