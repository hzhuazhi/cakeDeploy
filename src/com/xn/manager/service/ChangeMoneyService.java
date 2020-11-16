package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.BankCollectionModel;
import com.xn.manager.model.ChangeMoneyModel;
import com.xn.manager.model.InterestModel;
import com.xn.manager.model.MerchantModel;

import java.util.List;

/**
 * @Description  查询卡成功信息service
 * @Date 2020/9/17 20:53
 * @Version 1.0
 */
public interface ChangeMoneyService<T> extends BaseService<T> {

    /**
     * 根据类型查询数据
     * @return
     */
    List<ChangeMoneyModel>    queryAscriptionInfo(Integer  type);

    /**
     * 有效的名字
     * @param type
     * @param id
     * @param merchantModel
     * @param interestModels
     * @return
     */
    String    queryAscriptionName(Integer  type,Integer  id,List<MerchantModel> merchantModel,List<InterestModel> interestModels);
}
