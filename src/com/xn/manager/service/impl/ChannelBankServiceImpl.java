package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.ChannelBankDao;
import com.xn.manager.model.ChannelBankModel;
import com.xn.manager.service.BankTypeService;
import com.xn.manager.service.ChannelBankService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:商户与银行卡的绑定关系Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("channelBankService")
public class ChannelBankServiceImpl<T> extends BaseServiceImpl<T> implements ChannelBankService<T> {
    private static Logger log   =  Logger.getLogger(ChannelBankServiceImpl.class);

    @Autowired
    private ChannelBankDao channelBankDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelBankDao;
    }

    @Override
    public String byIdQueryBankCard(Long id) {
        String    rsString = "";
        List<ChannelBankModel> list = channelBankDao.byIdQueryBank(id);
        if(list.size()==0){
            return rsString;
        }else{
//            List<Long>     idList = new ArrayList<>();
            for(ChannelBankModel channelBankModel1:list){
//                idList.add(channelBankModel1.getBankId());
                rsString+=channelBankModel1.getBankCard()+"#";
            }
//            channelBankModel.setBankIdList(idList);
//            List<ChannelBankModel> rsList=channelBankDao.byBankIdQueryBankCard(channelBankModel);
//
//            for (ChannelBankModel channelBankModelRs:rsList){
//                rsString+=channelBankModelRs.getBankCard()+"#";
//            }
        }
        return rsString;
    }

    @Override
    public List<ChannelBankModel> queryByAll(ChannelBankModel channelBankModel) {
        return channelBankDao.queryByAll(channelBankModel);
    }

    @Override
    public List<ChannelBankModel> queryNotChannelBankAll(ChannelBankModel channelBankModel) {
        return channelBankDao.queryNotChannelBankAll(channelBankModel);
    }

    @Override
    public List<ChannelBankModel> byIdQueryBank(ChannelBankModel channelBankModel) {
        return channelBankDao.byIdQueryBank(channelBankModel.getChannelId());
    }
}
