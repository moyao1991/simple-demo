package com.moyao.demo.infra.rpc.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moyao.demo.infra.db.model.TradeDetailDo;

@Mapper
public interface TradeDetailMapper extends BaseMapper<TradeDetailDo> {
}
