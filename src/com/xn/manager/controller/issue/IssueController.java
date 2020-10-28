package com.xn.manager.controller.issue;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.OssUploadUtil;
import com.xn.manager.model.IssueModel;
import com.xn.manager.service.IssueService;
import com.xn.system.entity.Account;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 下发的Controller层
 * @Author yoko
 * @Date 2020/9/27 15:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/issue")
public class IssueController extends BaseController {

    private static Logger log = Logger.getLogger(IssueController.class);


    @Autowired
    private IssueService<IssueModel> issueService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/issue/issueIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, IssueModel model) throws Exception {
        List<IssueModel> dataList = new ArrayList<IssueModel>();
        if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
            model.setCurdayStart(DateUtil.getDayNumber(new Date()));
            model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = issueService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, IssueModel model) throws Exception {
        List<IssueModel> dataList = new ArrayList<IssueModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = issueService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        return "manager/issue/issueAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, IssueModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            issueService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        IssueModel atModel = new IssueModel();
        atModel.setId(id);
        model.addAttribute("account", issueService.queryById(atModel));
        return "manager/issue/issueEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, IssueModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            issueService.update(bean);
            String pictureAds = OssUploadUtil.localMethod(files);
            if (StringUtils.isBlank(pictureAds)){
                sendFailureMessage(response, "图片上传失败,请重试!");
                return;
            }else {
                bean.setOrderStatus(3);
                bean.setPictureAds(pictureAds);
                bean.setCheckStatus(0);
                bean.setCheckStatusStr("1");
            }
            issueService.updateOrderStatus(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, IssueModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            issueService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, IssueModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            issueService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取数据的详情
     */
    @RequestMapping("/jumpInfo")
    public String jumpInfo(Model model, long id) {
        IssueModel atModel = new IssueModel();
        atModel.setId(id);
        model.addAttribute("account", issueService.queryById(atModel));
        return "manager/issue/issueInfo";
    }
}
