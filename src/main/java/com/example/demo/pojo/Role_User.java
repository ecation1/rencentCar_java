package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role_User {
    private String ruid;//用户角色id
    private String rid;//角色id
    private String uid;//用户idx
}
