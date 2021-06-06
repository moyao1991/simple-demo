package com.moyao.demo.infra.rpc.extservice;

import com.moyao.demo.infra.rpc.extobj.UserConfigEo;

public interface UserConfigExService {

    UserConfigEo getUserConfig(Long userId);
}
