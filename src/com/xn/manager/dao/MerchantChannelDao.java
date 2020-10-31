package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.MerchantChannelModel;

import java.util.List;

/**
 * @Description:商户管理代付卡商表
 * @create 2018-09-11 14:42
 **/
public interface MerchantChannelDao<T> extends BaseDao<T>{
    List<MerchantChannelModel> queryByAll(MerchantChannelModel merchantChannelModel);

    List<MerchantChannelModel>  queryisAllList();
}
