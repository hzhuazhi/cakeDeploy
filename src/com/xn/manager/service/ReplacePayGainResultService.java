package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.replacepay.ReplacePayGainResultModel;

import java.util.List;

/**
 * @ClassName:
 * @Description: 第三方代付主动拉取结果返回的订单结果的Service层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
public interface ReplacePayGainResultService<T> extends BaseService<T> {

    /**
     * @Description: 批量添加代付订单结果
     * @param list - 代付结果信息集合
     * @return
     * @author yoko
     * @date 2020/11/10 14:31
     */
    public int addBatchResult(List<ReplacePayGainResultModel> list);
}
