package com.example.demo.mapper;

import com.example.demo.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {
    int AddComment(Comment comment);
    List<Map> selectComment(Map map);
}
