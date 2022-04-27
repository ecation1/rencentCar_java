package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private String sid;
    private String shopName;
    private String address;
    private String detail;
    private String uid;
    private float lat;
    private float lng;
}
