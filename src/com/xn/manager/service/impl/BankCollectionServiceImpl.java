package com.xn.manager.service.impl;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.common.util.BeanUtils;
import com.xn.manager.dao.BankCollectionDao;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.model.BankCollectionModel;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.BankCollectionService;
import com.xn.manager.service.BankService;
import com.xn.system.dao.AccountDao;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:查询卡成功信息Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankCollectionService")
public class BankCollectionServiceImpl<T> extends BaseServiceImpl<T> implements BankCollectionService<T> {
    private static Logger log=Logger.getLogger(BankCollectionServiceImpl.class);

    @Autowired
    private BankCollectionDao bankCollectionDao;

    @Autowired
    private AccountDao accountDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankCollectionDao;
    }

    @Override
    public List<BankCollectionModel> queryBankCollection(BankCollectionModel bankCollectionModel) {
        List<Account>  accountsList = accountDao.queryAllList();//查询用户表信息
        List<BankCollectionModel>  rsList  = bankCollectionDao.queryBankCardCountInfo(bankCollectionModel);

        List<BankCollectionModel>  addRsList =new ArrayList<>();
        for(BankCollectionModel bankCollectionModel1:rsList){
            BankCollectionModel  bankCollectionModel2 = new BankCollectionModel();
            BeanUtils.copy(bankCollectionModel1,bankCollectionModel2);
            for (Account account1:accountsList){
                if(account1.getId()==bankCollectionModel1.getAccountId().longValue()){
                    bankCollectionModel2.setAccountIdName(account1.getAcName());
                    continue;
                }
                if(account1.getId()==bankCollectionModel1.getCardSiteId().longValue()){
                    bankCollectionModel2.setCardSiteIdName(account1.getAcName());
                    continue;
                }
            }
            addRsList.add(bankCollectionModel2);
        }
        return addRsList;
    }

    @Override
    public BankCollectionModel queryCountInfo(BankCollectionModel bankCollectionModel) {
        return bankCollectionDao.queryCountInfo(bankCollectionModel);
    }
}
