package com.tengyun.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tengyun.business.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户表(User)表服务接口
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:19
 */
public interface UserService extends IService<User> {

    void login(String code, HttpServletResponse response) throws IOException;

    void verification(String phone);
}