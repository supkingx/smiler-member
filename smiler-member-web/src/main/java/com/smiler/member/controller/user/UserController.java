package com.smiler.member.controller.user;

import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/12
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return userBaseService.testService();
    }

    @GetMapping("/queryAllUsers")
    public List<UserPo> queryAllUsers() {
        return userBaseService.queryAllUsers();
    }

    @GetMapping("/queryUserById")
    public UserPo queryUserById(BigInteger id) {
        return userBaseService.queryUserById(id);
    }

    @PostMapping("/insertUser")
    public void insertUser(@RequestBody List<UserVo> userVos) {
        userBaseService.insertUser(userVos);
    }

    @PostMapping("/updateUserById")
    public void updateUserById(@RequestBody List<UserVo> userVos) {
        userBaseService.updateUserById(userVos);
    }

    @PostMapping("/insertUser2")
    public void insertUser2(@RequestBody List<UserVo> userVos) {
        userService.insertUser(userVos);
    }

    @PostMapping("/queryUsersComprehensive")
    public List<UserVo> queryUsersComprehensive(@RequestBody UserSo userSo) {
        return userService.queryUsersComprehensive(userSo);
    }
}
