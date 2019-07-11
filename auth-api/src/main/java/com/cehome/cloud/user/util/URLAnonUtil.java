package com.cehome.cloud.user.util;

import com.alibaba.fastjson.JSON;
import com.cehome.cloud.common.object.BaseResult;
import com.cehome.utils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 不做验证的地址获取工具类
 * 需要做权限认证的项目需要在配置中心添加配置项：perms.anon.urls
 * 如：perms.anon.urls=/login;/static/*
 *
 * Created by hyl on 2019/07/09/ 10:12
 */
public class URLAnonUtil {

    private static Logger logger = LoggerFactory.getLogger(URLAnonUtil.class);

    private static String URL_ANON = "";

    static {
        URL_ANON = PropertyUtils.getStringValue("perms.anon.urls");
        if (StringUtils.isEmpty(URL_ANON)) {
            throw new IllegalArgumentException("perms.anon.urls 参数不存在");
        }
    }

    public static List<String> listAnonUrl(){
        return Arrays.asList(URL_ANON.split(",")).stream().collect(Collectors.toList());
    }

    public static void unauthorized(ServletRequest req, ServletResponse resp, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try(PrintWriter out = httpServletResponse.getWriter()){
            out.append(JSON.toJSONString(BaseResult.fail(msg)));
        } catch (IOException e) {
            logger.error("返回Response信息出现IOException异常:",e);
        }
    }
}
