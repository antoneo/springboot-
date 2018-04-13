package com.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;


/**
 * 用户表
 */
@Data
@TableName("Sys_User")
public class User extends Model<User> implements Serializable{


    private static final long serialVersionUID = -2667032709578738270L;

    //自增
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;

    public User(){}

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

}
