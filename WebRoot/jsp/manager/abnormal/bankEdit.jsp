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
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="1">

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>手机号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="mobileCardId"  name="mobileCardId" class='text-input medium-input' >
                                <option value="" >===请选择===</option>
                                <c:forEach items="${mobile}" var="dataList">
                                    <c:choose>
                                        <c:when test="${dl.mobileCardId == dataList.id}">
                                            <option selected="selected" value="${dataList.id}">${dataList.phoneNum}</option>
                                        </c:when>
                                        <c:when test="${dl.mobileCardId != dataList.id}">
                                            <option value="${dataList.id}">${dataList.phoneNum}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>

                            <%--<input type="text" class="formInput" id="phoneNum" name="phoneNum" value="${dl.phoneNum}"	maxlength="240" />--%>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>银行卡账号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="bankCard" name="bankCard" value="${dl.bankCard}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>银行名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="bankTypeId" name="bankTypeId"  class='text-input medium-input' >
                                <option value="">===请选择===</option>
                                <c:forEach items="${type}" var="dataList">
                                    <c:choose>
                                        <c:when test="${dl.bankTypeId == dataList.id}">
                                            <option selected="selected" value="${dataList.id}">${dataList.bankName}</option>
                                        </c:when>
                                        <c:when test="${dl.bankTypeId != dataList.id}">
                                            <option value="${dataList.id}">${dataList.bankName}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <%--<input type="text" class="formInput" id="bankName" name="bankName" value="${dl.bankName}" disabled maxlength="240" />--%>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>开户名</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput"  id="accountName" name="accountName" value="${dl.accountName}" 	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>绑定的手机设备号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="phoneDeviceNum" name="phoneDeviceNum" value="${dl.phoneDeviceNum}" 	maxlength="240" />
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
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                bankCard:{
                    required:true,
                    maxlength:30
                }
            },
            messages: {
                bankCard:{
                    required : "银行卡账号不能为空!",
                    maxlength : "银行卡账号长度最多是30个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/bank/updateBank.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/jsp/manager/abnormal/abnormalIndex.jsp";
                        } else {
                            art.alert(data.msg);
                        }
                    },
                    error : function(data) {
                        art.alert(data.info);
                    }
                });
                return false;
                //阻止表单提交
            }
        });
    });
</script>
</body>
</html>