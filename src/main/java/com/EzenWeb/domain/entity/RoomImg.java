package com.EzenWeb.domain.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "roomimg")
public class RoomImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rino;
    private String rimg;

    @ManyToOne
    @JoinColumn(name = "rno")
    @ToString.Exclude
    private RoomEntity roomEntity;
}
