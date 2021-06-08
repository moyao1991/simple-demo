package com.moyao.demo.interfaces.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyao.demo.application.TransferService;
import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.common.exception.DailyLimitExceededException;
import com.moyao.demo.interfaces.web.common.Result;
import com.moyao.demo.interfaces.web.common.UserHolder;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public Result transfer(@RequestBody TransferCommand cmd) throws DailyLimitExceededException {
        cmd.setSourceUserId(UserHolder.get());
        Boolean result = transferService.transfer(cmd);
        return result? Result.success() : Result.fail();
    }
}
