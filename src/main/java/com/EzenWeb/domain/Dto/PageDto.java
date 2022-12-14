package com.EzenWeb.domain.Dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PageDto {
    private int cno;
    private int page;
    private String key;
    private String keyword;
    @Builder.Default
    private List<BoardDto> list = new ArrayList<>();
    private int startbtn;
    private int endbtn;
    private long totalBoards;
}
