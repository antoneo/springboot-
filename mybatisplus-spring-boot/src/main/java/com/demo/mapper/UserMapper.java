package com.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.demo.SuperMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.annotations.SqlParser;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    /**
     * 注解 @SqlParser(filter = true) 过滤多租户解析
     */
    @SqlParser(filter = true)
    @Select("select  id, username, password from sys_user")
    List<User> selectListBySQL();

}