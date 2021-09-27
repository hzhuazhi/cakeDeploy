package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.ExcelUtil;
import com.xn.manager.model.excel.OrderOutExcelInModel;
import com.xn.manager.model.replacepay.ReplacePayGainResultModel;
import com.xn.manager.service.ReplacePayGainResultService;
import com.xn.manager.util.PublicMethod;
import com.xn.system.entity.Account;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName:
 * @Description: 代付订单，导入Excel的Controller层
 * @Author: yoko
 * @Date: 2021-7-4 16:39:20
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/orderoutexcel")
public class OrderOutExcelController extends BaseController {

    private static Logger log = Logger.getLogger(OrderOutExcelController.class);

    @Autowired
    private ReplacePayGainResultService<ReplacePayGainResultModel> replacePayGainResultService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/orderoutexcel/orderoutexcelIndex";
    }




    /**
     * 代付订单结果，Excel导入
     */
    @RequestMapping("/orderOutExcelIn")
    public void orderOutExcelIn(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(files == null  || files.getInputStream() == null || StringUtils.isBlank(files.getName())){
                sendFailureMessage(response, "请选择文件上传!");
            }

            List<Object> list =  ExcelUtil.importDataFromExcelBySpecial(new OrderOutExcelInModel(),files.getInputStream(),files.getOriginalFilename());
            if(CollectionUtils.isEmpty(list)){
                sendFailureMessage(response, "列表数据为空!");
            }
            List<OrderOutExcelInModel> importList = BeanUtils.copyList(list, OrderOutExcelInModel.class);
            for (OrderOutExcelInModel orderOutExcelInModel : importList){
                log.info("创建时间：" + orderOutExcelInModel.创建时间 + ", 客户订单号：" + orderOutExcelInModel.客户订单号 + ", 客户交易名称" + orderOutExcelInModel.客户交易名称
                        + ", 交易类型：" + orderOutExcelInModel.交易类型 + ", 交易金额：" + orderOutExcelInModel.交易金额 + "付款方账号：" +orderOutExcelInModel.付款方账号 + ", 付款方账号名称：" + orderOutExcelInModel.付款方账号名称
                        + ", 收款方账户：" + orderOutExcelInModel.收款方账户 + ", 收款方名称：" + orderOutExcelInModel.收款方名称 + "手续费承担方：" +orderOutExcelInModel.手续费承担方
                        + ", 备注：" + orderOutExcelInModel.备注 + ", 订单状态：" + orderOutExcelInModel.订单状态 + ", 杉德订单号：" + orderOutExcelInModel.杉德订单号 + ", 失败原因：" + orderOutExcelInModel.失败原因);
            }
            List<ReplacePayGainResultModel> replacePayGainResultList = PublicMethod.assembleReplacePayGainResultByExcel(importList);
            if (replacePayGainResultList != null && replacePayGainResultList.size() > 0){
                // 把代付结果进行批量添加
                int num = replacePayGainResultService.addBatchResult(replacePayGainResultList);
                if (num == replacePayGainResultList.size()){
                    sendSuccessMessage(response, "导入成功~");
                }else {
                    sendFailureMessage(response, "部分导入成功，导入成功数据条数：" + num + "， 需要导入数据条数：" + replacePayGainResultList.size() + "；请联系管理员！");
                }
            }

        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}