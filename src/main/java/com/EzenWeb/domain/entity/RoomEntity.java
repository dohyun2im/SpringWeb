package com.EzenWeb.domain.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;
    private String rtitle;
    private String rprice;
    private String rtrans;
    private String name;
    private String lat;
    private String lng;

    @ManyToOne
    @JoinColumn(name = "mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "RoomEntity")
    @Builder.Default
    @ToString.Exclude
    private List<RoomImg> roomImgslist = new ArrayList<>();
}
