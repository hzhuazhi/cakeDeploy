package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.ExcelUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.*;
import com.xn.manager.service.BankTypeService;
import com.xn.manager.service.CardsBankService;
import com.xn.manager.service.MobileCardService;
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
import java.util.List;

/**
 * @Description 卡商银行卡基础表
 * @Date 2020/9/11 22:20
 * @Version 1.0
 */

@Controller
@RequestMapping("/cardsbank")
public class CardsBankController extends BaseController {
    private static Logger log = Logger.getLogger(CardsBankController.class);

    @Autowired
    private CardsBankService<CardsBankModel> cardsBankService;




    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/cardsbank/cardsbankIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, CardsBankModel model) throws Exception {
        List<CardsBankModel> dataList = new ArrayList<CardsBankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
//                model.geta(account.getId());
            }
            dataList = cardsBankService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, CardsBankModel model) throws Exception {
        List<CardsBankModel> dataList = new ArrayList<CardsBankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
//                model.setId(account.getId());
            }
            dataList = cardsBankService.queryAllList(model);
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
        return "manager/cardsbank/cardsbankAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, CardsBankModel bean) throws Exception {
//        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            Integer  type = cardsBankService.isCheckCardsBank(bean,account);
//            bean.setMerchantId(account.getId());
//            if(type==0){
//                cardsBankService.add(bean);
//                sendSuccessMessage(response, "保存成功~");
//            }else if(type==1){
//                sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
//            }else if(type==2){
//                sendFailureMessage(response,"手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加！");
//            }else if(type==3){
//                sendFailureMessage(response,"银行卡类型未部署，请联系管理员进行部署，再进行添加！");
//            }
////            CardsBankModel queryBean = new CardsBankModel();
////            queryBean.setBankCard(bean.getBankCard());
////            CardsBankModel queryBeanRs = cardsBankService.isCheckCardsBank(queryBean);
////            if (queryBeanRs != null && queryBeanRs.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////                sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
////            }else{
////                MobileCardModel mobileCardModel = new MobileCardModel();
////                mobileCardModel.setPhoneNum(bean.getPhoneNum());
////                MobileCardModel queryMobileBean = mobileCardService.queryByCondition(mobileCardModel);
////
////                if(queryMobileBean != null && queryMobileBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////                    BankTypeModel queryBankTypeBean = new BankTypeModel();
////                    queryBankTypeBean.setBankName(bean.getBankName());
////                    BankTypeModel queryBean1 = bankTypeService.queryByCondition(queryBankTypeBean);
////                    if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////
////                    }else{
////                        sendFailureMessage(response,"银行卡类型未部署，请联系管理员进行部署，再进行添加！");
////                    }
////                }else{
////                    sendFailureMessage(response,"手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加！");
////                }
////            }
//        }else {
//            sendFailureMessage(response,"登录超时,请重新登录在操作!");
//        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        CardsBankModel atModel = new CardsBankModel();
        atModel.setId(id);
        model.addAttribute("account", cardsBankService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/cardsbank/cardsbankEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,CardsBankModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if ("2".equals(op)) {
//                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
//            }
            cardsBankService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, CardsBankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            cardsBankService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, CardsBankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            cardsBankService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * @Description: TODO(导入Excel)
     * @author df
     * @param file-要导入的文件
     * @create 16:04 2018/10/23
     **/
    @ResponseBody
    @RequestMapping("/importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file) {
        try {
            if(file == null  || file.getInputStream() == null || StringUtils.isBlank(file.getName())){

            }
            List<Object> list =  ExcelUtil.importDataFromExcel(new TestExecl(),file.getInputStream(),file.getOriginalFilename());
            List<TestExecl> importList = BeanUtils.copyList(list, TestExecl.class);
            for(TestExecl testExecl:importList){
                System.out.println(testExecl.getId());
                System.out.println(testExecl.getName());
                System.out.println(testExecl.getAge());
                System.out.println(testExecl.getSex());
                System.out.println("");
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }



}
