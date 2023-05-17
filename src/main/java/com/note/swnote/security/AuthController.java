package com.note.swnote.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @GetMapping("/auth/login")
    public String loginPage() {
        return "/auth/login";
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public String loginDo() {
        return "login";
    }
}
