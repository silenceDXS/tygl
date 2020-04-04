package com.thxy.tygl.service;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.NoticeJson;
import com.thxy.tygl.entity.Type;
import com.thxy.tygl.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    public Map<String,Object> findAllNotice(int nowPage, int pageSize);
    public  int getTotal();
    public List<Notice> findNoticeByTitle(int nowPage, int pageSize, String title);
    public  int  countFindNotice( String title);
    int delNoticeById(int param);
    int insertNotice(NoticeJson noticeJson);
    Notice findNoticeById(int noticeId);
    List<Type> getType();
}
