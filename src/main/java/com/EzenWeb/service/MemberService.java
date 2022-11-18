package com.EzenWeb.service;

import com.EzenWeb.domain.Dto.memberDto;
import com.EzenWeb.domain.entity.MemberEntity;
import com.EzenWeb.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    JavaMailSender javaMailSender;
    public int mno(){
        return (Integer) request.getSession().getAttribute("loginMno");
    }
    public int setmember(memberDto Dto) {
        MemberEntity entity = memberRepository.save(Dto.toEntity());
        return entity.getMno();
    }

    public int getmember(memberDto dto) {
        List<MemberEntity> entityList = memberRepository.findAll();
        for (MemberEntity entity : entityList) {
            if(entity.getMemail().equals(dto.getMemail())){
                if(entity.getMpassword().equals(dto.getMpassword())){
                    request.getSession().setAttribute("loginMno" , entity.getMno());
                    return 1;
                }
            }
        }
        return 0;
    }
    public Resource logout() {
        request.getSession().setAttribute("loginMno",null);
        return new ClassPathResource("templates/index.html");
    }
    public String findpassword(String memail) {
        int mno = (Integer) request.getSession().getAttribute("loginMno");
        List<MemberEntity> entityList = memberRepository.findAll();
        for (MemberEntity entity : entityList) {
            if(entity.getMemail().equals(memail) && entity.getMno()==mno){
                return entity.getMpassword();
            }
        }
        return null;
    }
    @Transactional
    public String updatepassword(String updatepassword) {
        int mno = (Integer) request.getSession().getAttribute("loginMno");
        if (mno > 0) {
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            if (optional.isPresent()) {
                MemberEntity entity = optional.get();
                entity.setMpassword(updatepassword);
                memberRepository.save(entity);
                return "변경";
            }
        }
        return null;
    }
    @Transactional
    public String setdelete(String mpassword) {
        int mno = (Integer) request.getSession().getAttribute("loginMno");
        if(mno>0){
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            if(optional.isPresent()){
                MemberEntity entity = optional.get();
                memberRepository.delete(entity);
                request.getSession().setAttribute("loginMno",null);
                return "삭제완료";
            }
        }
        return "삭제못함";
    }

    public List<memberDto> list() {
        List<MemberEntity> list = memberRepository.findAll();
        List<memberDto> listDto = new ArrayList<>();
        for(MemberEntity entity : list){
            listDto.add(entity.toDto());
        }
        return listDto;
    }
    public String getauth(String toemail){
        String auth = "";
        String html = "<html><body><h1>회원가입 이메일 인증코드입니다.</h1>";
        Random random = new Random();
        for(int i=0; i<6; i++){
            char randomchar = (char) (random.nextInt(26)+97);
            //char randomchar = random.nextInt(10)+48;
            auth+=randomchar;
        }
        html += "<div>인증코드: "+auth+"</div></body></html>";
        mailsend(toemail, "인증코드" , html);
        return auth;
    }
    public void mailsend(String email , String title , String content){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom("ehehwhdwhd@naver.com", "김도현");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content.toString(), true);
            javaMailSender.send(message);
        }
        catch (Exception e) {
            System.out.println("전송실패"+e);
        }
    }


}
