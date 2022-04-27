package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String cmid;
    private String c_comment;
    private String uid;
    private String cid;
    private String oid;
    private int stars;
    private String createTime;
}
