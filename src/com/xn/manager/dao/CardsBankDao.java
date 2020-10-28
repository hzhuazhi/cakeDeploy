package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.CardsBankModel;

import java.util.List;

/**
 * @Description:卡商银行卡信息
 * @create 2018-09-11 14:42
 **/
public interface CardsBankDao<T> extends BaseDao<T> {
    public List<CardsBankModel> querySupplyType(CardsBankModel  cardsBankModel);
}
