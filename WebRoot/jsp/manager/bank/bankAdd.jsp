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
        <h2>新增账号</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>别名 格式 </span><br><span style="color: #bb0000">（手机机器编号-手机卡）</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="alias" name="alias"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>手机号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="phoneNum" name="phoneNum" class='text-input medium-input' >
                            <option value="">===请选择===</option>
                            <c:forEach items="${mobile}" var="dataList">
                                <option value="${dataList.phoneNum}">${dataList.cardName}==${dataList.phoneNum}</option>
                            </c:forEach>
                        </select>

                        <%--<input type="text" class="formInput" id="phoneNum" name="phoneNum"	maxlength="240" />--%>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="bankTypeId" name="bankTypeId" class='text-input medium-input' >
                            <option value="">===请选择===</option>
                            <c:forEach items="${type}" var="dataList">
                                 <option value="${dataList.id}">${dataList.bankName}</option>
                            </c:forEach>
                        </select>
                        <%--<input type="text" class="formInput" id="bankName" name="bankName"	maxlength="240" />--%>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行卡账号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankCard" name="bankCard"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >开户名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountName" name="accountName"	maxlength="240" />
                        </div>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >原始卡尾号后4位</span>
                    </div>
                    <div class="formCtrlDiv">
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="leadBankCard" name="leadBankCard"	maxlength="240" />
                        </div>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>收款日限金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="inDayMoney" name="inDayMoney"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>收款月限金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="inMonthMoney" name="inMonthMoney"	maxlength="240" />
                    </div>
                </li>
                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require">补充状态</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<select id="supplyType" name="supplyType" disabled>--%>
                            <%--<option value="0">===请选择===</option>--%>
                            <%--<option value="1" selected>初始化</option>--%>
                            <%--<option value="2">完成</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require">测试状态</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<select id="isTest" name="isTest" disabled>--%>
                            <%--<option value="0">===请选择===</option>--%>
                            <%--<option value="1" selected>初始化</option>--%>
                            <%--<option value="2">发送过测试</option>--%>
                            <%--<option value="3">测试失败，请重新发送 </option>--%>
                            <%--<option value="4">测试成功</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require">测试金额</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" id="test_money" name="test_money"	maxlength="240" />--%>
                    <%--</div>--%>
                <%--</li>--%>
                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="添  加" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                alias:{
                    required:true,
                    maxlength:50
                },
                phoneNum:{
                    required:true,
                    maxlength:20
                },
                bankTypeId:{
                    required:true,
                    maxlength:2
                },
                bankCard:{
                    required:true,
                    maxlength:20
                },
                accountName:{
                    required:true,
                    maxlength:20
                },
                inDayMoney:{
                    required:true,
                    maxlength:20
                },
                inMonthMoney:{
                    required:true,
                    maxlength:20
                }
            },
            messages: {
                alias:{
                    required : "别名不能为空!",
                    maxlength : "别名最多是50个字符!"
                },
                phoneNum:{
                    required : "手机号不能为空!",
                    maxlength : "手机号最多是20个字符!"
                },
                bankTypeId:{
                    required:"银行名称不能为空!",
                    number:"银行名称长度最多是22个字符!"
                },
                bankCard:{
                    required:"银行卡账号不能为空!",
                    number:"银行卡账号长度最多是20个字符!"
                },
                accountName:{
                    required:"开户名不能为空!",
                    number:"开户名长度最多是20个字符!"
                },
                inDayMoney:{
                    required:"收款日限金额不能为空!",
                    number:"收款日限金额长度最多是20个字符!"
                },
                inMonthMoney:{
                    required:"收款月限金额不能为空!",
                    number:"收款月限金额长度最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/bank/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/bank/list.do";
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