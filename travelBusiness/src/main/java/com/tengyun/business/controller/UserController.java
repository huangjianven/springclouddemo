package com.tengyun.business.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.UserService;
import com.tengyun.common.entity.ResponseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

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
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping("list")
    public ResponseDTO selectAll(Page<User> page, User user) {
        return ResponseDTO.success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public ResponseDTO selectOne(@RequestParam Serializable id) {
        return ResponseDTO.success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseDTO insert(@RequestBody User user) {
        return ResponseDTO.success(this.userService.save(user));
    }

    /**
     * 登陆
     *
     * @param code JSCODE
     * @return 新增结果
     */
    @RequestMapping("login")
    public ResponseDTO login(@RequestParam String code) {
        return this.userService.login(code);
    }

    /**
     * 绑定
     *
     * @param code 验证码
     * @return 新增结果
     */
    @PostMapping("bind")
    public ResponseDTO bind(@RequestParam String code) {
        return this.userService.login(code);
    }

    /**
     * 获取验证码
     *
     * @param phone 电话号码
     * @return 新增结果
     */
    @PostMapping("verification")
    public ResponseDTO verification(@RequestParam String phone) {
        return this.userService.verification(phone);
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseDTO update(@RequestBody User user) {
        return ResponseDTO.success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseDTO delete(@RequestParam("ids") List<String> ids) {
        return ResponseDTO.success(this.userService.removeByIds(ids));
    }
}