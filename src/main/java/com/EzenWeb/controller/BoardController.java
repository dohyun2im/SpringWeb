package com.EzenWeb.controller;

import com.EzenWeb.domain.Dto.BcategoryDto;
import com.EzenWeb.domain.Dto.BoardDto;
import com.EzenWeb.domain.Dto.VisitDto;
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
    @GetMapping("/visit")
    public Resource visit() {
        return new ClassPathResource("templates/board/visit.html");
    }
    //카테고리넣기.
    @PostMapping("/setcategory")
    public boolean setcategory(@RequestParam String category) {
        return boardService.setcategory(BcategoryDto.builder().category(category).build());
    }
    //카테고리 리스트
    @GetMapping("/getcategory")
    public List<BcategoryDto> getcategory() {
        return boardService.getcategory();
    }
    //게시물쓰기
    @PostMapping("/setboard")
    public boolean setboard(BoardDto dto) {
        System.out.println("확인"+dto.toString());
        return boardService.setboard(dto);
    }
    //목록보기
    @GetMapping("/getboards")
    public List<BoardDto> getboards(@RequestParam int cno) {
        return boardService.getboards(cno);
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
    //방명록 작성
    @PostMapping("/setvisit")
    public boolean setvisit(@RequestBody VisitDto dto){return boardService.setvisit(dto);}
    @GetMapping("/getvisit")
    public List<VisitDto> getvisit(@RequestParam int category){return boardService.getvisit(category);}
    @GetMapping("/filedownload")
    public void filedownload(@RequestParam String filename){
        boardService.filedownload(filename);
    }
}
