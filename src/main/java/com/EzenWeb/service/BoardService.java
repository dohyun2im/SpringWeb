package com.EzenWeb.service;

import com.EzenWeb.domain.Dto.BoardDto;
import com.EzenWeb.domain.entity.BoardEntity;
import com.EzenWeb.domain.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Transactional
    public boolean setboard(BoardDto dto) {
        BoardEntity entity = boardRepository.save(dto.toEntity());
        if (entity.getBno() > 0) {return true;}
        return false;
    }
    @Transactional
    public List<BoardDto> getboards() {
        List<BoardEntity> entities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();
        for (BoardEntity entity : entities) {
            dtos.add(entity.toDto());
        }
        return dtos;
    }
    @Transactional
    public BoardDto getboard(int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) {
            int view = optional.get().getBview();
            optional.get().setBview(view+1);
            return optional.get().toDto();
        }
        return null;
    }
    @Transactional
    public boolean updateboard(BoardDto dto){
        Optional<BoardEntity> optional = boardRepository.findById(dto.getBno());
        if(optional.isPresent()) {
            BoardEntity entity = optional.get();
            entity.setBtitle(dto.getBtitle());
            entity.setBcontent(dto.getBcontent());
            entity.setBfile(dto.getBfile());
            boardRepository.save(entity);
            return true;
        }
        return false;
    }
    @Transactional
    public boolean deleteboard( int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if(optional.isPresent()) {
            BoardEntity entity = optional.get();
            boardRepository.delete(entity);
            return true;
        }
        return false;
    }
}
