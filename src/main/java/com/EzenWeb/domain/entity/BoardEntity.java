package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.Dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name="board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column(nullable = false)
    private String btitle;
    @Column(nullable = false , columnDefinition = "text")
    private String bcontent;
    @Column(nullable = true)
    @ColumnDefault("0")
    private int bview;
    @Column(nullable = false , columnDefinition = "longtext")
    private String bfile;
    @Column(nullable = true)
    private int mno;
    @Column(nullable = true)
    private int cno;

    public BoardDto toDto(){
        return BoardDto.builder()
                       .bno(this.bno)
                       .btitle(this.btitle)
                       .bcontent(this.bcontent)
                       .bview(this.bview)
                       .bfile(this.bfile)
                       .mno(this.mno)
                       .cno(this.cno)
                       .build();
    }
}
