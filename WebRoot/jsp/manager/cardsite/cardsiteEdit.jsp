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
                            <span class="require" ><font color="red">*</font>卡站点名称 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acName" name="acName"  value="${dl.acName}"	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>账号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountNum" name="accountNum" value="${dl.accountNum}" maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>密码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd" name="passWd"  	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>账号联系人</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acContacts" name="acContacts" value="${dl.acContacts}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>联系电话</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acPhone" name="acPhone" value="${dl.acPhone}"	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>使用状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isEnable" name="isEnable">
                                <option value="" >===请选择===<option>
                                <%--<c:if test="${dl.isEnable == 0}">--%>
                                    <%--<option value="0" selected>===请选择===</option>--%>
                                    <%--<option value="1">暂停使用</option>--%>
                                    <%--<option value="2">正常状态</option>--%>
                                <%--</c:if>--%>
                                <c:if test="${dl.isEnable == 1}">
                                    <option value="1" selected>暂停使用</option>
                                    <option value="2">正常状态</option>
                                </c:if>
                                <c:if test="${dl.isEnable == 2}">
                                    <option value="1" >暂停使用</option>
                                    <option value="2"selected>正常状态</option>
                                </c:if>
                            </select>
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
                acName:{
                    required:true,
                    maxlength:20
                },
                accountNum:{
                    required:true,
                    maxlength:20
                },

                acContacts:{
                    required:true,
                    maxlength:20
                },
                acPhone:{
                    required:true,
                    maxlength:20
                },
                isEnable:{
                    required:true,
                    maxlength:1
                }
            },
            messages: {
                acName:{
                    required : "卡站点名称为空!",
                    maxlength : "卡站点名称最多是20个字符!"
                },
                accountNum:{
                    required : "账号不能为空!",
                    maxlength : "账号最多是20个字符!"
                },
                acContacts:{
                    required : "账号联系人不能为空!",
                    maxlength : "账号联系人最多是20个字符!"
                },
                acPhone:{
                    required:"联系电话不能为空!",
                    number:"联系电话最多是20个字符!"
                },
                isEnable:{
                    required:"请选择状态不能为空!",
                    number:"请选择状态不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/cardsite/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/cardsite/list.do";
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