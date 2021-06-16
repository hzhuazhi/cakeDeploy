package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.model.ReplacePayModel;
import com.xn.manager.model.ReplacePayStrategyModel;
import com.xn.manager.service.ReplacePayService;
import com.xn.manager.service.ReplacePayStrategyService;
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
 * @Description 银行卡表信息
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/replacePayStrategy")
public class ReplacePayStrategyController extends BaseController {

    private static Logger log = Logger.getLogger(ReplacePayStrategyController.class);

    @Autowired
    private ReplacePayStrategyService<ReplacePayStrategyModel> replacePayStrategyService;

    @Autowired
    private ReplacePayService<ReplacePayModel> replacePayService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/replacepaystrategy/replacepaystrategyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ReplacePayStrategyModel model) throws Exception {
        List<ReplacePayStrategyModel> dataList = new ArrayList<ReplacePayStrategyModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAccountNum(account.getAccountNum());

//                model.setMerchantId(account.getId());
            }
            dataList = replacePayStrategyService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ReplacePayStrategyModel model) throws Exception {
        List<ReplacePayStrategyModel> dataList = new ArrayList<ReplacePayStrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setMerchantId(account.getId());
//            }

            dataList = replacePayStrategyService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/loadDataAllList")
    public void loadDataAllList(HttpServletRequest request, HttpServletResponse response, ReplacePayStrategyModel model) throws Exception {
        List<ReplacePayStrategyModel> dataList = new ArrayList<ReplacePayStrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
            dataList = replacePayStrategyService.queryAllList(model);
            List<Long>  idList = new ArrayList<>();
            if(dataList.size()!=0){
                for(ReplacePayStrategyModel replacePayStrategyModel:dataList){
                    idList.add(replacePayStrategyModel.getReplacePayId());
                }
            }
            ReplacePayModel  replacePayModel  =new ReplacePayModel();
            replacePayModel.setIdList(idList);
            List<ReplacePayModel> notDataList = replacePayService.queryAllList(replacePayModel);

            ReplacePayStrategyModel  queryModel=replacePayStrategyService.queryByTsTime(new ReplacePayStrategyModel());

            for(ReplacePayModel  replacePayModel1:notDataList){
                ReplacePayStrategyModel  replacePayStrategyModel = new ReplacePayStrategyModel();
                replacePayStrategyModel.setReplacePayId(replacePayModel1.getId());
                if(queryModel!=null){
                    replacePayStrategyModel.setOpenTimeSlot(queryModel.getOpenTimeSlot());
                    replacePayStrategyModel.setOutMonthMoney(queryModel.getOutMonthMoney());
                    replacePayStrategyModel.setOutDayNum(queryModel.getOutDayNum());
                    replacePayStrategyModel.setOutDayMoney(queryModel.getOutMonthMoney());
                }
                replacePayStrategyService.add(replacePayStrategyModel);
            }
        sendSuccessMessage(response, "导入成功");
//        }
//        HtmlUtil.writerJson(response, dataList);
    }


    /**
     *
     * 获取需要替换的卡表格数据列表
     */
    @RequestMapping("/queryReplaceList")
    public void queryReplaceList(HttpServletRequest request, HttpServletResponse response, ReplacePayStrategyModel model) throws Exception {
        List<BankModel> dataList = new ArrayList<BankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setMerchantId(account.getId());
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
            ReplacePayStrategyModel  mobileCardModel = new  ReplacePayStrategyModel();
//            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                mobileCardModel.setMerchantId(account.getId());
//            }
//            model.addAttribute("mobile",mobileCardService.queryAllList(mobileCardModel));
            model.addAttribute("type",replacePayStrategyService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/replacepaystrategy/replacePayStrategyAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,ReplacePayStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            bean.setMerchantId(account.getId());
            replacePayStrategyService.add(bean);
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
//
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            ReplacePayStrategyModel  mobileCardModel = new  ReplacePayStrategyModel();
            model.addAttribute("account", replacePayStrategyService.queryById(mobileCardModel));
            model.addAttribute("op", op);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }

        return "manager/replacepaystrategy/replacePayStrategyEdit";
    }




    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ReplacePayStrategyModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            replacePayStrategyService.update(bean);
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
            replacePayStrategyService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ReplacePayStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            replacePayStrategyService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }



}
