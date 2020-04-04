package com.thxy.tygl.entity;

import lombok.Data;

@Data
public class Comment {
    private int  commentId;
    private int circleId;
    private int userId;
    private String content;
    private String createTime;
    private int active;
}
