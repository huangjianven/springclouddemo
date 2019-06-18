package com.tengyun.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.UserService;
import com.tengyun.data.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 用户表(User)表控制层
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:20
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 登陆
     *
     * @param code JSCODE
     * @return 新增结果
     */
    @RequestMapping("login")
    public void login(@RequestParam String code, HttpServletResponse response) throws IOException {
        this.userService.login(code, response);
    }

//    /**
//     * 绑定
//     *
//     * @param code 验证码
//     * @return 新增结果
//     */
//    @PostMapping("bind")
//    public ResponseDTO bind(@RequestParam String code) {
//        return this.userService.login(code);
//    }
//
//    /**
//     * 获取验证码
//     *
//     * @param phone 电话号码
//     * @return 新增结果
//     */
//    @PostMapping("verification")
//    public ResponseDTO verification(@RequestParam String phone) {
//        return this.userService.verification(phone);
//    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<IPage<User>> selectAll(Page<User> page, User user) {

        return Result.success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public Result<User> selectOne(@RequestParam Serializable id) {
        return Result.success(this.userService.getById(id));
    }
//
    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody User user) {
        return Result.success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        return Result.success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody User user) {
        user = this.userService.getById(user.getId());
        return Result.success(this.userService.updateById(user));
    }
}