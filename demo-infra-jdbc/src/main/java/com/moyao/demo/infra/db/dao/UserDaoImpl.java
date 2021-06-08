package com.moyao.demo.infra.db.dao;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moyao.demo.infra.db.mapper.UserMapper;
import com.moyao.demo.infra.db.model.UsersDo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class UserDaoImpl implements UserDao {

    private final UserMapper userMapper;

    @Override
    public UsersDo selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public UsersDo selectByUserName(String userName) {
        QueryWrapper<UsersDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UsersDo::getUsername, userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(UsersDo usersDo) {
        boolean insert = usersDo.getId() == null;

        if (insert) {
            usersDo.setDxCreated(LocalDateTime.now());
        }
        usersDo.setDxModified(LocalDateTime.now());

        if (insert) {
            userMapper.insert(usersDo);
            return;
        }
        userMapper.updateById(usersDo);
    }

}
