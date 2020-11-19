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
    <c:if test="${ACCOUNT.roleId==1}">
    <div class = "condQueryDiv">
        <input type="hidden" value="${ACCOUNT.roleId}" name="roleId" id="roleId">
        <form id = "condForm">

            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">名称/别名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>

                <div class = "condQueryLabelDiv">账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountNum" name ="accountNum">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>
        </form>
    </div>
    </c:if>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">订单号</th>
            <th width="150">归属卡商</th>
            <th width="150">银行名称</th>
            <th width="150">银行卡账号</th>
            <th width="150">开户名</th>
            <th width="150">提现金额</th>
            <th width="150">订单状态</th>
            <th width="150">审核状态</th>
            <th width="150">创建时间</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/merchantwithdraw/merchantwithdraw.js'></script>
</body>
</html>
