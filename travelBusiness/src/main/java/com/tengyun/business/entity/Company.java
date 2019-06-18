package com.tengyun.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;
/**
 * 公司(Company)表实体类
 *
 * @author 黄建文
 * @since 2019-06-18 14:23:08
 */
@TableName("company")
@Data
public class Company extends Model<Company> {

    @TableId(type = IdType.UUID)
    private String id;
        
    private String name;
        
    private String account;
        
    private String appSecurity;
        
    private String token;
        
    private String remark;
        
    private Date createTime;
        
    private Date updateTime;
        
    private String createBy;
        
    private String updateBy;
        
    private Integer state;
        
    private String appKey;

    @TableField(exist = false)
    private Company company;

}