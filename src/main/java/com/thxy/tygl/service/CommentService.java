package com.thxy.tygl.service;

import com.thxy.tygl.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public Map<String,Object> findAllComment(int nowPage, int pageSize,int CommentId);
    int getTotal();
    public List<Comment> findCommentByParam(int nowPage, int pageSize, String param);
    public  int  countFindComment( String StudentId);
    int delCommentByCommentId( int param);
}
