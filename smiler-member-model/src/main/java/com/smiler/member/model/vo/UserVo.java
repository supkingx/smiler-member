package com.smiler.member.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smiler.member.common.Vo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/30
 */
@Getter
@Setter
public class UserVo extends Vo {

    private static final long serialVersionUID = -2239870254191299407L;

    private BigInteger id;

    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private Byte gender;
    private String address;

}
