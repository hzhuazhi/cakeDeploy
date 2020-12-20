package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.AdminWithdrawModel;

/**
 * @Description 管理员：提现记录的Dao层
 * @Author yoko
 * @Date 2020/12/19 12:29
 * @Version 1.0
 */
public interface AdminWithdrawDao<T> extends BaseDao<T> {

    /**
     * @Description: 分配下发订单
     * @param model
     * @return
     * @author yoko
     * @date 2020/12/19 14:06
    */
    public int updateOutType(AdminWithdrawModel model);
}
