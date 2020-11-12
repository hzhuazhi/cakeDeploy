package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.InterestWithdrawModel;
import com.xn.manager.model.MerchantWithdrawModel;

/**
 * @Description: 提现明细表
 * @create 2018-09-11 14:42
 **/
public interface InterestWithdrawDao<T> extends BaseDao<T> {
    /**
     * 根据条件查询总额度
     * @param interestWithdraw
     * @return
     */
    public InterestWithdrawModel queryByCountMoney(InterestWithdrawModel interestWithdraw);
}
