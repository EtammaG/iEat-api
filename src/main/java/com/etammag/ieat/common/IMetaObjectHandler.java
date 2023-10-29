package com.etammag.ieat.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.entity.details.Login;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用于MyBatis写进数据库前插入共有字段，需要配合Entity的属性上的注解使用
 *
 * @author Eiji
 */
@Component
@Slf4j
public class IMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Login principal = BaseContext.getCurrentLogin();
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", principal.getId());
        metaObject.setValue("updateUser", principal.getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Login principal = BaseContext.getCurrentLogin();
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", principal.getId());
    }
}