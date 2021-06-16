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
                        <span class="require" ><font color="red">*</font>公司名称 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="companyName" name="companyName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>公司地址 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="companyAds" name="companyAds"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行名称 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankName" name="bankName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行卡账号 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankCard" name="bankCard"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行支行 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="subbranchName" name="subbranchName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>开户名 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="accountName" name="accountName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>余额 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="balance" name="balance"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>可用余额 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="useBalance" name="useBalance"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>回调地址 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="inInterfaceAds" name="inInterfaceAds"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>余额地址 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="balanceInterfaceAds" name="balanceInterfaceAds"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>商户ID </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="businessNum" name="businessNum"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>平台商户ID </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="platformBusinessNum" name="platformBusinessNum"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>账号属性 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="accountAttribute" name="accountAttribute">
                            <option value="0">对私</option>
                            <option value="1">对公</option>
                        </select>
                        <%--<input type="text" class="formInput" id="accountAttribute" name="accountAttribute"	maxlength="240" />--%>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>账号类型 </span>
                    </div>
                    <div class="formCtrlDiv">

                        <select id="accountType" name="accountType">
                            <option value="3">公司账户</option>
                            <option value="4">银行卡</option>
                        </select>
                        <%--<input type="text" class="formInput" id="accountType" name="accountType"	maxlength="240" />--%>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>负责人电话 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="telephoneNum" name="telephoneNum"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>衫德秘钥路径</span>
                    </div>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" id="secretKey" name="secretKey"	maxlength="240" />--%>
                    <%--</div>--%>

                    <div class="layui-row" >
                        <form  enctype='multipart/form-data' method='post' action='javascript:;'  id="frmUploadFile">
                            <input type = "file" id = "pictureFile" class = "form-control" onchange = "addFile()" />
                        </form>
                        <input type="hidden" class="formInput" id="sandKeyPath" name="sandKeyPath"	maxlength="240" />
                        <%--<form action="${ctxData}ossUp/uploadLocalFile.do" enctype="multipart/form-data" method="post" style="display:inline-block">--%>
                    <%--<span  style="display:inline-block;background-color: #1E9FFF;height: 38px;line-height: 38px; padding: 0 18px;color: #fff;white-space: nowrap;--%>
                        <%--text-align: center;--%>
                        <%--font-size: 14px;--%>
                        <%--border: none;--%>
                        <%--border-radius: 2px;--%>
                        <%--cursor: pointer;width: 100px;overflow: hidden;position: relative;top: 0px;cursor: pointer">--%>
                        <%--<span style="cursor: pointer;width: 50px">选择上传密钥文件</span>--%>
                        <%--<input type="file" name="file" style="position: absolute;left: 0;top: 0;opacity: 0"></span>--%>
                            <%--<input type="submit" value="保存" class="buttonClass imginput addbtn" style="background-color: #1E9FFF">--%>
                        <%--</form>--%>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>是否测试通过</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isOk" name="isOk">
                            <option value="2">通过</option>
                            <option value="1">未通过</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>使用状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="useStatus" name="useStatus">
                            <option value="1">正常使用</option>
                            <option value="2">暂停使用</option>
                        </select>
                    </div>
                </li>


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
                    maxlength:20
                },
                secretKey:{
                    required:true,
                    maxlength:200
                },
                useStatus:{
                    required:true,
                    maxlength:1
                }
            },
            messages: {
                alias:{
                    required : "商户名称为空!",
                    maxlength : "商户名称最多是200个字符!"
                },
                secretKey:{
                    required : "商户秘钥不能为空!",
                    maxlength : "商户秘钥最多是20个字符!"
                },
                useStatus:{
                    required:"使用状态不能为空!",
                    number:"使用状态最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/replacepay/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/replacepay/list.do";
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


    function   addFile(){
        var operatorId = '123';
        var formData = new FormData();
        formData.append("file",$("#pictureFile").get(0).files[0]);
        formData.append("operatorId", operatorId);//需要上传的多个参数
        $.ajax({//jQuery方法，此处可以换成其它请求方式
            url: ctx+'/ossUp/uploadLocalFile.do',
            dataType: "json",
            type: "post",
            data: formData,
            processData: false,//不去处理发送的数据
            contentType: false,//不去设置Content-Type请求头
            error: function (res) {
                alert(res.desc);
                return;
            },
            success: function (res) {
                $("#sandKeyPath").val(res.data);
                alert(res.msg);
                return;
            }

        })
    }
</script>
</body>
</html>