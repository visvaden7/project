package com.license.gldnif.service;


import com.license.gldnif.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface MemberService {
    @Select("select id, passwd, name, address, profile_img from member where id = #{id} AND passwd = #{passwd}")
    Member login(Member vo) throws Exception;

    @Insert("INSERT INTO member (id,passwd,name,address) VALUES (#{id}, #{passwd}, #{name}, #{address})")
    void signUp(Member vo) throws Exception;
}
