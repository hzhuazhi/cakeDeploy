package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.BeanUtils;
import com.xn.common.util.DateUtil;
import com.xn.common.util.ExportData;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantProfitModel;
import com.xn.manager.service.MerchantProfitService;
import com.xn.manager.service.MerchantService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 卡商收益的Controller层
 * @Author yoko
 * @Date 2020/12/9 17:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/merchantprofit")
public class MerchantProfitController extends BaseController {

    private static Logger log = Logger.getLogger(MerchantProfitController.class);

    @Autowired
    private MerchantProfitService<MerchantProfitModel> merchantProfitService;


    @Autowired
    private MerchantService<MerchantModel> merchantService;

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantprofit/merchantprofitIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantProfitModel model) throws Exception {
        List<MerchantProfitModel> dataList = new ArrayList<MerchantProfitModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", MerchantProfitService.getTotalData(model));
            dataList = merchantProfitService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }







    /**
     * 按照Excel报表导出数据
     */
    @RequestMapping("/exportData")
    public void exportDataNew(HttpServletRequest request, HttpServletResponse response, MerchantProfitModel model) throws Exception {
        exportData(request,response,model);
    }


    /**
     * 实际导出Excel
     * @param request
     * @param response
     * @param model
     */
    public void exportData(HttpServletRequest request, HttpServletResponse response, MerchantProfitModel model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                model.setMerchantId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
            List<MerchantProfitModel> dataList = new ArrayList<MerchantProfitModel>();
            dataList = merchantProfitService.queryAllList(model);
            // 导出数据
            String[] titles = new String[9];
            String[] titleCode = new String[9];
            String filename = "卡商收益信息";
//            titles = new String[]{"代理名称", "渠道名称", "平台订单", "订单金额", "实际支付金额", "手续费", "收益分成", "收益", "创建时间"};
//            titleCode = new String[]{"agentName", "channelName", "myTradeNo", "totalAmount", "payAmount", "serviceCharge", "profitRatio", "profit", "createTime"};
            titles = new String[]{"卡商名称", "订单号", "订单类型", "订单金额", "派单金额", "收益分成", "收益", "补单类型", "创建时间"};
            titleCode = new String[]{"merchantName", "orderNo", "orderTypeStr", "orderMoney", "distributionMoney", "profitRatio", "profit", "replenishTypeStr", "createTime"};
            List<Map<String,Object>> paramList = new ArrayList<>();
            for(MerchantProfitModel paramO : dataList){
                if (paramO.getOrderType() == 1){
                    paramO.setOrderTypeStr("代收订单");
                }else if (paramO.getOrderType() == 2){
                    paramO.setOrderTypeStr("代付订单");
                }else if (paramO.getOrderType() == 3){
                    paramO.setOrderTypeStr("下发订单");
                }

                if (paramO.getReplenishType() == 1){
                    paramO.setReplenishTypeStr("不是补单");
                }else if (paramO.getReplenishType() == 2){
                    paramO.setReplenishTypeStr("是补单");
                }

                Map<String,Object> map = BeanUtils.transBeanToMap(paramO);
                paramList.add(map);
            }
            ExportData.exportExcel(paramList, titles, titleCode, filename, response);
        }
    }

    /**
     *
     * 获取汇总数据
     */
    @RequestMapping("/totalData")
    public void totalData(HttpServletRequest request, HttpServletResponse response, MerchantProfitModel model) throws Exception {
        MerchantProfitModel data = new MerchantProfitModel();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
                model.setCurdayStart(DateUtil.getDayNumber(new Date()));
                model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
            }
//            bean.addAttribute("total", MerchantProfitService.getTotalData(model));
//            dataList = MerchantProfitService.queryByList(model);
            data = merchantProfitService.getTotalData(model);
        }
        HtmlUtil.writerJson(response, data);
    }
}
