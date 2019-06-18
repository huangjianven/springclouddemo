package com.tengyun.business.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.tengyun.business.entity.Company;
import com.tengyun.business.entity.User;
import com.tengyun.business.service.OBTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * obt服务实现类
 *
 * @author 黄建文
 * @since 2019-06-16 22:30:21
 */
@Service("OBTService")
public class OBTServiceImpl implements OBTService {

    @Value("${obt.url}")
    private String url;

    @Override
    public String getToken(Company company) {
        String subUrl = "api/admin/oa/login/getToken";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appKey", company.getAppKey());
        paramMap.put("appSecurity", company.getAppSecurity());
        String s = HttpUtil.post(url+subUrl, JSONObject.toJSONString(paramMap));
        JSONObject object = JSONObject.parseObject(s);
        return object.getString("token");
    }
    @Override
    public void autoLogin(User user, HttpServletResponse response) throws IOException {
        String subUrl = "api/admin/oa/login/ssoLogin";
        String appKey = user.getCompany().getAppKey();
        String appSecurity = user.getCompany().getAppSecurity();
        String token = user.getCompany().getToken();
        String account = user.getAccount();
        String signature = SecureUtil.md5(appKey + account + SecureUtil.md5(appSecurity));
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("appKey", appKey);
        paramMap.put("token", token);
        paramMap.put("accountName", account);
        paramMap.put("signature", signature);
        paramMap.put("viewname", "travelorderlist");
        redirect(url + subUrl, paramMap, response);
    }
    private void redirect(String url, Map<String, String> params, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println(" <HEAD><TITLE>sender</TITLE></HEAD>");
        out.println(" <BODY>");
        out.println("<form name=\"submitForm\" action=\""+url+"\" method=\"post\">");
        for (String key : params.keySet()) {
            out.println("<input type=\"hidden\" name=\"" + key + "\" value=\"" + params.get(key) + "\"/>");
        }
        out.println("</from>");
        out.println("<script>window.document.submitForm.submit();</script>");
        out.println(" </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}