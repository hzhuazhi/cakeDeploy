package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.TransferModel;

/**
 * @Description 中转站的Service层
 * @Author yoko
 * @Date 2020/12/8 17:00
 * @Version 1.0
 */
public interface TransferService<T> extends BaseService<T> {

    /**
     * @Description: 更新用户的密码
     * <p>
     *     更新登录密码，提现密码
     * </p>
     * @param model
     * @return
     * @author yoko
     * @date 2020/11/15 17:53
     */
    public int updatePassWd(TransferModel model);

}
