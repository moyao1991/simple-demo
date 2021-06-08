package com.moyao.demo.infra.db.dao;

import com.moyao.demo.infra.db.model.UsersDo;

public interface UserDao {

    UsersDo selectById(Long id);

    UsersDo selectByUserName(String userName);

    void save(UsersDo usersDo);

}
