package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankPoolDao;
import com.xn.manager.dao.ChannelBankPoolDao;
import com.xn.manager.model.ChannelBankModel;
import com.xn.manager.model.ChannelBankPoolModel;
import com.xn.manager.service.BankPoolService;
import com.xn.manager.service.ChannelBankPoolService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:商户的银行卡池子的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("channelBankPoolService")
public class ChannelBankPoolServiceImpl<T> extends BaseServiceImpl<T> implements ChannelBankPoolService<T> {
    private static Logger log=Logger.getLogger(ChannelBankPoolServiceImpl.class);

    @Autowired
    private ChannelBankPoolDao channelBankPoolDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return channelBankPoolDao;
    }

    @Override
    public String byIdQueryBankCard(ChannelBankPoolModel channelBankModel) {
        String    rsString = "";
        List<ChannelBankPoolModel> list = channelBankPoolDao.byIdQueryBank(channelBankModel);
        if(list.size()==0){
            return rsString;
        }else{
//            List<Long>     idList = new ArrayList<>();
            for(ChannelBankPoolModel channelBankModel1:list){
//                idList.add(channelBankModel1.getBankId());
                rsString+=" ("+channelBankModel1.getAcName()+"--"+channelBankModel1.getBankName()+"--"+channelBankModel1.getAccountName()+") "+"#";
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
    public List<ChannelBankPoolModel> queryByAll(ChannelBankPoolModel channelBankModel) {
        return channelBankPoolDao.queryByAll(channelBankModel);
    }

    @Override
    public List<ChannelBankPoolModel> queryNotChannelBankAll(ChannelBankPoolModel channelBankModel) {
        return channelBankPoolDao.queryNotChannelBankAll(channelBankModel);
    }

    @Override
    public List<ChannelBankPoolModel> byIdQueryBank(ChannelBankPoolModel channelBankModel) {
        return channelBankPoolDao.byIdQueryBank(channelBankModel);
    }
}
