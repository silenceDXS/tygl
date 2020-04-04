package com.thxy.tygl.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
@Data
public class NoticeJson {

    private String title;
    private String noticeType;
    private  String content;
    private String author;
    private MultipartFile[] pic;

}
