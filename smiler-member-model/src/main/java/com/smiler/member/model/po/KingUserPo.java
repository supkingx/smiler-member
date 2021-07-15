package com.smiler.member.model.po;

import com.smiler.member.common.Po;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
@Getter
@Setter
public class KingUserPo extends Po {

    private static final long serialVersionUID = -6768252023153362282L;

    private BigInteger id;
    private String username;

    private Date birthday;
    private Byte gender;
    private String address;
}
