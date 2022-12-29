package com.EzenWeb.controller;

import com.EzenWeb.domain.Dto.RoomDto;
import com.EzenWeb.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @PostMapping("/setRoom")
    public boolean write(RoomDto roomDto) {
        System.out.println(roomDto.toString());
        return roomService.write();
    }
}
