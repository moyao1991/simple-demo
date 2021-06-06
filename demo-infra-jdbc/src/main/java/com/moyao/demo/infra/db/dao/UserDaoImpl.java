package com.moyao.demo.infra.db.dao;

import org.springframework.stereotype.Service;

import com.moyao.demo.infra.rpc.db.mapper.UserMapper;
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
}
