package com.EzenWeb.domain.entity;

import com.EzenWeb.domain.BaseEntity;
import com.EzenWeb.domain.Dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name="board")
public class BoardEntity extends BaseEntity {
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
    @Column(columnDefinition = "longtext")
    private String bfile;

    @ManyToOne
    @JoinColumn(name="mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name="cno")
    @ToString.Exclude
    private BcategoryEntity bcategoryEntity;

    public BoardDto toDto(){
        return BoardDto.builder()
                       .bno(this.bno)
                       .btitle(this.btitle)
                       .bcontent(this.bcontent)
                       .bview(this.bview)
                       .filename(this.bfile)
                       .category(this.bcategoryEntity.getCategory())
                       .memail(this.memberEntity.getMemail().split("@")[0])
                       .build();
    }
}
