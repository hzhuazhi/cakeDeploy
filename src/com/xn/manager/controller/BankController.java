package com.xn.manager.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
@RequestMapping("/bank")
public class BankController extends BaseController {

    private static Logger log = Logger.getLogger(BankController.class);

    @Autowired
    private BankService<BankModel> bankService;

    @Autowired
    private BankChangeService<BankChangeModel> bankChangeService;

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
     *
     * 获取需要替换的卡表格数据列表
     */
    @RequestMapping("/queryReplaceList")
    public void queryReplaceList(HttpServletRequest request, HttpServletResponse response, BankModel model) throws Exception {
        List<BankModel> dataList = new ArrayList<BankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setMerchantId(account.getId());
            }
            model.setStatusTwo(2);
            model.setStatusThree(3);
            model.setChangeTime(DateUtil.getNowPlusTime());
            dataList = bankService.queryUpByList(model);
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

            Map<String, Object>   bankMap= bankService.isCheckCardsBank(bean,account);
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
            model.addAttribute("mobile",mobileCardService.queryAllList(mobileCardModel));
            model.addAttribute("account", bankService.queryById(atModel));
            model.addAttribute("op", op);
            model.addAttribute("type",bankTypeService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }

        return "manager/abnormal/bankEdit";
    }

    /**
     * 修改卡号数据
     */
    @RequestMapping("/updateBank")
    public void updateBank(HttpServletRequest request, HttpServletResponse response,BankModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//
            BankChangeModel   bankChangeModel  = new  BankChangeModel();
            bankChangeModel.setBankCard(bean.getBankCard());
            BankChangeModel queryBean1 =  bankChangeService.queryByCondition(bankChangeModel);
            if(queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response, "该卡已经部署过了，不能重复保存，请重新换卡");
            }else{

                BankTypeModel bankTypeModel =new  BankTypeModel();
                bankTypeModel.setId(bean.getBankTypeId());
                List<BankTypeModel> typeList = bankTypeService.queryAllList(bankTypeModel);
                //修改卡状态，修改卡号，和尾号、设备号
                BankModel    bankModel   =new BankModel();
                bankModel.setId(bean.getId());
                bankModel.setBankCard(bean.getBankCard());
                String  lastNum =bean.getBankCard().substring((bean.getBankCard().length()-4),bean.getBankCard().length());
                bankModel.setLastNum(lastNum);
                bankModel.setPhoneDeviceNum(bean.getPhoneDeviceNum());
                bankModel.setChangeStatus(4);
                bankModel.setCheckChange("");

                BankChangeModel  addBankChangeModel  = new  BankChangeModel();
                addBankChangeModel.setMerchantId(account.getId());
                addBankChangeModel.setBankId(bean.getId());
                addBankChangeModel.setBankCard(bean.getBankCard());
                addBankChangeModel.setBankName(typeList.get(0).getBankName());
                addBankChangeModel.setAccountName(bean.getAccountName());
                addBankChangeModel.setPhoneDeviceNum(bean.getPhoneDeviceNum());
                addBankChangeModel.setCurday(DateUtil.getDayNumber(new Date()));
                addBankChangeModel.setCurhour(DateUtil.getHour(new Date()));
                addBankChangeModel.setCurminute(DateUtil.getCurminute(new Date()));
                bankService.update(bankModel);
                bankChangeService.add(addBankChangeModel);
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


    /**
     * @Description: TODO(导入Excel)
     * @author df
     * @param file-要导入的文件
     * @create 16:04 2018/10/23
     **/
    @ResponseBody
    @RequestMapping("/importExcel")
    public void importExcel(HttpServletRequest request, HttpServletResponse response,@RequestParam("file") MultipartFile file) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            String    rsString  =  "";
            List<BankModel>     addList =  new ArrayList<>();

            try {
                if(file == null  || file.getInputStream() == null || StringUtils.isBlank(file.getName())){
                    sendFailureMessage(response, "该文件为空，请上传正确的文件！");
                }
                List<Object> list =  ExcelUtil.importDataFromExcel(new BankExcelModel(),file.getInputStream(),file.getOriginalFilename());
                List<BankExcelModel> importList = BeanUtils.copyList(list, BankExcelModel.class);
                boolean  flag = true ;
                String     errorString    = "";

                MerchantModel  queryModel = new MerchantModel();
                queryModel.setAccountNum(account.getAccountNum());

                MerchantModel beans = merchantService.queryByCondition(queryModel);
                if(beans==null||beans.getId()<=0){
                    sendSuccessMessage(response, "数据有误，请联系管理员！");
                    return;
                }

                for(BankExcelModel testExecl:importList){

                    BankModel bean= new BankModel();
                    BeanUtils.copy(testExecl,bean);

                    Map<String, Object>   bankMap= bankService.isCheckCardsBank(bean,account);
                    if(bankMap.get("type").equals("0")){//成功的结果
                        addList.add((BankModel)bankMap.get("bankModel"));
                    }else if(bankMap.get("type").equals("1")){
                        flag=false;
                        errorString+="卡号为"+bean.getBankCard()+"的数据：有重复的银行卡账号,请重新输入其它银行卡账号! \n";
//                        sendFailureMessage(response,"有重复的银行卡账号,请重新输入其它银行卡账号!");
                    }else if(bankMap.get("type").equals("2")){
                        errorString+="卡号为"+bean.getBankCard()+"的数据：手机号未在“手机卡管理”进行部署,请先将手机号部署好再进行添加! \n";
//                        sendFailureMessage(response,"");
                        flag=false;
                    }else if(bankMap.get("type").equals("3")){
                        errorString+="卡号为"+bean.getBankCard()+"的数据：银行卡类型未部署，请联系管理员进行部署，再进行添加! \n";
                        flag=false;
//                        sendFailureMessage(response,"");
                    }
                }

                if(!flag){
                    sendFailureMessage(response, errorString);
                }

                for(BankModel bankModel:addList){
//                    bankModel.setMerchantId(beans.getId());
                    bankService.add(bankModel);
                }
                sendSuccessMessage(response, "导入成功");
            } catch (Exception e) {
                e.printStackTrace();
                sendFailureMessage(response, "该文件未按照模板模板规格进行编写，请按照模板规格填写后，重新上传！");
            }
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /***
     * 获取卡商信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/queryBankType")
    public void queryAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<BankTypeModel> dataList = new ArrayList<BankTypeModel>();
        dataList = bankTypeService.queryAllList();
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    @RequestMapping("/exportData")
    public void exportData(HttpServletRequest request, HttpServletResponse response, BankModel model) {
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
