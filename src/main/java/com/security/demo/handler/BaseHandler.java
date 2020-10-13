package com.security.demo.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseHandler {

    protected void responseWrite(HttpServletRequest request, HttpServletResponse response, Object result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding("UTF-8");
        if (StringUtils.isNotEmpty(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods",
                    StringUtils.join(HttpMethod.GET.name(), HttpMethod.POST.name(),
                            HttpMethod.PUT.name(), HttpMethod.PATCH.name(),
                            HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name(), ','));
            response.setHeader("Access-Control-Allow-Headers",
                    StringUtils.join("Content-Type", "XFILENAME", "XFILECATEGORY", "XFILESIZE", "code", "state", ','));
        }
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
