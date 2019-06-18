package com.tengyun.business.service;

import com.tengyun.business.entity.Company;
import com.tengyun.business.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * obt服务接口
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:19
 */
public interface OBTService {

    String getToken(Company company);

    void autoLogin(User user, HttpServletResponse response) throws IOException;
}