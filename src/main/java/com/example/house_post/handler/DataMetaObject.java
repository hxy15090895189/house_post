package com.example.house_post.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class DataMetaObject implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("isdel",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
