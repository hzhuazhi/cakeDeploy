package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.ChannelReplenishModel;
import com.xn.manager.model.MerchantReplenishModel;

/**
 * @Description 卡商审核补单申请的Service层
 * @Author yoko
 * @Date 2020/10/12 18:55
 * @Version 1.0
 */
public interface MerchantReplenishService<T> extends BaseService<T> {

    /**
     * @Description: 更新审核信息
     * @param model
     * @return
     * @author yoko
     * @date 2020/10/12 18:53
     */
    public int updateCheck(MerchantReplenishModel model);

    /**
     * @Description: 更新支付服务中渠道提交的补单信息
     * <p>
     *     把审核结果反馈给支付平台
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/10/13 16:43
     */
    public int updateChannelCheck(ChannelReplenishModel model);
}
