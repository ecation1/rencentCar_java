package com.example.demo.controller;

import cn.dev33.satoken.util.SaResult;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Order;
import com.example.demo.service.CommentService;
import com.example.demo.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comm")
public class CommController {
    @Autowired
    CommentService commentService;
    @RequestMapping("addComm")
    public SaResult AddComment(@RequestBody Comment comment){
        int i = commentService.AddComment(comment);
        if(i==0){
            return SaResult.error("新增失败");
        }else {
            return SaResult.ok("新增成功");
        }
    }
    @RequestMapping("getComm")
    public SaResult selectComment(@RequestBody Map map){
        List<Map> maps = commentService.selectComment(map);
        return SaResult.get(200,"查询成功",maps);
    }
}
