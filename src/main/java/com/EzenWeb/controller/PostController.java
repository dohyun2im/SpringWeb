package com.EzenWeb.controller;

import com.EzenWeb.domain.Dto.memberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
    @RequestMapping(value = "/domain" , method = RequestMethod.POST)
    public String PostExample() {
        return "Hello PostExample";
    }
    @PostMapping("/member")
    public String PostMember(@RequestBody Map<String, String> postdata) {
        return postdata.toString();
    }
    @PostMapping("/member2")
    public String PostMemberDto(@RequestBody memberDto dto) {
        return dto.toString();
    }
}
