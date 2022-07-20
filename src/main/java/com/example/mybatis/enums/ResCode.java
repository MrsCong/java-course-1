package com.example.mybatis.enums;

import com.alibaba.druid.util.StringUtils;

public enum ResCode {

    SUCCESS("200","成功"),
    ERROR("500","错误");

    private String code;

    private String name;

    ResCode(String code,String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    public static String getNameByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        ResCode[] values = ResCode.values();
        for (ResCode resCode : values) {
            if (resCode.code.equals(code)) {
                return resCode.getName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(SUCCESS);
    }


}
