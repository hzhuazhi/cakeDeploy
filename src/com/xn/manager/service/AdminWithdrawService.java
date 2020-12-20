package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.AdminWithdrawModel;

/**
 * @Description 管理员：提现记录的Service层
 * @Author yoko
 * @Date 2020/12/19 12:30
 * @Version 1.0
 */
public interface AdminWithdrawService<T> extends BaseService<T> {

    /**
     * @Description: 分配下发订单
     * @param model
     * @return
     * @author yoko
     * @date 2020/12/19 14:06
     */
    public int updateOutType(AdminWithdrawModel model);
}
