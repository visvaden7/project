package com.board.gldnif.service;


import com.board.gldnif.model.Member;
import org.apache.ibatis.annotations.Select;


public interface MemberService {
    @Select("select id, password, name, address from member where id = #{id} AND password = #{password}")
    Member login(Member vo) throws Exception;
}
