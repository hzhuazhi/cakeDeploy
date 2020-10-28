package com.xn.manager.controller;

import com.xn.common.constant.Constant;
import com.xn.common.constant.ManagerConstant;
import com.xn.common.constant.SpringConfig;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.file.FileUtils;
import com.xn.manager.model.ConfigModel;
import com.xn.manager.model.MerchantRechargeModel;
import com.xn.manager.service.MerchantRechargeService;
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
import java.io.File;
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
@RequestMapping("/merchantrecharge")
public class MerchantRechargeController extends BaseController {
    private static Logger log = Logger.getLogger(ChannelBankController.class);


    @Autowired
    private MerchantRechargeService<MerchantRechargeModel> merchantRechargeService;

    @Autowired
    private AccountService<Account> accountService;//管理员

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantrecharge/merchantrechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel model) throws Exception {
        List<MerchantRechargeModel> dataList = new ArrayList<MerchantRechargeModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }else if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setCardSiteId(account.getId());
            }
            dataList = merchantRechargeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel model) throws Exception {
        List<MerchantRechargeModel> dataList = new ArrayList<MerchantRechargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = merchantRechargeService.queryAllList(model);
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
        return "manager/merchantrecharge/merchantrechargeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
            MerchantRechargeModel queryBean = new MerchantRechargeModel();
            String    orderNo   = DateUtil.getNowPlusTimeMill();
            bean.setOrderNo(orderNo);
//            queryBean.setSecretKey(bean.getSecretKey());
//            ChannelModel queryBean1 = modelMerchantRechargeService.queryByCondition(queryBean);
//            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                sendFailureMessage(response,"有重复的商户秘钥,请重新输入商户秘钥!");
//            }else{
////                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
////                bean.setRoleId(ManagerEnum.RoleTypeEnum.TP.getRoleType());
////                bean.setSecretKey(MD5.parseMD5(bean.getAccountNum()));
                merchantRechargeService.add(bean);
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

        model.addAttribute("account", merchantRechargeService.queryById(atModel));
        model.addAttribute("op", op);
        model.addAttribute("ks", accountService.queryByList(new AccountModel()));
        return "manager/merchantrecharge/merchantrechargeEdit";

    }



    @RequestMapping("/chechData")
    public void chechData(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel model) throws Exception {
        Map<String,String> map  = new HashMap<>() ;
        MerchantRechargeModel   listMerchantRecharge=merchantRechargeService.queryById(model);
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
            MerchantRechargeModel  merchantRechargeModel = new MerchantRechargeModel();
            merchantRechargeModel.setId(model.getId());
            merchantRechargeModel.setOrderStatus(4);
            merchantRechargeService.update(merchantRechargeModel);
            map.put("rs","/merchantrecharge/jumpUpdate.do?op=1&id="+model.getId());
            map.put("type","1");
            HtmlUtil.writerJson(response, map);
        }

    }





    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MerchantRechargeModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        SpringConfig springConfig=new SpringConfig();
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MerchantRechargeModel  merchantRechargeModel =  new MerchantRechargeModel();
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能上传图片
//                if (null!=bean.getPictureFile()){
//                    merchantRechargeModel.getOrderNo();
//                    String  filename=bean.getPictureFile().getOriginalFilename();
//                    String  []   filehou = filename.split("\\.");
//                    String  name = Constant.FOREIGN_ADDRESS+bean.getOrderNo()+"."+filehou[filehou.length-1];
//
//                    merchantRechargeModel.setOrderStatus(bean.getOrderStatus());
//                    File    file =new File(Constant.STORE_ADDRESS+bean.getOrderNo()+"."+filehou[filehou.length-1]);
//                    try{
//                        FileUtils.copyFileUsingFileStreams(bean.getPictureFile().getInputStream(),file);
//                        merchantRechargeModel.setPictureAds(name);
//                        merchantRechargeModel.setOrderStatus(3);//成功
//                    }catch (Exception e){
//                        sendFailureMessage(response, "图片存储出错，请重新上传！");
//                    }
//
//                }
//            }else{
//                BeanUtils.copy(bean,merchantRechargeModel);
//                if (null!=bean.getPictureFile()){
//                    String  filename=bean.getPictureFile().getOriginalFilename();
//                    String  []   filehou = filename.split("\\.");
//                    String  name = Constant.FOREIGN_ADDRESS+bean.getOrderNo()+"."+filehou[filehou.length-1];
//                    File    file =new File(Constant.STORE_ADDRESS+bean.getOrderNo()+"."+filehou[filehou.length-1]);
//                    try{
//                        merchantRechargeModel.setPictureAds(name);
//                        FileUtils.copyFileUsingFileStreams(bean.getPictureFile().getInputStream(),file);
//                    }catch (Exception e){
//                        sendFailureMessage(response, "图片存储出错，请重新上传！");
//                    }
//                }
//            }

            merchantRechargeService.update(merchantRechargeModel);

            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantRechargeService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantRechargeService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}
