package com.board.gldnif.controller;

import com.board.gldnif.model.Member;
import com.board.gldnif.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello Spring!!!";
    }

    @RequestMapping("login")
    public String login(Member pvo, HttpSession session) throws Exception {
        String path = "redirect:/";
        Member rvo = memberService.login(pvo);
        if(rvo!=null){
            session.setAttribute("mvo", rvo);
            System.out.println(session.getAttribute("mvo"));
            path = "loginResult";
        }
        System.out.println(path);
        return path;
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        if(session.getAttribute("mvo")!= null){
            System.out.println("mvo::" + session.getAttribute("mvo"));
            session.invalidate();
        }
        return "redirect:/";
    }
    @RequestMapping("viewTest")
    public String viewTest(Model model){
        model.addAttribute("message","Hello Spring Boot thymeleaf");
        return "view";
    }

}
