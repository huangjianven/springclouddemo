package com.example.singledatasource.business.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.entity.ResponseDTO;
import com.example.singledatasource.business.test.entity.Test;
import com.example.singledatasource.business.test.service.TestService;
import com.example.singledatasource.business.test.service.TestUserClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Test)表控制层
 *
 * @author 黄建文
 * @since 2019-04-08 19:04:15
 */
@RestController
@RequestMapping("test")
@Api(tags = {"测试"})
public class TestController {
    /**
     * 服务对象
     */
    @Resource
    private TestService testService;
    @Resource
    private TestUserClient testUserClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param test 查询实体
     * @return 所有数据
     */
    @GetMapping("list")
    @ApiOperation(value = "查询列表", notes = "根据name查询所有")
    public ResponseDTO selectAll(Page<Test> page, Test test) {
        ResponseDTO<IPage<Test>> success = ResponseDTO.success(this.testService.page(page, new QueryWrapper<>(test)));
        return ResponseDTO.success(this.testService.page(page, new QueryWrapper<>(test)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    @ApiOperation("通过ID查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "query")
    public ResponseDTO selectOne(@RequestParam Integer id) {
        Test test = this.testService.getById(id);
        ResponseDTO<Test> success = ResponseDTO.success(test);
        String s = JSONObject.toJSONString(success);
        return JSONObject.parseObject(s, ResponseDTO.class);
    }

    /**
     * 新增数据
     *
     * @param test 实体对象
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation("新增数据")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true),
//            @ApiImplicitParam(name = "name", value = "用户名称", required = true)
//    })
    public ResponseDTO insert(@RequestBody Test test) {
        return ResponseDTO.success(this.testService.save(test));
    }

    /**
     * 修改数据
     *
     * @param test 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseDTO update(@RequestBody Test test) {
        return ResponseDTO.success(this.testService.updateById(test));
    }

    /**
     * 删除数据
     *
     * @param ids 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseDTO delete(@RequestParam("ids") List<String> ids) {
        return ResponseDTO.success(this.testService.removeByIds(ids));
    }
    @PostMapping("stringRedisTemplate")
    public ResponseDTO stringRedisTemplate() {
        for (int i = 0; i < 100; i++) {
            stringRedisTemplate.opsForValue().set("user"+i, "1245");
        }
        String user = stringRedisTemplate.opsForValue().get("user");
        return ResponseDTO.success();
    }

    @PostMapping("redisTemplate")
    public ResponseDTO redisTemplate() {
        Test test = new Test();
        test.setId(1);
        test.setName("hjw");
        test.setPassword("123");
        redisTemplate.opsForValue().set("1",test);
        Test o = (Test) redisTemplate.opsForValue().get("1");
        return ResponseDTO.success();
    }

    @GetMapping("feign")
    public ResponseDTO feign() {
        ResponseDTO<Page<Test>> tests = testUserClient.listTestUser();
        return ResponseDTO.success(tests);
    }
    @GetMapping("restTemplate")
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public ResponseDTO restTemplate() {
        String s = restTemplate.getForObject("http://MUTIDATASOURCE/testUser/list",String.class);
        return ResponseDTO.success(s);
    }

    public ResponseDTO helloFallBack() {
        return ResponseDTO.failed("我去你吗");
    }
}