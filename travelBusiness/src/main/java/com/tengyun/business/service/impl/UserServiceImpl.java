package com.tengyun.business.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tengyun.business.dao.UserMapper;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.OBTService;
import com.tengyun.business.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 用户表(User)表服务实现类
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:20
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${weixin.appid}")
    private String appid;
    @Value("${weixin.secret}")
    private String secret;
    @Value("${weixin.openid-url}")
    private String openidUrl;
    @Resource
    private OBTService OBTService;

    @Override
    public void login(String code, HttpServletResponse response) throws IOException {
        String openId = getOpenId(code);
        User user = baseMapper.getDetailByOpenId(openId);
        if (user != null) {
            response.sendRedirect("http://localhost:8080");
        } else {
            String token = OBTService.getToken(user.getCompany());
            user.getCompany().setToken(token);
            OBTService.autoLogin(user, response);
        }
    }

    @Override
    public void verification(String phone) {

    }

    public String getOpenId(String code) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        String s = HttpUtil.get(openidUrl, paramMap);
        JSONObject object = JSONObject.parseObject(s);
        return object.getString("openid");
    }

}