package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.InterestModel;
import com.xn.manager.model.MerchantModel;

import java.util.List;

/**
 * @Description:利益分配者Service层
 * @create 2018-09-11 14:40
 **/
public interface InterestService<T> extends BaseService<T> {
    /**
     * 处理金额
     * @param dateList
     * @return
     */
    List<InterestModel> queryListInfo(List<InterestModel> dateList) ;
}
