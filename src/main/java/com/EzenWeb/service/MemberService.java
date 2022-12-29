package com.EzenWeb.service;

import com.EzenWeb.domain.Dto.OAuthDto;
import com.EzenWeb.domain.Dto.memberDto;
import com.EzenWeb.domain.entity.MemberEntity;
import com.EzenWeb.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class MemberService implements UserDetailsService , OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String oauth2UserInfo = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthDto dto = OAuthDto.of(registrationId,oauth2UserInfo,oAuth2User.getAttributes());

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("kakaoUser"));

       Optional<MemberEntity> optional = memberRepository.findbymemail(dto.getMemail());
       MemberEntity memberEntity;
       if (optional.isPresent()){//기존회원
           memberEntity = optional.get();
       }
       else{//기존회원아님
           memberEntity = memberRepository.save(dto.toEntity());
       }

        memberDto mdto = new memberDto();
            mdto.setMemail(memberEntity.getMemail());
            mdto.setAuthoritySet(authorities);
            mdto.setAttributes(dto.getAttributes());
        return mdto;
    }
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {
        System.out.println(memail);
        MemberEntity entity = memberRepository.findbymemail(memail)
                .orElseThrow(()-> new UsernameNotFoundException("사용자가 존재하지 않습니다."));

        Set<GrantedAuthority> authoritySet = new HashSet<>();
        authoritySet.add(new SimpleGrantedAuthority(entity.getRole()));
        memberDto dto = entity.toDto();
        dto.setAuthoritySet(authoritySet);
        return dto;
    }
    /*public int mno(){
            return (Integer) request.getSession().getAttribute("loginMno");
        }*/
    @Transactional
    public int setmember(memberDto Dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Dto.setMpassword(passwordEncoder.encode(Dto.getPassword()));
        MemberEntity entity = memberRepository.save(Dto.toEntity());
        entity.setRole("Role_Member");
        return entity.getMno();
    }

    @Transactional
    public String getloginMno(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal.equals("anonymousUser")){
            return null;
        }else{
            memberDto dto = (memberDto) principal;
            return dto.getMemail()+"_"+dto.getAuthorities();
        }
    }




    public Resource logout() {
        request.getSession().setAttribute("loginMno",null);
        return new ClassPathResource("templates/index.html");
    }
    @Transactional
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
    @Transactional
    public List<memberDto> list() {
        List<MemberEntity> list = memberRepository.findAll();
        List<memberDto> listDto = new ArrayList<>();
        for(MemberEntity entity : list){
            listDto.add(entity.toDto());
        }
        return listDto;
    }
    @Transactional
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
    @Transactional
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
