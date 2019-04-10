package com.example.mutidatasource.business.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mutidatasource.business.test.dao.TestMapper;
import com.example.mutidatasource.business.test.entity.Test;
import com.example.mutidatasource.business.test.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Test)表服务实现类
 *
 * @author 黄建文
 * @since 2019-04-08 19:04:14
 */
@Service("testService")
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}