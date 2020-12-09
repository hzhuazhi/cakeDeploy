package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.MerchantProfitModel;

/**
 * @Description 卡商的收益数据的Service层
 * @Author yoko
 * @Date 2020/12/9 11:19
 * @Version 1.0
 */
public interface MerchantProfitService<T> extends BaseService<T> {

    /**
     * @Description: 获取订单的total信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/3/27 17:56
     */
    public MerchantProfitModel getTotalData(MerchantProfitModel model);
}
