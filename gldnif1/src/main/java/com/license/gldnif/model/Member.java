package com.board.gldnif.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private int m_id;
    private String id;
    private String passwd;
    private String name;
    private String address;
    private String profile_img;
}
