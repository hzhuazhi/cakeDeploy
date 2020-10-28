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
                            <span class="require" ><font color="red">*</font>手机卡名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="cardName" name="cardName"	value="${dl.cardName}" maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>归属人的名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="useName" name="useName"	value="${dl.useName}" maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>手机号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="phoneNum" name="phoneNum"	value="${dl.phoneNum}"  maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >是否欠费</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isArrears" name="isArrears" >
                                <c:if test="${dl.isArrears == 1}">
                                    <option value="1" selected>未欠费</option>
                                    <option value="2">欠费</option>
                                </c:if>
                                <c:if test="${dl.isArrears == 2}">
                                    <option value="1" >未欠费</option>
                                    <option value="2" selected>欠费</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>备注</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="remark" name="remark" value="${dl.remark}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require">使用状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="useStatus" name="useStatus" >
                                <c:if test="${dl.useStatus == 1}">
                                    <option value="1" selected>正常使用</option>
                                    <option value="2">暂停使用</option>
                                </c:if>
                                <c:if test="${dl.useStatus == 2}">
                                    <option value="1" >正常使用</option>
                                    <option value="2" selected>暂停使用</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <%--<li style="border-top: none;">--%>
                        <%--<div class="formTextDiv">--%>
                            <%--<span class="require">是否有效</span>--%>
                        <%--</div>--%>
                        <%--<div class="formCtrlDiv">--%>
                            <%--<select id="yn" name="yn" >--%>
                                <%--<c:if test="${dl.yn == 0}">--%>
                                    <%--<option value="0" selected>有效</option>--%>
                                    <%--<option value="1">无效</option>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${dl.yn == 1}">--%>
                                    <%--<option value="0" >有效</option>--%>
                                    <%--<option value="1" selected>无效</option>--%>
                                <%--</c:if>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</li>--%>

                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" style="background-color:#0a1964" class="formBtn" value="修 改" /> <span>
						</span> <input type="reset" style="background-color:#0a1964" value="重  置" class="formBtn"/>
                            <input type="button" onClick="javascript :history.back(-1);" class="formBtn" style="background-color:#0a1964" value=" 返 回 " />
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
                cardName:{
                    required:true,
                    maxlength:20
                },
                useName:{
                    required:true,
                    maxlength:20
                },
                phoneNum:{
                    required:true,
                    maxlength:20
                },
                remark:{
                    required:false,
                    maxlength:20
                },
                useStatus:{
                    required:true,
                    maxlength:20
                },
                yn:{
                    required:true,
                    maxlength:20
                }
            },
            messages: {
                cardName:{
                    required : "手机卡名称不能为空!",
                    maxlength : "手机卡名称最多是20个字符!"
                },
                useName:{
                    required:"归属人的名称不能为空!",
                    number:"归属人的名称长度最多是20个字符!"
                },
                phoneNum:{
                    required:"手机号不能为空!",
                    number:"手机号长度最多是20个字符!"
                },
                isArrears:{
                    required:"是否欠费不能为空!",
                    number:"是否欠费不能为空!"
                },
                remark:{
                    required:"备注不能为空!",
                    number:"备注不能为空!"
                },
                useStatus:{
                    required:"使用状态不能为空!",
                    number:"使用状态不能为空!"
                },
                yn:{
                    required:"是否有效不能为空!",
                    number:"是否有效不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/mobilecard/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/mobilecard/list.do";
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