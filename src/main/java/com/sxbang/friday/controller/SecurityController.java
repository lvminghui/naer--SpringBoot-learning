package com.sxbang.friday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(value = "/login.html")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/403.html")
    public String noPermission() {
        return "403";
    }
}
