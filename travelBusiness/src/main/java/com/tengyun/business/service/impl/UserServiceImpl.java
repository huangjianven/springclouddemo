package com.tengyun.business.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tengyun.business.dao.UserMapper;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.UserService;
import com.tengyun.common.constant.WxConfig;
import com.tengyun.common.entity.ResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ResponseDTO login(String code) {
        String openId = getOpenId(code);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("open_id", openId);
        User user = this.getOne(userQueryWrapper);
        if (user == null) {
            return ResponseDTO.failed(403, "用户未绑定");
        }
        return null;
    }

    @Override
    public ResponseDTO verification(String phone) {
        return null;
    }

    public String getOpenId(String code) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", WxConfig.APPID);
        paramMap.put("secret", WxConfig.SECRET);
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        String s = HttpUtil.get(WxConfig.JSCODE2SESSION, paramMap);
        JSONObject object = JSONObject.parseObject(s);
        return object.getString("openId");
    }

}