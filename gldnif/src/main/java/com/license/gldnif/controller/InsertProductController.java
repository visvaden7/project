package com.license.gldnif.controller;

import com.license.gldnif.model.ProductInfo;
import com.license.gldnif.service.InsertProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class InsertProductController {

    @Resource
    InsertProductService insertProductService;

    @RequestMapping("/insertOrder")
    public String insertOrder(ProductInfo vo, HttpSession session) throws Exception{
        System.out.println(vo + "1234");
        String path = "redirect:/insertOrder.html";
        System.out.println(session.getId());
        if(session.getId() != null && vo.getOrderNo() != null && vo.getDeliveryDate() != null && vo.getTaxInvoice() != null){
            insertProductService.insertOrder(vo);
            System.out.println("hello Order");
        }
        return path;
    }
    //추후 session 값에 따라 처리기능 추가


    @RequestMapping("/insertDelivery")
    public String insertDelivery(ProductInfo vo, HttpSession session) throws Exception {
        vo.setHp(vo.getPhoneNo1() + vo.getPhoneNo2() + vo.getPhoneNo3());
        System.out.println(vo + "1234");
        String path = "redirect:/insertFormat.html";
        if(vo.getOrderNo() != null){
            insertProductService.insertDelivery(vo);
            System.out.println("hello Delivery");
        }
        return path;
    }
    //추후 session 값에 따라 처리기능 추가

    @RequestMapping("/insertQc")
    public String insertQc(ProductInfo vo, HttpSession session) throws Exception{
        System.out.println(vo + "1234");
        String path = "redirect:/insertFormat.html";
        if(vo.getProductNo() != null){
            insertProductService.insertQc(vo);
        }
        return path;
    }
    //추후 session 값에 따라 처리기능 추가

    @RequestMapping("/insertProduct")
    public String insertProduct(ProductInfo vo, HttpSession session) throws Exception{
        System.out.println(vo + "1234");
        System.out.println(vo.getReceiptDate());
        Integer cnt = vo.getCount();
        if(cnt != null){
            System.out.println(cnt);
            if(vo.getReceiptDate() != null){
                for(int i = 0; i < cnt; i++ ){
                    System.out.println(vo);
                    insertProductService.insertPrdouct(vo);
                }
            }
        }
        return "redirect:/insertFormat.html";
    }
}
