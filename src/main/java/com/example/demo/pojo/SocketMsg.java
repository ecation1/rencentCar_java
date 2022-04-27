package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: CYW
 * @Date: 2021-04-26 10:55
 * @Description: 消息实体
 * @Program: springboot_websocket
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketMsg {
    private int type; //聊天类型0：群聊，1：单聊.
    private String fromUser;//发送者.
    private String toUser;//接受者.
    private String msg;//消息
}

