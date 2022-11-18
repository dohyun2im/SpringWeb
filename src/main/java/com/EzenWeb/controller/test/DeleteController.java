package com.EzenWeb.controller.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
    @DeleteMapping("/{variable}")
    public String deletevariable(@PathVariable("variable") String a) {
        return a;
    }
    @DeleteMapping("/request1")
    public String getRequestParam(@RequestParam String variable) {
        return variable;
    }
}
