package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.MerchantWithdrawModel;

/**
 * @Description: 提现明细表
 * @create 2018-09-11 14:42
 **/
public interface MerchantWithdrawDao<T> extends BaseDao<T> {
    /**
     * 根据条件查询总额度
     * @param merchantWithdrawModel
     * @return
     */
    public MerchantWithdrawModel queryByCountMoney(MerchantWithdrawModel  merchantWithdrawModel);
}
