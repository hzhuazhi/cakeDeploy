package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.TransferModel;

/**
 * @Description 中转站的Dao层
 * @Author yoko
 * @Date 2020/12/8 16:51
 * @Version 1.0
 */
public interface TransferDao<T> extends BaseDao<T> {

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
