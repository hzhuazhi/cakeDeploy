package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.ReplacePayStrategyModel;

/**
 * @Description:配置管理的Service层
 * @create 2018-09-11 14:40
 **/
public interface ReplacePayStrategyService<T> extends BaseService<T> {
   public ReplacePayStrategyModel queryByTsTime(ReplacePayStrategyModel  replacePayStrategyModel);
}
