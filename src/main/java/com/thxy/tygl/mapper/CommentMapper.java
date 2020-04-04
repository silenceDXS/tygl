package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.Comment;
import com.thxy.tygl.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    //分页查询
    List<Comment> getAllCommentForPage(@Param("currPage") int nowPage, @Param("pageSize") int pageSize,@Param("circleId") int commentId);
    int getCountComment();
    //查找用户
    List<Comment> findCommentByParam(@Param("param") Map<String,Object> map);

    //查找到的用户总量
    int countFindComment(@Param("userId") int param);
    //根据学号删除账号信息
    int delCommentByCommentId(@Param("commentId") int param);
}
