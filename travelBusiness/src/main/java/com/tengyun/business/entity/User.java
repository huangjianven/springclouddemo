package com.tengyun.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;
/**
 * 用户表(User)表实体类
 *
 * @author 黄建文
 * @since 2019-06-18 14:23:08
 */
@TableName("user")
@Data
public class User extends Model<User> {
        
    @TableId(type = IdType.UUID)
    private String id;
        
    private String openId;
        
    private String phone;
        
    private String name;
        
    private String password;
        
    private String companyaccount;
    /**
    * 用户状态（1、正常，0、删除）
    */    
    private Integer state;
    /**
    * 用户类型（1、个人，0、秘书）
    */    
    private Integer type;
        
    private String parid;
        
    private String remark;
        
    private Date createTime;
        
    private Date updateTime;
        
    private String createBy;
        
    private String updateBy;
        
    private String account;

    @TableField(exist = false)
    private Company company;

}