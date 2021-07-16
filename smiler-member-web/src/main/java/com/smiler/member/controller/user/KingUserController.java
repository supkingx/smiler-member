package com.smiler.member.controller.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.smiler.member.model.so.KingUserSo;
import com.smiler.member.model.vo.KingUserVo;
import com.smiler.member.service.KingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/15
 */
@RestController
@RequestMapping("/kinguser")
public class KingUserController {

    @Autowired
    private KingUserService kingUserService;

    @PostMapping("/queryAllKingUser")
    public List<KingUserVo> queryAllKingUser(@RequestBody KingUserSo so) {
        System.out.println(JSON.toJSONString(so));
        List<KingUserVo> kingUserVoList = kingUserService.queryAllKinUser(so);
        return kingUserVoList;
    }

    @PostMapping("/queryAllKinUserPage")
    public PageInfo<KingUserVo> queryAllKinUserPage(@RequestBody KingUserSo so) {
        System.out.println(JSON.toJSONString(so));
        PageInfo<KingUserVo> pageInfo = kingUserService.queryAllKinUserPage(so);
        return pageInfo;
    }
}
