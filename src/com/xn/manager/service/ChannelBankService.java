package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.ChannelBankModel;

import java.util.List;

/**
 * @Description:商户与银行卡的绑定关系Service层
 * @create 2018-09-11 14:40
 **/
public interface ChannelBankService<T> extends BaseService<T> {
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
    public List<ChannelBankModel> queryByAll(ChannelBankModel channelBankModel);

    /**
     * 根据条件查询不在这个条件里面的 银行卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankModel> queryNotChannelBankAll(ChannelBankModel channelBankModel);

    /**
     * 根据商户id 查询 该商户下的银行卡信息
     * @param channelBankModel
     * @return
     */
    public List<ChannelBankModel> byIdQueryBank(ChannelBankModel channelBankModel);
}
