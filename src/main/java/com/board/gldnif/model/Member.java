package com.board.gldnif.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {
    private String id;
    private String password;
    private String name;
    private String address;
}
