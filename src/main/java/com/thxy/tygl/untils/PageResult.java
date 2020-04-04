package com.thxy.tygl.untils;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;



public class PageResult implements Serializable {
    //查找框的内容
    private String inputParam;

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    //在查找的时候做标记
    private int findParam;

    public int getFindParam() {
        return findParam;
    }

    public void setFindParam(int findParam) {
        this.findParam = findParam;
    }

    //总记录数  总共多少条数据
    private int totalCount;

    //每页记录数  每页多少条数据
    private int pageSize;

    //总页数  总页数
    private int totalPage;

    //数据库当前页数的起始数
    private int currPage;

    //页面当前页数
    private  int nowPage;
    public  PageResult(){};

    public PageResult(int totalCount, int pageSize, int nowPage) {
        this.totalCount = totalCount; //数据库总记录数
        this.pageSize = pageSize;  //每页几条
        this.currPage = (nowPage-1)*pageSize;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize); //计算总页数
        this.nowPage=nowPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }
}
