package com.moyao.demo.interfaces.web;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Charsets;
import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;
import com.moyao.demo.interfaces.web.common.Result;
import com.moyao.demo.interfaces.web.common.UserHolder;
import com.moyao.demo.interfaces.web.common.converter.UserConverter;
import com.moyao.demo.interfaces.web.common.vo.LoginUserVo;

import cn.hutool.core.codec.Base64;

@RestController
public class UserController {


    @Autowired
    private UserDao userDao;

    @GetMapping("/user")
    public Result getUser() {
        Long id = UserHolder.get();
        UsersDo usersDo = userDao.selectById(id);
        Preconditions.checkNotNull(usersDo);
        LoginUserVo loginUserVo = UserConverter.CONVERTER.toLoginUserVo(usersDo);
        return Result.success(loginUserVo);
    }

    @GetMapping("/login")
    public Result login(@RequestParam(value = "username") String username) {
        UsersDo usersDo = userDao.selectByUserName(username);
        Preconditions.checkNotNull(usersDo);
        return  Result.success(Base64.encode(String.valueOf(usersDo.getId()), Charsets.UTF_8));
    }
}
