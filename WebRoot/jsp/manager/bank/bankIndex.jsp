<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">手机卡别名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>
                <div class = "condQueryLabelDiv">手机号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="phoneNum" name ="phoneNum">
                </div>
                <div class = "condQueryLabelDiv">银行卡：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>
                <div class = "condQueryLabelDiv">银行名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankName" name ="bankName">
                </div>
                <div class = "condQueryLabelDiv">开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
            </div>
            <br>
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">卡状态</div>
                <div class="formCtrlDiv">
                    <select id="isArrears" name="isArrears">
                        <option value="0">===请选择===</option>
                        <option value="1">未欠费</option>
                        <option value="2">欠费</option>
                    </select>
                    <%--<input type ="text" class ="inputCommonSty" id="bank_card" name ="bank_card">--%>
                </div>
                <div class = "condQueryLabelDiv">测试状态：</div>
                <div class="formCtrlDiv">
                    <select id="isOk" name="isOk">
                        <option value="0">===请选择===</option>
                        <option value="1">未通过</option>
                        <option value="2">通过</option>
                    </select>
                </div>
                <div class = "condQueryLabelDiv">使用状态：</div>
                <div class="formCtrlDiv">
                    <select id="useStatus" name="useStatus">
                        <option value="0">===请选择===</option>
                        <option value="1">正常使用</option>
                        <option value="2">暂停使用</option>
                    </select>
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>

                <%--<c:if test="${ACCOUNT.roleId==1}">--%>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>

                <div class = "searchdiv">
                    <input type="button" id="butExcelExport" class = "buttonClass imginput" value="下载导入模板" style="margin-left: 30px;" >
                </div>
                <%--</c:if>--%>
            </div>
        </form>

    </div>
    <br>
    <div class="layui-row" >
        <form action="${ctxData}bank/importExcel.do" enctype="multipart/form-data" method="post" style="display:inline-block">
                    <span  style="display:inline-block;background-color: #1E9FFF;height: 38px;line-height: 38px; padding: 0 18px;color: #fff;white-space: nowrap;
                        text-align: center;
                        font-size: 14px;
                        border: none;
                        border-radius: 2px;
                        cursor: pointer;width: 100px;overflow: hidden;position: relative;top: 15px;cursor: pointer">
                        <span style="cursor: pointer;width: 50px">选择上传Excel</span>
                        <input type="file" name="file" style="position: absolute;left: 0;top: 0;opacity: 0"></span>
            <input type="submit" value="保存" class="buttonClass imginput addbtn" style="background-color: #1E9FFF">
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">卡id</th>
            <th width="150">手机卡别名</th>
            <th width="150">手机号</th>
            <th width="150">银行卡账号</th>
            <th width="150">银行名称</th>
            <th width="150">开户名</th>
            <th width="150">凭证短信</th>
            <th width="150">凭证尾号</th>
            <th width="150">银行码</th>
            <th width="150">收款日限金额</th>
            <th width="150">收款月限金额</th>
            <th width="150">卡状态</th>
            <th width="150">检测状态</th>
            <%--<th width="150">限制的原因</th>--%>
            <th width="150">创建时间</th>
            <th width="150">测试状态</th>
            <th width="150">使用状态</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/bank/bank.js'></script>
</body>
</html>
