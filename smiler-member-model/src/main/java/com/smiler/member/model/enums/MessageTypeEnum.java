package com.smiler.member.model.enums;

/**
 * @description: 消息体类型枚举
 * @Author: wangchao
 * @Date: 2021/6/28
 */
public enum MessageTypeEnum {
    /**
     * 用户新建
     */
    USER_ADD(1, "用户新建");

    private Integer type;
    private String typeDesc;

    MessageTypeEnum(Integer type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
