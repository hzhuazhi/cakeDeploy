package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.ChannelBankModel;
import com.xn.manager.model.ChannelBankPoolModel;

import java.util.List;

/**
 * @Description:商户的银行卡池子Service层
 * @create 2018-09-11 14:40
 **/
public interface ChannelBankPoolService<T> extends BaseService<T> {
    /***
     * 根据id 查询该id 下对应的银行卡，并且组成一个String 出来
     * @param id
     * @return
     */
    public String   byIdQueryBankCard(Long id);

    /***
     * 根据查询所有的id
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankPoolModel> queryByAll(ChannelBankPoolModel channelBankModel);

    /**
     * 根据条件查询不在这个条件里面的 银行卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankPoolModel> queryNotChannelBankAll(ChannelBankPoolModel channelBankModel);

    /**
     * 根据商户id 查询 该商户下的银行卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankPoolModel> byIdQueryBank(ChannelBankPoolModel channelBankModel);
}
