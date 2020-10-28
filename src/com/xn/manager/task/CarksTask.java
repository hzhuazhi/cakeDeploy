package com.xn.manager.task;

import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.CardsBankDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.model.CardsBankModel;
import com.xn.manager.model.MobileCardModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author long
 * @Date 2020/9/12 15:51
 * @Version 1.0
 */

@Service
public class CarksTask {
    private final static Logger log = Logger.getLogger(CarksTask.class);

    @Autowired
    private CardsBankDao cardsBankDao;

    @Autowired
    private BankTypeDao bankTypeDao;

    @Autowired
    private MobileCardDao mobileCardDao;

    public void analysisCarks() throws Exception{
        while(true){
            CardsBankModel  cardsBankModel = new CardsBankModel();
            cardsBankModel.setSupplyType(1);
            List<CardsBankModel> rslist  = cardsBankDao.querySupplyType(cardsBankModel);



        }
    }



    public Integer   addBank(CardsBankModel  cardsBankModel){
        Integer    rsInt =  0;

        MobileCardModel mobileCardModel = new MobileCardModel();
        mobileCardModel.setPhoneNum(cardsBankModel.getPhoneNum());
        MobileCardModel queryMobileBean =(MobileCardModel)mobileCardDao.queryByCondition(mobileCardModel);

        return rsInt;
    }

}
