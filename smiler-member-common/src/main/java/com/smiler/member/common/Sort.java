package com.smiler.member.common;

import java.io.Serializable;

/**
 * @description: 排序字段
 * @Author: wangchao
 * @Date: 2021/7/15
 */
public class Sort implements Serializable {

    private static final long serialVersionUID = 2146828055798054069L;

    private String orderField;
    private OrderType orderType = OrderType.ASC;

    public Sort() {
    }

    public Sort(String orderField, OrderType orderType) {
        this.orderField = orderField;
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return getOrderField() + " " + getOrderType();
    }
}
