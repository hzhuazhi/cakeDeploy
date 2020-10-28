package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.ChannelBankModel;
import com.xn.manager.model.ChannelModel;
import com.xn.manager.service.ChannelBankService;
import com.xn.manager.service.ChannelService;
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
 * @Description 商户信息
 * @Date 2020/9/11 14:01
 * @Version 1.0
 */
@Controller
@RequestMapping("/channelbank")
public class ChannelBankController extends BaseController {

    private static Logger log = Logger.getLogger(ChannelBankController.class);

    @Autowired
    private ChannelBankService<ChannelBankModel> channelBankService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/channelbank/channelbankIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelBankModel model) throws Exception {
        List<ChannelBankModel> dataList = new ArrayList<ChannelBankModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                //不是管理员，只能查询自己的数据
//                model.setId(account.getId());
//            }
            List<ChannelBankModel> dataListInfo = channelBankService.queryByList(model);


            for(ChannelBankModel channelBankModel:dataListInfo){

                String bankCardInfo  =  channelBankService.byIdQueryBankCard(channelBankModel.getId());
                channelBankModel.setBankCardInfo(bankCardInfo);
                dataList.add(channelBankModel);
            }

        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, ChannelBankModel model) throws Exception {
        List<ChannelBankModel> dataList = new ArrayList<ChannelBankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = channelBankService.queryAllList(model);
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
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
//                sendFailureMessage(response,"只允许管理员操作!");
//            }else {
////                model.addAttribute("agent", agentService.queryAllList());
//            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/channelbank/channelbankAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, ChannelBankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
            ChannelBankModel queryBean = new ChannelBankModel();

            if(StringUtils.isBlank(bean.getBankIds())){
                sendFailureMessage(response,"请选中要添加的银行卡号，再进行添加！");
            }else{
                String [] bankId = bean.getBankIds().split(",");
                for(String str:bankId){
                    queryBean.setBankId(Long.parseLong(str));
                    ChannelBankModel queryBean1=channelBankService.queryByCondition(queryBean);
                    if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                        continue;
                    }else{
                        ChannelBankModel addModel  =  new  ChannelBankModel();
                        addModel.setBankId(Long.parseLong(str));
                        addModel.setChannelId(bean.getChannelId());
                        channelBankService.add(addModel);
                    }
                }
            }

            sendSuccessMessage(response, "保存成功~");
//            queryBean.setSecretKey(bean.getSecretKey());
//            ChannelModel queryBean1 = channelService.queryByCondition(queryBean);
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
        model.addAttribute("account", channelBankService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/channelbank/channelbankEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,ChannelBankModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelBankService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, ChannelBankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelBankService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, ChannelBankModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            channelBankService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }





    @RequestMapping("/jumpBankUpdate")
    public String jumpBankUpdate(Model model, long id, Integer op) {
        ChannelBankModel atModel = new ChannelBankModel();
        atModel.setChannelId(id);
        model.addAttribute("account", atModel);
        return "manager/channelbank/channelbankQuery";
    }


    /**
     * 根据 条件查询 该条件下的银行卡信息
     */
    @RequestMapping("/query")
    public void queryIdList(HttpServletRequest request, HttpServletResponse response, ChannelBankModel model) throws Exception {
        List<ChannelBankModel> dataList = new ArrayList<ChannelBankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            ChannelBankModel  queryBean = new ChannelBankModel();
            queryBean.setChannelId(model.getChannelId());
            dataList = channelBankService.byIdQueryBank(queryBean);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     * 根据 条件查询 该条件下的银行卡信息
     */
    @RequestMapping("/queryNotChannelBankList")
    public void queryNotChannelBankList(HttpServletRequest request, HttpServletResponse response, ChannelBankModel model) throws Exception {
        List<ChannelBankModel> dataList = new ArrayList<ChannelBankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            queryBean.setId(model.getId());

            ChannelBankModel  queryBean = new ChannelBankModel();
            dataList = channelBankService.queryByAll(queryBean);
            List<Long>   bankIdList = new ArrayList<>();
            for(ChannelBankModel channelBankModel:dataList){
                bankIdList.add(channelBankModel.getBankId());
            }
            queryBean.setBankIdList(bankIdList);
            dataList = channelBankService.queryNotChannelBankAll(queryBean);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
