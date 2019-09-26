package com.lottoland.rps.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Welcome")
@RestController
@RequestMapping("/api/v1/Welcome")
public class PresentationController {

    @GetMapping
    public String sayWelcome() {
        return "Welcome to rock-paper-scissors. My name is Antonio(COMPUTER). You will have two levels of difficult in this game";
    }

}
