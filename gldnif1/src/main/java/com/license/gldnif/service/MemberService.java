package com.board.gldnif.service;


import com.board.gldnif.model.Member;
import org.apache.ibatis.annotations.Select;


public interface MemberService {
    @Select("select id, passwd, name, address, profile_img from member where id = #{id} AND passwd = #{passwd}")
    Member login(Member vo) throws Exception;
}
