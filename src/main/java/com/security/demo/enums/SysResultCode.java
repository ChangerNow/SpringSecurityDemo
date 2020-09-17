package com.security.demo.enums;

/**
 * @author: zoubing
 * @date 2020/9/17 15:20
 **/
public enum SysResultCode implements ResultCode{


    ;


    /**
     * 错误码
     */
    private int code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 显示错误内容
     */
    private String view;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getView() {
        return view;
    }


}
