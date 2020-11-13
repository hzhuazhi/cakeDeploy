package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.OssUploadUtil;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantRechargeModel;
import com.xn.manager.model.OrderOutModel;
import com.xn.manager.model.StrategyModel;
import com.xn.manager.service.OrderOutService;
import com.xn.manager.service.StrategyService;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description TODO
 * @Date 2020/9/21 18:17
 * @Version 1.0
 */

@Controller
@RequestMapping("/orderout")
public class OrderOutController extends BaseController {
    private static Logger log = Logger.getLogger(OrderOutController.class);

    @Autowired
    private OrderOutService<OrderOutModel> orderOutService;

    @Autowired
    private StrategyService<StrategyModel> strategyService;


    private  int  defaultTime =10;
    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/orderout/orderoutIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, OrderOutModel model) throws Exception {
        List<OrderOutModel> dataList = new ArrayList<OrderOutModel>();
        if (model.getCurdayStart()==null||model.getCurdayEnd()==null||model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
            model.setCurdayStart(DateUtil.getDayNumber(new Date()));
            model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setAccountNum(account.getAccountNum());
            }
            dataList = orderOutService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, OrderOutModel model) throws Exception {
        List<OrderOutModel> dataList = new ArrayList<OrderOutModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            dataList = orderOutService.queryAllList(model);
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
            model.addAttribute("op", account);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/orderout/orderoutAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, OrderOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
//            OrderOutModel queryBean = new OrderOutModel();
//            queryBean.setAccountNum(bean.getAccountNum());
//            MerchantSiteModel queryBean1 = orderOutService.queryByCondition(queryBean);
//            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//                sendFailureMessage(response,"有重复账户了，请重新填写！");
//            }else{
//                bean.setMerchantId(account.getId());
//                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
//                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
//                bean.setCreateRoleId(account.getRoleId());
//                bean.setCreateUserId(account.getId());
//                bean.setRoleId(3);
//                orderOutService.add(bean);
//                sendSuccessMessage(response, "保存成功~");
//            }
        }else {

        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        MerchantRechargeModel atModel = new MerchantRechargeModel();
        atModel.setId(id);
        model.addAttribute("account", orderOutService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/orderout/orderoutEdit";
    }



    @RequestMapping("/chechData")
    public void chechData(HttpServletRequest request, HttpServletResponse response, MerchantRechargeModel model) throws Exception {
        Map<String,String> map  = new HashMap<>() ;
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            OrderOutModel atModel = new OrderOutModel();
            atModel.setId(model.getId());
            OrderOutModel merchantModel  = orderOutService.queryById(atModel);
            if(merchantModel.getOperateStatus()==2){
                map.put("type","2");
                map.put("rs","订单已经在处理中了，请处理其他订单,如果想处理，请点击 强行处理！");
                HtmlUtil.writerJson(response, map);
            }
            OrderOutModel  upOrderOutModel  = new OrderOutModel();
            upOrderOutModel.setId(model.getId());
            upOrderOutModel.setOperateStatus(2);
            orderOutService.update(upOrderOutModel);
            map.put("type","1");
            map.put("rs","/orderout/jumpUpdate.do?op=1&id="+model.getId());
            HtmlUtil.writerJson(response, map);
        }else{
            map.put("type","2");
            map.put("rs","登录超时,请重新登录在操作！");
            HtmlUtil.writerJson(response, map);
        }

    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, OrderOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            OrderOutModel   orderOutModel  =   new   OrderOutModel();
            orderOutModel.setId(bean.getId());

            String pictureAds = "";
            if (!files.isEmpty()){
                pictureAds = OssUploadUtil.localMethod(files);
                if (StringUtils.isBlank(pictureAds)){
                    sendFailureMessage(response, "图片上传失败,请重试!");
                    return;
                }
                orderOutModel.setPictureAds(pictureAds);
            }

            StrategyModel  strategyModel  = new  StrategyModel();
            strategyModel.setId(11);
            StrategyModel  strategyModelCond = strategyService.queryByCondition(strategyModel);

            if(bean.getOrderStatus()==4){
                if(strategyModelCond!=null){ //超时时间
                    Date afterDate = new Date(new Date().getTime() + strategyModelCond.getStgNumValue()*60*1000);
                    orderOutModel.setInvalidTime(afterDate);
                }else{
                    Date afterDate = new Date(new Date().getTime() + defaultTime*60*1000);
                    orderOutModel.setInvalidTime(afterDate);
                }
                if(pictureAds!=""){
                    orderOutModel.setPictureAds(pictureAds);
                }else{
//                    sendFailureMessage(response, "为成功状态的必须需要上传，转账凭证！");
                }
            }

            orderOutModel.setFailInfo(bean.getFailInfo());
            orderOutModel.setOrderStatus(bean.getOrderStatus());
            orderOutModel.setUpdateUserId(account.getId());
            orderOutModel.setUpdateRoleId(account.getRoleId());
            orderOutService.update(orderOutModel);
            sendSuccessMessage(response, "修改成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, OrderOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            orderOutService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, OrderOutModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            orderOutService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}
