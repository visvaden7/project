package com.license.gldnif.controller;

import com.license.gldnif.model.ProductInfo;
import com.license.gldnif.service.ProductService;
import com.license.gldnif.service.SaveExel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
            List<ProductInfo> list1 = productService.selectOrder();
            List<ProductInfo> list2 = productService.selectProduct();
//            mv.setViewName("productInformation");
            mv.setViewName("test/test");
            mv.addObject("list", list );
            //진행중인 주문목록 가져오기
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list1.size(); j++){
                    if(list.get(i).getOrderNo().equals(list1.get(j).getOrderNo())){
                        list1.remove(j);
                    };
                }
            }
            //제품목록 가져오기
            for(int i = 0; i < list2.size(); i++){
                for(int j = 0; j < list2.size(); j++){
                    if(list2.get(i).getProductNo().equals(list2.get(j).getProductNo())){
//                        System.out.println(list2.get(i).getQcId());
                        if(list2.get(i).getQcId() > list2.get(j).getQcId()){
                            list2.remove(j);
                        }
                    };
                }
            }
            mv.addObject("list1", list1);
            mv.addObject("list2", list2);
            mv.addObject("session",session);
            log.info(list.toString());
        }
        return mv;

    }

    @GetMapping("/saveExel.csv")
    public void SaveExel(HttpSession session,HttpServletResponse response) throws Exception {
        log.info("mvo:: " + session.getAttribute("mvo"));
        if(session.getAttribute("mvo") != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            log.info("hello,It's ok");
            String savetime = format.format(System.currentTimeMillis());
            response.setContentType("txt/csv; charset=MS949");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + savetime + ".csv;");
            log.info("attachment file=" + savetime + ".csv");
            SaveExel.downloadExel   (response.getWriter(), productService.selectAll());
        }
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(ProductInfo vo, HttpSession session) throws Exception{
        if(vo.getLockeyNo().equals("")) vo.setLockeyNo(null);
        System.out.println(vo);
        if(vo.getLockeyNo() != null){
            productService.updateProduct(vo);
            System.out.println(vo);
        }
        return "redirect:/insertFormat.html";
    }

    //추후 session 값에 따라 처리기능 추가

    @RequestMapping("/upload")
    public String upload(MultipartHttpServletRequest mre, HttpServletRequest req) throws Exception {
        // HttpServletRequest req 인자로 했을 시
        String test = req.getParameter("deliveryRequestNo"); // jsp text name mapping
        MultipartFile mf = mre.getFile("filepath"); // jsp file name mapping
        String uploadPath = "/Users/chang/Desktop/";
        System.out.println("hello");
        System.out.println(mf);
        String path = uploadPath; // 파일 업로드 경로
        assert mf != null : "error";
        String original = mf.getOriginalFilename(); // 업로드하는 파일 name
        System.out.println(path);
        System.out.println("!!!!!!!!!!" + original);    // file original name
        System.out.println("!!!!!!!!!!" + mf.getSize());// file size
        System.out.println(test);
        if(mf.isEmpty()){
        }else if(test == null){
            return "redirect:/insertFormat.html";
        }
        uploadPath = (String)(path + original); // 파일 업로드 경로 + 파일 이름

        System.out.println(uploadPath);
        System.out.println(test);
        productService.upload(uploadPath,test);

        mf.transferTo(new File(uploadPath)); // 파일을 위에 지정 경로로 업로드

        return "redirect:/insertFormat.html";
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
