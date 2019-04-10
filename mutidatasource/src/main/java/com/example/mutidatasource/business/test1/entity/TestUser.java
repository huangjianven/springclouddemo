package com.example.mutidatasource.business.test1.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
/**
 * (TestUser)表实体类
 *
 * @author 黄建文
 * @since 2019-04-08 19:51:01
 */
@TableName("test_user")
@Data
public class TestUser extends Model<TestUser> {
        
    @TableId
    private Integer id;
        
    private String name;
        
    private String password;

}