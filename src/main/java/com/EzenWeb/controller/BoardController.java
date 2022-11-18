package com.EzenWeb.controller;

import com.EzenWeb.domain.Dto.BoardDto;
import com.EzenWeb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;


    @GetMapping("/boardlist")
    public Resource boardList() {
        return new ClassPathResource("templates/board/list.html");
    }
    @GetMapping("/write")
    public Resource write() {
    return new ClassPathResource("templates/board/write.html");
    }
    @GetMapping("/view")
    public Resource view() {
        return new ClassPathResource("templates/board/view.html");
    }
    @GetMapping("/update")
    public Resource update() {
        return new ClassPathResource("templates/board/update.html");
    }


    //게시물쓰기
    @PostMapping("/setboard")
    public boolean setboard(@RequestBody BoardDto dto) {
        return boardService.setboard(dto);
    }
    //목록보기
    @GetMapping("/getboards")
    public List<BoardDto> getboards() {
        return boardService.getboards();
    }
    //개별조회
    @GetMapping("/getboard")
    public BoardDto getboard(@RequestParam int bno){
       return boardService.getboard(bno);
    }
    //게시물수정
    @PutMapping("/updateboard")
    public boolean updateboard(@RequestBody BoardDto dto){
        return boardService.updateboard(dto);
    }
    //게시물삭제
    @DeleteMapping("/deleteboard")
    public boolean deleteboard(@RequestParam int bno){
        return boardService.deleteboard(bno);
    }
}
