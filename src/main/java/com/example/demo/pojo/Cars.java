package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cars {
    private String cid;
    private String carid;
    private String carPic;
    private String lisenPic;
    private String name;
    private String bid;
    private String vid;
    private int status;
    private int price;
    private int deposit;//押金
    private String keepTime;
    private String aiTime;
}
