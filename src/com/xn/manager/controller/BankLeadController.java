package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.BankService;
import com.xn.manager.service.BankTypeService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MobileCardService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 主卡：原始卡卡信息的Controller层
 * @Author yoko
 * @Date 2021/2/26 16:18
 * @Version 1.0
 */
@Controller
@RequestMapping("/banklead")
public class BankLeadController extends BaseController {

    private static Logger log = Logger.getLogger(BankController.class);

    @Autowired
    private BankService<BankModel> bankService;

    @Autowired
    private MobileCardService<MobileCardModel> mobileCardService;

    @Autowired
    private MerchantService<MerchantModel> merchantService;


    @Autowired
    private BankTypeService<BankTypeModel> bankTypeService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/bank/bankIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, BankModel model) throws Exception {
        List<BankModel> dataList = new ArrayList<BankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAccountNum(account.getAccountNum());

                model.setMerchantId(account.getId());
            }
            dataList = bankService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, BankModel model) throws Exception {
        List<BankModel> dataList = new ArrayList<BankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setMerchantId(account.getId());
            }

            dataList = bankService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MobileCardModel  mobileCardModel = new  MobileCardModel();
            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                mobileCardModel.setMerchantId(account.getId());
            }
            model.addAttribute("mobile",mobileCardService.queryAllList(mobileCardModel));
            model.addAttribute("type",bankTypeService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/bank/bankAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, BankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
//                bean.setMerchantId(account.getId());
//            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
//                bean.setMerchantId(account.getCreateUser());
//                bean.setMerchantSiteId(account.getId());
//            }
//            MerchantModel  queryModel = new MerchantModel();
//            queryModel.setAccountNum(account.getAccountNum());
//
//            MerchantModel beans = merchantService.queryByCondition(queryModel);
//            if(beans==null||beans.getId()<=0){
//                sendSuccessMessage(response, "数据有误，请联系管理员！");
//                return;
//            }
            bean.setMerchantId(account.getId());

            Map<String, Object> bankMap= bankService.isCheckCardsBank(bean,account);
            if(bankMap.get("type").equals("0")){
                bankService.add((BankModel)bankMap.get("bankModel"));
                sendSuccessMessage(response, "保存成功~");
            }else if(bankMap.get("type").equals("1")){
                sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
            }else if(bankMap.get("type").equals("2")){
                sendFailureMessage(response,"手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加！");
            }else if(bankMap.get("type").equals("3")){
                sendFailureMessage(response,"银行卡类型未部署，请联系管理员进行部署，再进行添加！");
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(HttpServletRequest request, HttpServletResponse response, Model model, long id, Integer op) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        BankModel atModel = new BankModel();
        atModel.setId(id);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MobileCardModel  mobileCardModel = new  MobileCardModel();
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                mobileCardModel.setMerchantId(account.getId());
            }
            model.addAttribute("mobile",mobileCardService.queryAllList(mobileCardModel));
            model.addAttribute("account", bankService.queryById(atModel));
            model.addAttribute("op", op);
            model.addAttribute("type",bankTypeService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }

        return "manager/bank/bankEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,BankModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if ("2".equals(op)) {
//                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
//            }
            BankTypeModel queryBankTypeBean  = new  BankTypeModel();
            queryBankTypeBean.setId(bean.getBankTypeId());
            BankTypeModel queryBean1 = (BankTypeModel) bankTypeService.queryByCondition(queryBankTypeBean);
            if(queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                bean.setBankName(queryBean1.getBankName());
                bean.setBankTypeId(queryBean1.getId());
                bean.setBankCode(queryBean1.getBankCode());
                bean.setSmsNum(queryBean1.getSmsNum());
            }else{
                sendFailureMessage(response, "该银行名称未部署，请联系管理员！");
            }

            MobileCardModel mobileCardModel = new MobileCardModel();
            mobileCardModel.setId(bean.getMobileCardId());
            MobileCardModel queryMobileBean =(MobileCardModel)mobileCardService.queryByCondition(mobileCardModel);

            if(queryMobileBean != null && queryMobileBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                bean.setPhoneNum(queryMobileBean.getPhoneNum());
            }else{
                sendFailureMessage(response, "该手机号未部署，请联系管理员！");
            }

            bankService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, BankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bankService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, BankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bankService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
