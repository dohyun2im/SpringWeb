package com.EzenWeb.service;

import com.EzenWeb.domain.Dto.BcategoryDto;
import com.EzenWeb.domain.Dto.BoardDto;
import com.EzenWeb.domain.Dto.VisitDto;
import com.EzenWeb.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BcategoryRepository bcategoryRepository;
    @Autowired
    private VisitRepository visitRepository;
    String path = "C:\\Users\\504\\Desktop\\SpringWeb\\src\\main\\resources\\static\\bupload\\";

    public void filedownload(String filename){
        String realfile = filename.split("_")[1];
        String filepath = path+filename;
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realfile, "UTF-8"));
            File file = new File(filepath);
            BufferedInputStream InputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[(int)file.length()];
            InputStream.read(bytes);

            BufferedOutputStream OutputStream = new BufferedOutputStream(response.getOutputStream());
            OutputStream.write(bytes);

            OutputStream.flush();
            InputStream.close();
            OutputStream.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    @Transactional
    public boolean setcategory(BcategoryDto dto){
        List<BcategoryEntity> bcategoryEntityList = bcategoryRepository.findAll();
        for (BcategoryEntity bcategoryEntity : bcategoryEntityList) {
            if(bcategoryEntity.getCategory().equals(dto.getCategory())) {
                return false;
            }
        }
        BcategoryEntity Entity = bcategoryRepository.save(dto.toEntity());
        if(Entity.getCno()>0) {
            return true;
        }
        return false;
    }

    @Transactional
    public List<BcategoryDto> getcategory(){
        List<BcategoryDto> DtoList = new ArrayList<>();
        List<BcategoryEntity> EntityList = bcategoryRepository.findAll();
        for (BcategoryEntity bcategoryEntity : EntityList) {
           DtoList.add(bcategoryEntity.toDto());
        }
        return DtoList;
    }

    @Transactional
    public boolean setboard(BoardDto dto) {
        Object  o = request.getSession().getAttribute("loginMno");
        if(o==null) return false;

        Optional<MemberEntity> optional =  memberRepository.findById((Integer)o);
        Optional<BcategoryEntity> optional2 = bcategoryRepository.findById(dto.getCno());

        if (!optional.isPresent() || !optional2.isPresent())return false;
        MemberEntity memberEntity =  optional.get();
        BcategoryEntity bcategoryEntity = optional2.get();
        BoardEntity entity = boardRepository.save(dto.toEntity());

        if (entity.getBno() > 0) {
            entity.setMemberEntity(memberEntity);
            entity.setBcategoryEntity(bcategoryEntity);

            String filename = UUID.randomUUID().toString()+"_"+dto.getBfile().getOriginalFilename();

            entity.setBfile(filename);
            try {
                dto.getBfile().transferTo(new File(path + filename));
            }
            catch (Exception e){
                System.out.println(e);
            }

            memberEntity.getBoardlist().add(entity);
            bcategoryEntity.getBoardEntityList().add(entity);
            return true;

        }
        return false;
    }
    @Transactional
    public List<BoardDto> getboards(int cno) {
        List<BoardEntity> entities ; //= boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();
        if(cno == 0 ){
            entities = boardRepository.findAll();
            for (BoardEntity entity : entities){
                dtos.add(entity.toDto());
            }
        }
        else{
            BcategoryEntity bcEntity = bcategoryRepository.findById(cno).get();
            entities = bcEntity.getBoardEntityList();
            for (BoardEntity entity : entities) {
                dtos.add(entity.toDto());
            }
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
    @Transactional
    public boolean setvisit(VisitDto dto){
        Optional<BcategoryEntity> optional = bcategoryRepository.findById(dto.getCno());
        if(optional.isPresent()) {
            VisitEntity entity = visitRepository.save(dto.toEntity());
            if(entity.getVno()>0){
                entity.setBcategoryEntity(optional.get());
                optional.get().getVisitEntityList().add(entity);
            }
        }
        return false;
    }
    @Transactional
    public List<VisitDto> getvisit(int cno){
        List<VisitEntity> Entities ;
        if(cno==0){
            Entities = visitRepository.findAll();
        }
        else {
            Entities  = bcategoryRepository.findById(cno).get().getVisitEntityList();
        }
        List<VisitDto> dtolist = new ArrayList<>();
        for(VisitEntity entity : Entities){
            dtolist.add(entity.toDto());
        }
        return dtolist;
    }
}
