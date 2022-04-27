package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private String username;
    private String password;
    private String telNumber;
    private String address;
    private String ID;
    private String name;
    private String email;
    private String createTime;
    private int status;
    private Roles roles;
}
