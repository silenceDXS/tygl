package com.thxy.tygl.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Circle {
    private int circleId;
    private int creatorId;
    private String content;
    private int like;
    private String createTime;
    private int active;


}
