package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class License {
    private String lid;//驾照信息id
    private String licenseId;//驾照号
    private String licensePic;//驾照图片
    private String uid;//用户id
    private int status;
}
