package com.smiler.member.model.so;

import com.smiler.member.common.So;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSo extends So {

    private static final long serialVersionUID = -3254277528930881464L;

    private String username;

    private String address;

    private Byte gender;

    private String birthdayStart;

    private String birthdayEnd;
}