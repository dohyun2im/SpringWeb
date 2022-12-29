package com.EzenWeb.service;

import com.EzenWeb.domain.entity.MemberEntity;
import com.EzenWeb.domain.entity.MemberRepository;
import com.EzenWeb.domain.entity.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class RoomService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public boolean write(){
        String email =memberService.getloginMno();
        if(email == null)return false;
        MemberEntity memberEntity = memberRepository.findbymemail(email).get();

        return true;
    }
}
