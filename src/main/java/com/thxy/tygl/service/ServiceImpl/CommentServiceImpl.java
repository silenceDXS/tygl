package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Circle;
import com.thxy.tygl.entity.Comment;
import com.thxy.tygl.mapper.CommentMapper;
import com.thxy.tygl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Map<String, Object> findAllComment(int nowPage, int pageSize,int commentId) {
        //获取所有用户
        List<Comment> comments=commentMapper.getAllCommentForPage((nowPage-1)*pageSize,pageSize,commentId);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("comment",comments);
        return resultMap;
    }

    @Override
    public int getTotal() {
        return commentMapper.getCountComment();
    }

    @Override
    public List<Comment> findCommentByParam(int nowPage, int pageSize, String param) {
        int currPage=(nowPage-1)*pageSize;
        int studentId1=Integer.parseInt(param);
        Map<String,Object> map=new HashMap<>();
        map.put("currPage",currPage);
        map.put("pageSize",pageSize);
        map.put("userId",studentId1);
        List<Comment> result=commentMapper.findCommentByParam(map);
        return result;
    }

    @Override
    public int countFindComment(String param) {
        int param1=Integer.parseInt(param);
        int result=commentMapper.countFindComment(param1);
        return result;
    }

    @Override
    public int delCommentByCommentId(int param) {
        int result=commentMapper.delCommentByCommentId(param);
        return result;
    }
}
