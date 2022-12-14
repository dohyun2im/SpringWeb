package com.EzenWeb.controller;

import com.EzenWeb.domain.Dto.memberDto;
import com.EzenWeb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/member")
public class MemberController {
   @Autowired
   private MemberService memberService;
   /* @GetMapping("/")
    public Resource hello(){
        return new ClassPathResource("templates/index.html");
    }
   @GetMapping("/signup")
   public Resource signup(){
      return new ClassPathResource("templates/member/signUp.html");
   }
    @GetMapping("/login")
    public Resource login(){
        return new ClassPathResource("templates/member/login.html");
    }

    @GetMapping("/findpassword")
    public Resource findPassword(){
       return new ClassPathResource("templates/member/findpassword.html");
   }
    @GetMapping("/updatepw")
    public Resource updatepassword(){
        return new ClassPathResource("templates/member/updatePassword.html");
    }
    @GetMapping("/setdelete")
    public Resource setdelete(){
        return new ClassPathResource("templates/member/setdelete.html");
    }
    @GetMapping("/logout")
    public Resource logout(){
        return memberService.logout();
    }*/

    @PostMapping("/setmember")
    public int setmember(@RequestBody memberDto dto) {
       int result = memberService.setmember(dto);
        return result;
    }

    @GetMapping("/getloginMno")
    public String getloginMno() {
        return memberService.getloginMno();
    }

    @PostMapping("/getPassword")
    public String findpassword(@RequestParam String memail) {
        return memberService.findpassword(memail);
    }
    @PutMapping("/updatepassword")
    public String updatepw(@RequestParam String updatepassword) {
        return memberService.updatepassword(updatepassword);
    }
    @PostMapping("/delete")
    public String delete(@RequestParam String mpassword) {
        return memberService.setdelete(mpassword);
    }
    /*@GetMapping("/mno")
    public int mno() {
        return memberService.mno();
    }*/
    @GetMapping("/list")
    public List<memberDto> list() {
        return memberService.list();
    }
    @GetMapping("/getauth")
    public String getauth(@RequestParam String memail) {
        return memberService.getauth(memail);
    }
}
