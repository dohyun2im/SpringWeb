package com.EzenWeb.controller.test;

import com.EzenWeb.domain.Dto.memberDto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    @PutMapping("/member")
    public String putMember(@RequestBody Map<String,String> putdata) {
        return putdata.toString();
    }

    @PutMapping("/member1")
    public String putMember1(@RequestBody memberDto dto) {
     return dto.toString();
    }

    @PutMapping("/member2")
    public memberDto putMember2(@RequestBody memberDto dto) {
        return dto;
    }
}
