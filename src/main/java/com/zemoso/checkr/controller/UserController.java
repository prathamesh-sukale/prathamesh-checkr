package com.zemoso.checkr.controller;

import com.zemoso.checkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    public String test(){
        return  userService.getTestData();
    }
}
