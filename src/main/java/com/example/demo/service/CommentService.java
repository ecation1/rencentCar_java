package com.example.demo.service;

import com.example.demo.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int AddComment(Comment comment);
    List<Map> selectComment(Map map);
}
