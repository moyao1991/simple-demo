package com.moyao.demo.infra.db.dao;

import com.moyao.demo.infra.db.model.TradeDetailDo;

public interface TradeDetailDao {

    TradeDetailDo  selectById(Long id, Long shopId);

}
