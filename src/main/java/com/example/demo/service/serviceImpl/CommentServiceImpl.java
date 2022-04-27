package com.example.demo.service.serviceImpl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Order;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    @Transactional
    public int AddComment(Comment comment) {
        comment.setCmid(UUID.randomUUID().toString());
        comment.setUid(StpUtil.getLoginId().toString());
        comment.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date()));
        Order order=new Order();
        order.setOid(comment.getOid());
        order.setStatus(3);
        orderMapper.updateOrder(order);
        return commentMapper.AddComment(comment);
    }

    @Override
    public List<Map> selectComment(Map map) {
        return commentMapper.selectComment(map);
    }
}
