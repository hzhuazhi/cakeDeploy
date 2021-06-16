package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.ReplacePayStrategyModel;

import java.util.List;

/**
 * @Description:策略配置
 * @create 2018-09-11 14:42
 **/
public interface ReplacePayStrategyDao<T> extends BaseDao<T> {
   public ReplacePayStrategyModel  queryByTsTime(ReplacePayStrategyModel  replacePayStrategyModel);
}
