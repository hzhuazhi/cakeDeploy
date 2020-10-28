package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.BankShortMsgModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.service.BankShortMsgService;
import com.xn.manager.service.BankTypeService;
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

/**
 * @Description 未处理短信
 * @Date 2020/9/11 14:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/bankshortmsg")
public class BankShortMsgController extends BaseController {

    private static Logger log = Logger.getLogger(BankShortMsgController.class);

    @Autowired
    private BankShortMsgService<BankShortMsgModel> bankShortMsgService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/bankshortmsg/bankshortmsgIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel model) throws Exception {
        List<BankShortMsgModel> dataList = new ArrayList<BankShortMsgModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
//                model.setId(account.getId());
            }
            dataList = bankShortMsgService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataListMsg")
    public void dataListMsg(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel model) throws Exception {
        List<BankShortMsgModel> dataList = new ArrayList<BankShortMsgModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            dataList = bankShortMsgService.querySms(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }



    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel model) throws Exception {
        List<BankShortMsgModel> dataList = new ArrayList<BankShortMsgModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
//                model.setId(account.getId());
            }
            dataList = bankShortMsgService.queryAllList(model);
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
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                sendFailureMessage(response,"只允许管理员操作!");
            }else {
//                model.addAttribute("agent", agentService.queryAllList());
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/bankshortmsg/bankshortmsgAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        BankTypeModel atModel = new BankTypeModel();
        atModel.setId(id);
        model.addAttribute("account", bankShortMsgService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/bankshortmsg/bankshortmsgEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,BankShortMsgModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                bankShortMsgService.update(bean);
                sendSuccessMessage(response, "保存成功~");
            }else {
                sendFailureMessage(response, "只有管理员才能进行修改!");
            }
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 修改数据
     */
    @RequestMapping("/updateHandleType")
    public void updateHandleType(HttpServletRequest request, HttpServletResponse response,BankShortMsgModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                bankShortMsgService.update(bean);
                sendSuccessMessage(response, "修改状态成功！");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                bankShortMsgService.delete(bean);
                sendSuccessMessage(response, "删除成功");
            }else {
                sendFailureMessage(response, "只有管理员才能进行删除!");
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, BankShortMsgModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bankShortMsgService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
