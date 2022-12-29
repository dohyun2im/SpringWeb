package com.EzenWeb.service;

import com.EzenWeb.domain.Dto.*;
import com.EzenWeb.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
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
    String path = "C:\\";

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
    public boolean fileupload(BoardDto dto , BoardEntity entity){
        if(dto.getBfile() != null) {
            String filename = UUID.randomUUID().toString() + "_" + dto.getBfile().getOriginalFilename();

            entity.setBfile(filename);
            try {
                dto.getBfile().transferTo(new File(path + filename));
                return true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
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
        Object  o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(o==null) return false;
        memberDto mdto = (memberDto) o;
        System.out.println(mdto);
        //Optional<MemberEntity> optional =  memberRepository.findById((Integer)o);
        Optional<MemberEntity> optional = memberRepository.findbymemail(mdto.getMemail());
        Optional<BcategoryEntity> optional2 = bcategoryRepository.findById(dto.getCno());

        if (!optional.isPresent() || !optional2.isPresent())return false;
        MemberEntity memberEntity =  optional.get();
        BcategoryEntity bcategoryEntity = optional2.get();
        BoardEntity entity = boardRepository.save(dto.toEntity());

        if (entity.getBno() > 0) {
            entity.setMemberEntity(memberEntity);
            entity.setBcategoryEntity(bcategoryEntity);

            fileupload(dto, entity);

            memberEntity.getBoardlist().add(entity);
            bcategoryEntity.getBoardEntityList().add(entity);
            return true;

        }
        return false;
    }
    @Transactional
    public PageDto getboards(PageDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage()-1,5, Sort.by(Sort.Direction.DESC,"bno"));
        Page<BoardEntity> entities = boardRepository.findBySearch(dto.getCno(),dto.getKey(), dto.getKeyword(),pageable);
        int btncount =5;
        int startbtn = (dto.getPage()/btncount)*btncount+1;
        int endbtn = startbtn+btncount-1;
        if(endbtn>entities.getTotalPages()){endbtn=entities.getTotalPages();}

        List<BoardDto> blist = new ArrayList<>();
        for (BoardEntity entity : entities) {
            blist.add(entity.toDto());
        }

        dto.setList(blist);
        dto.setStartbtn(startbtn);
        dto.setEndbtn(endbtn);
        dto.setTotalBoards(entities.getTotalElements());
        System.out.println(dto.toString());
        return dto;
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


            //받아온 파일이 있다면?
            if(!dto.getBfile().getOriginalFilename().equals("")) {
                //기존 게시물에 파일이 있다면?
                if (entity.getBfile() != null) {
                    File file = new File(path + entity.getBfile());
                    //삭제처리
                    if (file.exists()) {file.delete();}
                }
                //저장
                fileupload(dto, entity);
            }

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
            if (entity.getBfile() != null) {
                File file = new File(path + entity.getBfile());
                if (file.exists()) {
                    file.delete();
                }
            }
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
