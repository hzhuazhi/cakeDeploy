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
                        <span class="require" ><font color="red">*</font>卡商名称 </span>
                    </div>
                    <div class="formCtrlDiv" id="acDiv">
                        <%--<input type="text" class="formInput" id="acName" name="acName"	maxlength="240" />--%>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>支付类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="payType" name="payType">
                            <option value="0">===请选择===</option>
                            <option value="1">卡商支付</option>
                            <option value="2">我方支付</option>
                        </select>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>收款银行卡</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankCard" name="bankCard"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>收款银行名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankName" name="bankName"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>开户名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="accountName" name="accountName"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="orderMoney" name="orderMoney"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="orderStatus" name="orderStatus">
                            <option value="0">===请选择===</option>
                            <option value="1">初始化</option>
                            <option value="2">超时/失败</option>
                            <option value="3">成功</option>
                        </select>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >充值记录转账图片凭证</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="file" class="formInput" id="pictureAds" name="pictureAds"	maxlength="240" />
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
    queryBankAll();
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                accountId:{
                    required:true,
                    maxlength:20
                },
                payType:{
                    required:true,
                    maxlength:2
                },
                bankCard:{
                    required:true,
                    maxlength:30
                },
                bankName:{
                    required:true,
                    maxlength:30
                },
                accountName:{
                    required:true,
                    maxlength:20
                },
                orderMoney:{
                    required:true,
                    maxlength:14
                }
                ,
                orderStatus:{
                    required:true,
                    maxlength:1
                }
            },
            messages: {
                accountId:{
                    required : "卡商名称为空!",
                    maxlength : "卡商名称最多是20个字符!"
                },
                payType:{
                    required : "支付类型不能为空!",
                    maxlength : "支付类型最多是20个字符!"
                },
                bankCard:{
                    required:"收款银行名称不能为空!",
                    number:"收款银行卡是30个字符!"
                },
                bankName:{
                    required:"收款银行名称名称不能为空!",
                    number:"收款银行名称是30个字符!"
                },
                accountName:{
                    required : "开户名不能为空!",
                    maxlength : "开户名最多是20个字符!"
                },
                orderMoney:{
                    required:"订单金额不能为空!",
                    number:"订单金额最多是20个字符!"
                },
                orderStatus:{
                    required : "订单状态不能为空!",
                    maxlength : "订单状态最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/merchantrecharge/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/merchantrecharge/list.do";
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



    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    function queryBankAll (){
        var url = ctx + "/system/account/dataList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            debugger;
            var dataList=data;
            var shtml="";
            shtml += "<select id='accountId' name='accountId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.rows.length;i++) {
                shtml +="<option value="+dataList.rows[i].id+">"+dataList.rows[i].acName+"</option>";
            }
            shtml +="</select>";
            $("#acDiv").html(shtml);
        });
    }


</script>
</body>
</html>