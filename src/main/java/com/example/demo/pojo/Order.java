package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String oid;
    private String orderStartTime;
    private String orderEndTime;
    private int days;
    private int allPrice;
    private int status;
    private String name;
    private String ID;
    private String telNumber;
    private String cid;
    private String uid;
}
