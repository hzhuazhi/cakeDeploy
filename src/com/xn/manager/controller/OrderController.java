package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.ChannelModel;
import com.xn.manager.model.OrderModel;
import com.xn.manager.model.OrderReplenishModel;
import com.xn.manager.service.OrderReplenishService;
import com.xn.manager.service.OrderService;
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
import java.util.Date;
import java.util.List;

/**
 * @Description 商户信息
 * @Date 2020/9/11 14:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private static Logger log = Logger.getLogger(OrderController.class);

    @Autowired
    private OrderService<OrderModel> orderService;

    @Autowired
    private OrderReplenishService<OrderReplenishModel> orderReplenishService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/order/orderIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, OrderModel model) throws Exception {
        List<OrderModel> dataList = new ArrayList<OrderModel>();
        if (model.getBeginCurday() == 0 || model.getEndCurday() == 0){
            model.setBeginCurday(DateUtil.getDayNumber(new Date()));
            model.setEndCurday(DateUtil.getDayNumber(new Date()));
        }
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }
            else if(account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }


            dataList = orderService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataListBankCard")
    public void dataListBankCard(HttpServletRequest request, HttpServletResponse response, OrderModel model) throws Exception {
        List<OrderModel> dataList = new ArrayList<OrderModel>();
        if (model.getCurday()==null||model.getCurday() == 0 ){
            model.setCurday(DateUtil.getDayNumber(new Date()));
            model.setCurday(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }
            else if(account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }
            System.out.println("==========:"+model.getPage().getStartIndex());
            dataList = orderService.queryByListRelease(model);


        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /***
     * 无分页
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping("/dataListBankCardByCount")
    public void dataListBankCardByCount(HttpServletRequest request, HttpServletResponse response, OrderModel model) throws Exception {
        List<OrderModel> dataList = new ArrayList<OrderModel>();
        if (model.getCurday()==null||model.getCurday() == 0 ){
            model.setCurday(DateUtil.getDayNumber(new Date()));
            model.setCurday(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }
            else if(account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }
            dataList = orderService.queryByListReleaseByCount(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, OrderModel model) throws Exception {
        List<OrderModel> dataList = new ArrayList<OrderModel>();
        if (model.getBeginCurday() == 0 || model.getEndCurday() == 0){
            model.setBeginCurday(DateUtil.getDayNumber(new Date()));
            model.setEndCurday(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = orderService.queryAllList(model);
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
        return "manager/order/orderAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, OrderModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////            check是否有重复的账号
//            OrderModel queryBean = new OrderModel();
//            queryBean.setSecretKey(bean.getSecretKey());
//            OrderModel queryBean1 = channelService.queryByCondition(queryBean);
//            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                sendFailureMessage(response,"有重复的商户秘钥,请重新输入商户秘钥!");
//            }else{
////                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
////                bean.setRoleId(ManagerEnum.RoleTypeEnum.TP.getRoleType());
////                bean.setSecretKey(MD5.parseMD5(bean.getAccountNum()));
//                channelService.add(bean);
//                sendSuccessMessage(response, "保存成功~");
//            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        ChannelModel atModel = new ChannelModel();
        atModel.setId(id);
        model.addAttribute("account", orderService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/order/orderEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,OrderModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            orderService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, OrderModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            orderService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, OrderModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            orderService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 补单
     */
    @RequestMapping("/addRepairOrder")
    public void addRepairOrder(HttpServletRequest request, HttpServletResponse response, OrderModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            OrderReplenishModel queryBean = new OrderReplenishModel();
            queryBean.setOutTradeNo(bean.getOutTradeNo());
            /**是否有重复数据**/
            OrderReplenishModel queryBean1 = orderReplenishService.queryByCondition(queryBean);
            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response,"该订单号已经在补单订单表里面，无需添加！");
            }else{
                OrderModel  quertBean  = orderService.queryByCondition(bean);
                if (quertBean != null && quertBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                    OrderReplenishModel orderReplenishModel = new OrderReplenishModel();
                    orderReplenishModel.setOrderNo(quertBean.getOrderNo());
                    orderReplenishModel.setOutTradeNo(quertBean.getOutTradeNo());
                    orderReplenishModel.setOrderStatus(3);
                    orderReplenishModel.setHandlePeople(account.getAccountNum());
                    orderReplenishService.add(orderReplenishModel);
                    sendSuccessMessage(response, "保存成功~");
                }else{
                    sendFailureMessage(response,"该ID 和订单号不匹配,不能进行补发！");
                }
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }
}
