package com.moyao.demo.infra.rpc.extservice;

import org.springframework.stereotype.Service;

import com.moyao.demo.infra.rpc.extobj.UserConfigEo;

@Service
public class UserConfigExServiceImpl implements UserConfigExService {

    @Override
    public UserConfigEo getUserConfig(Long userId) {
        return new UserConfigEo();
    }
}
