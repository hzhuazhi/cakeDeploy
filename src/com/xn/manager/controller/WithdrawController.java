package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantRechargeModel;
import com.xn.manager.model.WithdrawModel;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.WithdrawService;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 卡商充值Controller
 * @Date 2020/9/15 20:08
 * @Version 1.0
 */

@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelBankController.class);

    @Autowired
    private MerchantService<MerchantModel> merchantService;

    @Autowired
    private WithdrawService<WithdrawModel> withdrawService;

    @Autowired
    private AccountService<Account> accountService;//管理员

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/withdraw/withdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, WithdrawModel model) throws Exception {
        List<WithdrawModel> dataList = new ArrayList<WithdrawModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
            //不是管理员，只能查询自己的数据
            model.setMerchantId(account.getId());
        }
        dataList = withdrawService.queryByList(model);
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, WithdrawModel model) throws Exception {
        List<WithdrawModel> dataList = new ArrayList<WithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            dataList = withdrawService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model,Integer id) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
//                sendFailureMessage(response,"只允许管理员操作!");
//            }else {
                MerchantModel query  = new MerchantModel();
                query.setId(id);
                MerchantModel  queryModel = merchantService.queryByCondition(query);

                List<MerchantModel>  list = new ArrayList<MerchantModel>();
                list.add(queryModel);
                list   = merchantService.queryListInfo(list);

                model.addAttribute("model", account);
                model.addAttribute("account", list.get(0));
//            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/withdraw/withdrawAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
            WithdrawModel queryBean = new WithdrawModel();
            String    orderNo   ="TX"+ DateUtil.getNowPlusTimeMill();
            bean.setOrderNo(orderNo);
            withdrawService.add(bean);
                sendSuccessMessage(response, "保存成功~");
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
        MerchantRechargeModel atModel = new MerchantRechargeModel();
        atModel.setId(id);

        model.addAttribute("account", withdrawService.queryById(atModel));
        model.addAttribute("op", op);
        model.addAttribute("ks", accountService.queryByList(new AccountModel()));
        return "manager/withdraw/withdrawEdit";

    }



    @RequestMapping("/chechData")
    public void chechData(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel model) throws Exception {
        Map<String,String> map  = new HashMap<>() ;
        WithdrawModel   listMerchantRecharge=withdrawService.queryById(model);
        if(null==listMerchantRecharge){
            map.put("type","3");
            map.put("rs","该订单已经失效！");
            HtmlUtil.writerJson(response, map);
        }

        if(listMerchantRecharge.getOrderStatus()!=1){
            map.put("type","2");
            map.put("rs","订单已经在处理中了，请处理其他订单！");
            HtmlUtil.writerJson(response, map);
        }else{
            WithdrawModel  merchantRechargeModel = new WithdrawModel();
            merchantRechargeModel.setId(model.getId());
            merchantRechargeModel.setOrderStatus(4);
            withdrawService.update(merchantRechargeModel);
            map.put("rs","/merchantrecharge/jumpUpdate.do?op=1&id="+model.getId());
            map.put("type","1");
            HtmlUtil.writerJson(response, map);
        }

    }





    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,WithdrawModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        SpringConfig springConfig=new SpringConfig();
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            WithdrawModel  withdrawModel =  new WithdrawModel();

            withdrawService.update(withdrawModel);

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            withdrawService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, WithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            withdrawService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}
