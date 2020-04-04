package com.thxy.tygl.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Notice {
    private int noticeId;
    private String title;
    private Timestamp createTime;
    private String noticeType;
    private int active;
    private  String content;
    private String author;
    private String coverUrl;
    private String authorUrl;
    private Type type;
}
