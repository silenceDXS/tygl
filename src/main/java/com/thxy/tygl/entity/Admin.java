package com.thxy.tygl.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Data
public class Admin {
    private  int adminId;
    private  String name;
    private  String adminAccount;
    private String adminPassword;
    private Timestamp lastTime;


}
