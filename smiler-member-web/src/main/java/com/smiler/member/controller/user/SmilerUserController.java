package com.smiler.member.controller.user;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.service.SmilerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/5
 */
@RestController
@RequestMapping("/smiler/user")
public class SmilerUserController {

    @Autowired
    private SmilerUserService smilerUserService;

    @PostMapping("/addSmilerUser")
    public void addSmilerUser(@RequestBody UserVo userVo) {
        smilerUserService.addSmilerUser(userVo);
    }

    @PostMapping("/queryUsersComprehensive")
    public List<UserVo> queryUsersComprehensive(@RequestBody UserSo userSo) {
        return smilerUserService.queryUsersComprehensive(userSo);
    }
}
