package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.BankShortMsgStrategyModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.service.BankShortMsgStrategyService;
import com.xn.manager.service.BankTypeService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
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

/**
 * @Description 银行收款短信解析策略的Controller层
 * @Author yoko
 * @Date 2020/9/19 15:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/bankShortMsgStrategy")
public class BankShortMsgStrategyController extends BaseController {

    private static Logger log = Logger.getLogger(BankShortMsgStrategyController.class);

    @Autowired
    private BankShortMsgStrategyService<BankShortMsgStrategyModel> bankShortMsgStrategyService;

    @Autowired
    private BankTypeService<BankTypeModel> bankTypeService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/bankShortMsgStrategy/bankShortMsgStrategyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, BankShortMsgStrategyModel model) throws Exception {
        List<BankShortMsgStrategyModel> dataList = new ArrayList<BankShortMsgStrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = bankShortMsgStrategyService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, BankShortMsgStrategyModel model) throws Exception {
        List<BankShortMsgStrategyModel> dataList = new ArrayList<BankShortMsgStrategyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = bankShortMsgStrategyService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("bankTyp", bankTypeService.queryAllList());
        return "manager/bankShortMsgStrategy/bankShortMsgStrategyAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, BankShortMsgStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            BankTypeModel bankTypeQuery = new BankTypeModel();
            bankTypeQuery.setId(bean.getBankTypeId());
            BankTypeModel bankTypeModel = bankTypeService.queryById(bankTypeQuery);
            if (bankTypeModel != null && bankTypeModel.getId() > 0){
                if (!StringUtils.isBlank(bankTypeModel.getSmsNum())){
                    bean.setSmsNum(bankTypeModel.getSmsNum());
                }
            }
            bankShortMsgStrategyService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        BankShortMsgStrategyModel atModel = new BankShortMsgStrategyModel();
        atModel.setId(id);
        model.addAttribute("account", bankShortMsgStrategyService.queryById(atModel));
        model.addAttribute("bankTyp", bankTypeService.queryAllList());
        return "manager/bankShortMsgStrategy/bankShortMsgStrategyEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,BankShortMsgStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            BankTypeModel bankTypeQuery = new BankTypeModel();
            bankTypeQuery.setId(bean.getBankTypeId());
            BankTypeModel bankTypeModel = bankTypeService.queryById(bankTypeQuery);
            if (bankTypeModel != null && bankTypeModel.getId() > 0){
                if (!StringUtils.isBlank(bankTypeModel.getSmsNum())){
                    bean.setSmsNum(bankTypeModel.getSmsNum());
                }
            }
            bankShortMsgStrategyService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, BankShortMsgStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bankShortMsgStrategyService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, BankShortMsgStrategyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bankShortMsgStrategyService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
