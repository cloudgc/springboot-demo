package com.example.demo.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright @ 2018/12/26
 *
 * @author cloudgc
 * @since 0.0.1
 */
@RestController
public class Api {


    @PostMapping
    public String name(String name) {
        return "echo ...." + name;
    }


}
