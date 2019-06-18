package com.tengyun.business.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tengyun.business.entity.Company;
import com.tengyun.business.service.CompanyService;
import com.tengyun.data.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 公司(Company)表控制层
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:21
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    /**
     * 服务对象
     */
    @Resource
    private CompanyService companyService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param company 查询实体
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<IPage<Company>> selectAll(Page<Company> page, Company company) {

        return Result.success(this.companyService.page(page, new QueryWrapper<>(company)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public Result<Company> selectOne(@RequestParam Serializable id) {
        return Result.success(this.companyService.getById(id));
    }
//
    /**
     * 新增数据
     *
     * @param company 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Company company) {
        return Result.success(this.companyService.save(company));
    }

    /**
     * 修改数据
     *
     * @param company 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Company company) {
        return Result.success(this.companyService.updateById(company));
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody Company company) {
        company = this.companyService.getById(company.getId());
        return Result.success(this.companyService.updateById(company));
    }
}