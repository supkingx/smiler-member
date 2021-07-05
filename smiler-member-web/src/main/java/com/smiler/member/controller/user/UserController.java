package com.smiler.member.controller.user;

import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao2.user.UserMapper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserBaseShardingService;
import com.smiler.member.service.UserService;
import com.smiler.member.service.UserShardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UserShardingService userShardingService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrikaFacade orikaFacade;

    @GetMapping("/test")
    public String test() {
        return userBaseService.testLog();
    }

    @GetMapping("/queryUserById")
    public UserPo queryUserById(BigInteger id) {
        return userBaseService.queryUserById(id);
    }

    @PostMapping("/updateUserById")
    public void updateUserById(@RequestBody List<UserVo> userVos) {
        userBaseService.updateUserById(userVos);
    }

    @PostMapping("/insertUser")
    public void insertUser(@RequestBody List<UserVo> userVos) {
        userService.insertUser(userVos);
    }

    @PostMapping("/addUserBatch")
    public void addUserBatch(@RequestBody List<UserVo> userVos) {
        userService.addUserBatch(userVos);
    }

    @PostMapping("/queryUsersComprehensive")
    public List<UserVo> queryUsersComprehensive(@RequestBody UserSo userSo) {
        return userService.queryUsersComprehensive(userSo);
    }

    @PostMapping("/insertUserBatch")
    public void insertUserBatch(@RequestBody List<UserVo> userVos) {
        userShardingService.addUserBatch(userVos);
    }

    @PostMapping("/insertUserBatch2")
    public void insertUserBatch2(@RequestBody List<UserVo> userPos) {
        userMapper.insertUser(orikaFacade.mapAsList(userPos, UserPo.class));
    }

    @GetMapping("/queryUser")
    public void queryUser(BigInteger id) {
        userShardingService.queryUserById(id);
    }

    @GetMapping("/updateUser")
    public void updateUser(List<UserVo> userPos) {
        userShardingService.updateUserById(userPos);
    }

    @GetMapping("/queryAll")
    public List<UserVo> queryAll() {
        return userShardingService.queryAllUsers();
    }
}
