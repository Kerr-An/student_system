package com.kerr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping("/logout")
    // 首先让session里的信息失效
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

}
