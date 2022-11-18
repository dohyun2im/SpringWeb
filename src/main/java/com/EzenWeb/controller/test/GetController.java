package com.EzenWeb.controller.test;

import com.EzenWeb.domain.Dto.memberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
    @RequestMapping(value="/hello" , method = RequestMethod.GET)
    public static String getHello(){
        return  "Hello  World!";
    }

    @GetMapping("/name")
    public String getName(){
        return "Flature";
    }
    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable(value="variable") String test){
        return test;
    }

    @GetMapping("/variable3")
    public String getVariable3(@RequestParam String variable){
        return variable;
    }

    @GetMapping("/request")
    public String request(@RequestParam String a  , @RequestParam String b , @RequestParam String c ){
        return a+" "+b+" "+c;
    }
    @GetMapping("/request2")
    public String request2(@RequestParam Map<String,String> a){
        return a.toString();
    }

    @GetMapping("/request3")
    public String request3(memberDto dto){
        return dto.toString();
    }
}
