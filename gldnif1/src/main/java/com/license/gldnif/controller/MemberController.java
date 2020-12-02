package com.board.gldnif.controller;

import com.board.gldnif.model.Member;
import com.board.gldnif.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/hello")
    public String hello(){
        return "hello Spring!!!";
    }

    @RequestMapping("/login")
    public String login(Member vo, HttpSession session) throws Exception {
        String path = "redirect:/";
        Member rvo = memberService.login(vo);
        // * post 정보 잘 들어오는지 확인하기
//        log.info(vo.getId() + " hello!!!");
//        log.info(vo.getPasswd() + " hello!!!");
//        log.info(rvo.getProfile_img());
        if(rvo!=null){
            session.setAttribute("mvo", rvo);
            session.setMaxInactiveInterval(30*60);
        // * memberservice check용
//            System.out.println(session.getAttribute("mvo"));
//            log.info("hello" + session.getAttribute("mvo"));
            path = "loginResult";
        }
        return path;
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
            log.info("mvo:: " + session.getAttribute("mvo"));
        if(session.getAttribute("mvo") != null){
            System.out.println("mvo::" + session.getAttribute("mvo"));
            log.info("It's ok!!!");
            session.invalidate();
        }
        return "redirect:/index.html";
    }

}
