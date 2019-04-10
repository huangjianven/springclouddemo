package com.example.mutidatasource.business.test1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.ResponseDTO;
import com.example.mutidatasource.business.test1.entity.TestUser;
import com.example.mutidatasource.business.test1.service.TestUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (TestUser)表控制层
 *
 * @author 黄建文
 * @since 2019-04-08 19:51:04
 */
@RestController
@RequestMapping("testUser")
public class TestUserController {
    /**
     * 服务对象
     */
    @Resource
    private TestUserService testUserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param testUser 查询实体
     * @return 所有数据
     */
    @GetMapping("list")
    public ResponseDTO selectAll(Page<TestUser> page, TestUser testUser) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseDTO.success(this.testUserService.page(page, new QueryWrapper<>(testUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public ResponseDTO selectOne(@RequestParam Serializable id) {
        return ResponseDTO.success(this.testUserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param testUser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseDTO insert(@RequestBody TestUser testUser) {
        return ResponseDTO.success(this.testUserService.save(testUser));
    }

    /**
     * 修改数据
     *
     * @param testUser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseDTO update(@RequestBody TestUser testUser) {
        return ResponseDTO.success(this.testUserService.updateById(testUser));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseDTO delete(@RequestParam("ids") List<Integer> ids) {
        return ResponseDTO.success(this.testUserService.removeByIds(ids));
    }
}