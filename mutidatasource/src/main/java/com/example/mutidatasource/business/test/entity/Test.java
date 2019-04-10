package com.example.mutidatasource.business.test.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (Test)表实体类
 *
 * @author 黄建文
 * @since 2019-04-08 19:04:40
 */
@TableName("test")
@Data
@ApiModel
public class Test extends Model<Test> {
        
    @TableId
    @ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户密码")
    private String password;

}