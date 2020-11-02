<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <style type="text/css">
        .manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
        .manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
        .manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
        <div class="formHeadDiv">
            <h2>编辑账号</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm" method="POST" enctype="multipart/form-data"
                  action='${ctxData}orderout/update.do'>
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="1">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>订单号 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="orderNo" name="orderNo" value="${dl.orderNo}" disabled 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>付款银行名称 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="inBankName" name="inBankName" value="${dl.inBankName}"	disabled maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>付款银行卡账号 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="inBankCard" name="inBankCard" value="${dl.inBankCard}" disabled	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>付款开户名 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="inAccountName" name="inAccountName" value="${dl.inAccountName}" disabled	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>订单金额 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="orderMoney" name="orderMoney" value="${dl.orderMoney}" disabled	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>订单类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="orderType" name="orderType" disabled>
                                <c:if test="${dl.orderType == 1}">
                                    <option value="1" selected="selected">手动转账</option>
                                    <option value="2">API转账</option>
                                </c:if>
                                <c:if test="${dl.orderType == 2}">
                                    <option value="1" >手动转账</option>
                                    <option value="2" selected="selected">API转账</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>订单状态 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="orderStatus" name="orderStatus">
                                <c:if test="${dl.orderStatus == 1}">
                                    <option value="1" selected="selected">初始化</option>
                                    <option value="2">失败</option>
                                    <%--<option value="3">质疑</option>--%>
                                    <option value="4">成功</option>
                                </c:if>
                                <c:if test="${dl.orderStatus == 2}">
                                    <option value="1" >初始化</option>
                                    <option value="2" selected="selected">失败</option>
                                    <%--<option value="3">质疑</option>--%>
                                    <option value="4">成功</option>
                                </c:if>
                                <%--<c:if test="${dl.orderStatus == 3}">--%>
                                    <%--<option value="1" >初始化</option>--%>
                                    <%--<option value="2" >失败</option>--%>
                                    <%--<option value="3" selected="selected">质疑</option>--%>
                                    <%--<option value="4">成功</option>--%>
                                <%--</c:if>--%>
                                <c:if test="${dl.orderStatus == 4}">
                                    <option value="1" >初始化</option>
                                    <option value="2" >失败</option>
                                    <%--<option value="3" >质疑</option>--%>
                                    <option value="4" selected="selected">成功</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >数据说明</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="failInfo" name="failInfo"	value="${dl.failInfo}" maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>转账凭证</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="file" accept="files/*" name="files" id="files"/>
                        </div>
                    </li>


                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" class="formBtn" style="background-color: #54D8FE" value="修 改" /> <span>
						</span>
                            <input type="button" onClick="javascript :history.back(-1);" style="background-color: #54D8FE" class="formBtn" value=" 返 回 " />
                        </div>
                    </li>
                </ul>
            </form>
        </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    // $(function(){
    //     //密码输入验证
    //     $("#addSupplierForm").validate({
    //         rules:{
    //             alias:{
    //                 required:true,
    //                 maxlength:20
    //             },
    //             secretKey:{
    //                 required:true,
    //                 maxlength:20
    //             },
    //             useStatus:{
    //                 required:true,
    //                 maxlength:1
    //             }
    //         },
    //         messages: {
    //             alias:{
    //                 required : "商户名称为空!",
    //                 maxlength : "商户名称最多是20个字符!"
    //             },
    //             secretKey:{
    //                 required : "商户秘钥不能为空!",
    //                 maxlength : "商户秘钥最多是20个字符!"
    //             },
    //             useStatus:{
    //                 required:"使用状态不能为空!",
    //                 number:"使用状态最多是20个字符!"
    //             }
    //         },
    //
    //         submitHandler : function() {
    //             var formData = $("#addSupplierForm").serialize();
    //             $.ajax({
    //                 url : ctx+ "/channel/update.do",
    //                 type : 'post',
    //                 dataType : 'json',
    //                 data :formData,
    //                 success : function(data) {
    //                     if (data.success) {
    //                         alert("修改成功！");
    //                         window.location.href = ctx + "/channel/list.do";
    //                     } else {
    //                         art.alert(data.msg);
    //                     }
    //                 },
    //                 error : function(data) {
    //                     art.alert(data.info);
    //                 }
    //             });
    //             return false;
    //             //阻止表单提交
    //         }
    //     });
    // });
</script>
</body>
</html>