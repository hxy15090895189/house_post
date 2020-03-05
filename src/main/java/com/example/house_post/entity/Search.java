package com.example.house_post.entity;

import lombok.Data;

@Data
public class Search {
    private String title; //标题
    private Integer did;  //区域编号
    private Integer sid; //街道编号
    private Integer tid; //类型编号
    private Long startPrice; //开始价格
    private Long endPrice; //结束价格
}
