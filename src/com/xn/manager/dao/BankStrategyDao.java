package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankStrategyModel;

import java.util.List;

/**
 * @Description:银行卡放量策略
 * @create 2018-09-11 14:42
 **/
public interface BankStrategyDao<T> extends BaseDao<T> {
    public BankStrategyModel queryMaxupdateTime();

    /**
     * 查询不包括有效的银行卡部署信息
     * @param bankStrategyModel
     * @return
     */
    public List<BankStrategyModel> queryNotInBankId(BankStrategyModel bankStrategyModel);


    /**
     * 批量修改放量策略
     * @param bankStrategyModel
     * @return
     */
    public  int   updateBatch(BankStrategyModel bankStrategyModel);
}
