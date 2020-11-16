package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.ExcelUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
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
import java.util.List;
import java.util.Map;

/**
 * @Description 充值表信息
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/changemoney")
public class ChangeMoneyController extends BaseController {

    private static Logger log = Logger.getLogger(ChangeMoneyController.class);

    @Autowired
    private ChangeMoneyService<ChangeMoneyModel> changeMoneyService;



    @Autowired
    private MerchantService<MerchantModel> merchantService;

    @Autowired
    private InterestService<InterestModel> interestService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/changemoney/changemoneyIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChangeMoneyModel model) throws Exception {
        List<ChangeMoneyModel> dataList = new ArrayList<ChangeMoneyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            dataList = changeMoneyService.queryByList(model);
            if(dataList.size()!=0){
                List<InterestModel>  interestModelList = interestService.queryAllList();
                List<MerchantModel>  merchantModelList = merchantService.queryAllList();
                for(ChangeMoneyModel  changeMoneyModel:dataList){
                    changeMoneyModel.setAscriptionName(changeMoneyService.queryAscriptionName(changeMoneyModel.getAscriptionType(),changeMoneyModel.getAscriptionId().intValue(),merchantModelList,interestModelList));

                }
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChangeMoneyModel model) throws Exception {
        List<ChangeMoneyModel> dataList = new ArrayList<ChangeMoneyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            dataList = changeMoneyService.queryAllList(model);
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
            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"只有管理员能操作!");
            }
            ChangeMoneyModel  mobileCardModel = new  ChangeMoneyModel();
            model.addAttribute("mobile",changeMoneyService.queryAllList(mobileCardModel));
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/changemoney/changemoneyAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChangeMoneyModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()!=ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response,"只有管理员能操作!");
            }
            changeMoneyService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }



    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/queryTypeAllList")
    public void queryTypeAllList(HttpServletRequest request, HttpServletResponse response, ChangeMoneyModel model) throws Exception {
        List<ChangeMoneyModel> dataList = new ArrayList<ChangeMoneyModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){

            dataList = changeMoneyService.queryAscriptionInfo(model.getAscriptionType());
        }
        HtmlUtil.writerJson(response, dataList);
    }


}
