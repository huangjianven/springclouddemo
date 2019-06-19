package com.tengyun.business.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tengyun.business.dao.UserMapper;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.OBTService;
import com.tengyun.business.service.UserService;
import com.tengyun.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private OBTService OBTService;

    @Override
    public void login(String code, HttpServletResponse response) throws IOException {
        String openId = getOpenId(code);
        User user = baseMapper.getDetailByOpenId(openId);
        if (user != null) {
            String uid = UUID.fastUUID().toString(true);
            stringRedisTemplate.opsForValue().set(uid, openId);
            response.sendRedirect("http://localhost:8080/#/?uid="+uid);
        } else {
            String token = OBTService.getToken(user.getCompany());
            user.getCompany().setToken(token);
            OBTService.autoLogin(user, response);
        }
    }

    @Override
    public void autoLogin(String uid, HttpServletResponse response) throws IOException {
        String openId = stringRedisTemplate.opsForValue().get(uid);
        User user = baseMapper.getDetailByOpenId(openId);
        String token = OBTService.getToken(user.getCompany());
        user.getCompany().setToken(token);
        OBTService.autoLogin(user, response);
    }

    @Override
    public void bind(String uid, String phone, String code) {
        String verification = stringRedisTemplate.opsForValue().get("verification_" + phone);
        if (!code.equals(verification)) {
            throw BusinessException.message("验证码错误");
        }
        String openId = stringRedisTemplate.opsForValue().get(uid);
        User user = baseMapper.getDetailByPhone(phone);
        user.setOpenId(openId);
        updateById(user);
    }

    @Override
    public void send(String phone) {
        User user = baseMapper.getDetailByPhone(phone);
        if (user == null) {
            throw BusinessException.message("没有查到相关信息");
        }
        int randomInt = RandomUtil.randomInt(100000, 999999);
        stringRedisTemplate.opsForValue().set("verification_"+phone, String.valueOf(randomInt));
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