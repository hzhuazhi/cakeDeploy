package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExcelUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.AccountTpModel;
import com.xn.manager.model.BankCollectionModel;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.model.excel.BankExcelModel;
import com.xn.manager.service.BankCollectionService;
import com.xn.manager.service.BankService;
import com.xn.manager.service.MobileCardService;
import com.xn.system.entity.Account;
import com.xn.system.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 银行卡表信息
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/bankcollection")
public class BankCollectionController extends BaseController {

    private static Logger log = Logger.getLogger(BankCollectionController.class);

    @Autowired
    private BankCollectionService<BankCollectionModel> bankCollectionService;


    @Autowired
    private AccountService<Account> accountService;
    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/bankcollection/bankcollectionIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, BankCollectionModel model) throws Exception {
        List<BankCollectionModel> dataList = new ArrayList<BankCollectionModel>();
        if(null==model.getCreateTime()&&null==model.getBeginTime()){
            model.setCurday(Integer.parseInt(DateUtil.getNowShortDate()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                    model.setAccountId(account.getId());
                }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                    model.setCardSiteId(account.getId());
                }
            }
            dataList = bankCollectionService.queryBankCollection(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, BankCollectionModel model) throws Exception {
        List<BankCollectionModel> dataList = new ArrayList<BankCollectionModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                model.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }
            dataList = bankCollectionService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }



    /**
     * @Description: 查看银行卡汇总数据
     * @param request
    * @param response
    * @param model
     * @date 2020/10/10 15:50
     */
    @RequestMapping("/dataBankAllList")
    public void dataBankAllList(HttpServletRequest request, HttpServletResponse response, BankCollectionModel model) throws Exception {
        if(null==model.getCreateTime()&&null==model.getBeginTime()){
            model.setCurday(Integer.parseInt(DateUtil.getNowShortDate()));
        }
        BankCollectionModel bankCollectionModel= new  BankCollectionModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                model.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }
            bankCollectionModel = bankCollectionService.queryCountInfo(model);
        }
        HtmlUtil.writerJson(response, bankCollectionModel);
    }





    /***
     * 获取卡站点信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/queryCardSite")
    public void queryCardSite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Account> dataList = new ArrayList<Account>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);

        Account accountBean  = new  Account();

        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                accountBean.setRoleId(ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE);
                accountBean.setCreateUser(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                accountBean.setRoleId(ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE);
                accountBean.setId(account.getId());
            }
            dataList = accountService.queryAllList(accountBean);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /***
     * 获取卡商信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/queryAccount")
    public void queryAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Account> dataList = new ArrayList<Account>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);

        Account accountBean  = new  Account();
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                accountBean.setId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                HtmlUtil.writerJson(response, dataList);
            }
            dataList = accountService.queryAllList(accountBean);
        }
        HtmlUtil.writerJson(response, dataList);
    }


//    queryAllList


}
