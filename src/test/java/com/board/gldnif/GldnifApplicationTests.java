package com.board.gldnif;

import com.board.gldnif.model.Member;
import com.board.gldnif.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GldnifApplicationTests {

    @Resource
    MemberService memberService;

    @Test
    void login() throws Exception {
        Member member = new Member();

        member.toString();
        member.setId("1234");
        member.setPassword("1234");
        System.out.println(member);

        memberService.login(member);
        System.out.println(member.getName());
        System.out.println(member.getId());
    }

}
