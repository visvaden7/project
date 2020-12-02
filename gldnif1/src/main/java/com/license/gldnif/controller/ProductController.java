package com.board.gldnif.controller;

import com.board.gldnif.model.ProductInfo;
import com.board.gldnif.service.ProductService;
import com.board.gldnif.service.SaveExel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Controller
public class ProductController {

    @Resource
    ProductService productService;

    @RequestMapping("/selectAll")
    public ModelAndView selectAll(HttpSession session) throws Exception{
        log.info("ok!!");
        ModelAndView mv = new ModelAndView("redirect:/index.html");
        log.info("ok!!");
        if(session.getAttribute("mvo") != null){
            List<ProductInfo> list = productService.selectAll();
            mv.setViewName("productInformation");
            mv.addObject("list", list );
            log.info(list.toString());
        }
        return mv;

    }

    @GetMapping("/saveExel.csv")
    public void SaveExel(HttpSession session,HttpServletResponse response) throws Exception {
        log.info("mvo:: " + session.getAttribute("mvo"));
        if(session.getAttribute("mvo") != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd:MM");
            log.info("hello,It's ok");
            String savetime = format.format(System.currentTimeMillis());
            response.setContentType("txt/csv; charset=MS949");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + savetime + ".csv;");
            log.info("attachment file=" + savetime + ".csv");
            SaveExel.downloadExel(response.getWriter(), productService.selectAll());
        }
    }

    @GetMapping("/main")
    public String Back(HttpSession session){
        session.getAttribute("mvo");
        log.info("mvo:: " +session.getAttribute("mvo"));
        String path = "redirect:/index.html";
        if(session.getAttribute("mvo") != null){
            path = "loginResult";
            log.info("path:: " + path);
        }
        log.info("hello");
        return path;
    }
}
