package com.example.mutidatasource.business.test1.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mutidatasource.business.test1.dao.TestUserMapper;
import com.example.mutidatasource.business.test1.entity.TestUser;
import com.example.mutidatasource.business.test1.service.TestUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (TestUser)表服务实现类
 *
 * @author 黄建文
 * @since 2019-04-08 19:51:04
 */
@Service("testUserService")
@DS("mysql1")
@Transactional(rollbackFor = Exception.class)
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements TestUserService {

}