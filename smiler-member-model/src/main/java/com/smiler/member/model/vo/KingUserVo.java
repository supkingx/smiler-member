package com.smiler.member.model.vo;

import com.smiler.member.common.Vo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
@Getter
@Setter
public class KingUserVo extends Vo {

    private static final long serialVersionUID = 356489375760766L;

    private BigInteger id;
    private String username;

    private String birthday;
    private Byte gender;
    private String address;
}
