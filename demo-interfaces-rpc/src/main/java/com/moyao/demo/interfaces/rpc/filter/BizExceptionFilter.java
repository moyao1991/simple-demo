package com.moyao.demo.interfaces.rpc.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import com.moyao.demo.common.exception.BizDataException;
import com.moyao.demo.common.exception.BizException;
import com.moyao.demo.interfaces.rpc.common.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Activate(group = {CommonConstants.PROVIDER}, order = 1000)
public class BizExceptionFilter implements Filter {

    @Override
    public org.apache.dubbo.rpc.Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            return invoker.invoke(invocation);
        } catch (BizException e) {
            return AsyncRpcResult.newDefaultAsyncResult(handleBizException(e), invocation);
        }
    }

    private Result handleBizException(BizException e) {
        if (e instanceof BizDataException) {
            return handleBizDataException((BizDataException) e);
        }
        return Result.fail(e.getCode(), e.getMsg());
    }

    private Result handleBizDataException(BizDataException e) {
        return Result.fail(e.getCode(), e.getMsg(), e.getData());
    }

}
