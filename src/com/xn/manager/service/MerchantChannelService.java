package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.MerchantChannelModel;

import java.util.List;

/**
 * @Description:代付订单表的Service层
 * @create 2018-09-11 14:40
 **/
public interface MerchantChannelService<T> extends BaseService<T> {
    public String byIdQueryMerchant(Long id);
    List<MerchantChannelModel> queryisAllList();
    List<MerchantChannelModel> queryByAll(MerchantChannelModel  queryBean);
}
