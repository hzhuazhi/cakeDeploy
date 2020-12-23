package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankPoolModel;

import java.util.List;

/**
 * @Description:银行卡池子
 * @create 2018-09-11 14:42
 **/
public interface BankPoolDao<T> extends BaseDao<T> {
    List<BankPoolModel>  byIdQueryBank(BankPoolModel bankPoolModel);
}
