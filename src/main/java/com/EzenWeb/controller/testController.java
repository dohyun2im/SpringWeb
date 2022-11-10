package com.EzenWeb.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class testController {
    @GetMapping("/")
    public Resource gettext() {
        return new ClassPathResource("templates/test.html");
    }
}
