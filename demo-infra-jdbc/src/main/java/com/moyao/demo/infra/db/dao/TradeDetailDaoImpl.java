package com.moyao.demo.infra.db.dao;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moyao.demo.infra.rpc.db.mapper.TradeDetailMapper;
import com.moyao.demo.infra.db.model.TradeDetailDo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeDetailDaoImpl implements TradeDetailDao{

    private final TradeDetailMapper tradeDetailMapper;

    @Override
    public TradeDetailDo selectById(Long id, Long shopId) {

        QueryWrapper<TradeDetailDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(TradeDetailDo::getId, id)
                .eq(TradeDetailDo::getShopId, shopId);
        return  tradeDetailMapper.selectOne(queryWrapper);
    }
}
