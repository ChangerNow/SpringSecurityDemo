package com.security.demo.enums;

/**
 * 通用响应码
 */
public interface ResultCode {

    /**
     * 获取结果码
     *
     * @return
     */
    int getCode();

    /**
     * 获取结果描述
     *
     * @return
     */
    String getMsg();

    /**
     * 获取结果显示
     *
     * @return
     */
    String getView();
}
