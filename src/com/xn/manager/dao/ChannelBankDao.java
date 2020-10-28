package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.ChannelBankModel;

import java.util.List;

/**
 * @Description:商户与银行卡的绑定关系
 * @create 2018-09-11 14:42
 **/
public interface ChannelBankDao<T> extends BaseDao<T> {
    /***
     * 根据id 查询对应多少张卡
     * @param id
     * @return
     */
    public List<ChannelBankModel> byIdQueryBank(Long id);

    /***
     * 根据卡id 集合 查询 卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankModel> byBankIdQueryBankCard(ChannelBankModel channelBankModel);

    /**
     * 根据条件查询有的数据
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankModel> queryByAll(ChannelBankModel channelBankModel);

    /**
     * 查询不包含的银行卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankModel> queryNotChannelBankAll(ChannelBankModel channelBankModel);
}
