package com.smiler.member.model.po;

import com.smiler.member.common.Vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/27
 */
public class UserIndexMessageVo extends Vo {

    private static final long serialVersionUID = -6833093199683502117L;

    private List<BigInteger> ids;

    public List<BigInteger> getIds() {
        return ids;
    }

    public void setIds(List<BigInteger> ids) {
        this.ids = ids;
    }
}
