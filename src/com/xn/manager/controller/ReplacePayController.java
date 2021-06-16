package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.*;
import com.xn.manager.model.*;
import com.xn.manager.model.excel.BankExcelModel;
import com.xn.manager.service.*;
import com.xn.system.entity.Account;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 银行卡表信息
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/replacepay")
public class ReplacePayController extends BaseController {

    private static Logger log = Logger.getLogger(ReplacePayController.class);

    @Autowired
    private ReplacePayService<ReplacePayModel> replacePayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/replacepay/replacepayIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ReplacePayModel model) throws Exception {
        List<ReplacePayModel> dataList = new ArrayList<ReplacePayModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAccountNum(account.getAccountNum());

                model.setMerchantId(account.getId());
            }
            dataList = replacePayService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ReplacePayModel model) throws Exception {
        List<ReplacePayModel> dataList = new ArrayList<ReplacePayModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setMerchantId(account.getId());
            }

            dataList = replacePayService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }




    /**
     *
     * 获取需要替换的卡表格数据列表
     */
    @RequestMapping("/queryReplaceList")
    public void queryReplaceList(HttpServletRequest request, HttpServletResponse response, ReplacePayModel model) throws Exception {
        List<BankModel> dataList = new ArrayList<BankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setMerchantId(account.getId());
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
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
//            model.addAttribute("mobile",mobileCardService.queryAllList(mobileCardModel));
            model.addAttribute("type",replacePayService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/replacepay/replacepayAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,ReplacePayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setMerchantId(account.getId());
            replacePayService.add(bean);
            sendSuccessMessage(response, "保存成功~");
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
            model.addAttribute("account", replacePayService.queryById(atModel));
            model.addAttribute("op", op);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }

        return "manager/replacepay/replacePayEdit";
    }





    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpBankUpdate")
    public String jumpBankUpdate(HttpServletRequest request, HttpServletResponse response, Model model, long id, Integer op) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        BankModel atModel = new BankModel();
        atModel.setId(id);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MobileCardModel  mobileCardModel = new  MobileCardModel();
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                mobileCardModel.setMerchantId(account.getId());
            }
            model.addAttribute("account", replacePayService.queryById(atModel));
            model.addAttribute("op", op);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }

        return "manager/replacePay/replacePayEdit";
    }

    /**
     * 修改卡号数据
     */
    @RequestMapping("/updateBank")
    public void updateBank(HttpServletRequest request, HttpServletResponse response,ReplacePayModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//
            ReplacePayModel   bankChangeModel  = new  ReplacePayModel();
            ReplacePayModel queryBean1 =  replacePayService.queryByCondition(bankChangeModel);
            if(queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response, "该卡已经部署过了，不能重复保存，请重新换卡");
            }else{

                ReplacePayModel replacePayModel =new  ReplacePayModel();
                replacePayModel.setId(bean.getBankTypeId());
                List<ReplacePayModel> typeList = replacePayService.queryAllList(replacePayModel);
                //修改卡状态，修改卡号，和尾号、设备号
                ReplacePayModel    bankModel   =new ReplacePayModel();
                bankModel.setId(bean.getId());
                bankModel.setBankCard(bean.getBankCard());
//
                replacePayService.update(bankModel);
                sendSuccessMessage(response, "保存成功~");
            }
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }


    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ReplacePayModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            ReplacePayModel queryBankTypeBean  = new  ReplacePayModel();
            queryBankTypeBean.setId(bean.getBankTypeId());
            ReplacePayModel queryBean1 = (ReplacePayModel) replacePayService.queryByCondition(queryBankTypeBean);
            if(queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            }else{
                sendFailureMessage(response, "该信息未部署，请联系管理员！");
            }
            replacePayService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }


    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ReplacePayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            replacePayService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ReplacePayModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            replacePayService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping("/exportData")
    public void exportData(HttpServletRequest request, HttpServletResponse response, ReplacePayModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            List<BankModel> dataList = new ArrayList<BankModel>();
            // 导出数据
            String[] titles = new String[10];
            String[] titleCode = new String[10];
            String filename = "导入银行卡信息";
            titles = new String[]{"别名", "手机号","银行名称", "银行卡账号", "开户名", "收款日限金额", "收款月限金额"};
            titleCode = new String[]{"alias", "phoneNum", "bankName", "bankCard", "accountName", "inDayMoney", "inMonthMoney"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }

}
