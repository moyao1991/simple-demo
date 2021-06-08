package com.moyao.demo.interfaces.web.common.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.moyao.demo.infra.db.model.UsersDo;
import com.moyao.demo.interfaces.web.common.vo.LoginUserVo;

@Mapper
public interface UserConverter {

    UserConverter CONVERTER = Mappers.getMapper(UserConverter.class);

    LoginUserVo toLoginUserVo(UsersDo usersDo);
}
