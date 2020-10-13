package com.security.demo.security.utils;
import lombok.Data;
/**
 * 描述：
 *
 **/
@Data
public class Response {
    private String code;
    private String msg;
    private Object data;
    public Response() {
        this.code = "200";
        this.msg = "SUCCESS";
    }
    public Response(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}

