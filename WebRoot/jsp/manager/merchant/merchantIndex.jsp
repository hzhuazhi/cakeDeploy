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

                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>
    </c:if>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">名称/别名</th>
            <th width="150">账号</th>
            <th width="150">所属角色ID</th>
            <th width="150">总额</th>
            <th width="150">保底金额</th>
            <th width="150">手续比例</th>
            <th width="150">余额</th>
            <th width="150">锁定金额</th>
            <th width="150">总收益金额</th>
            <th width="150">可提现金额</th>
            <th width="150">卡商类型</th>
            <th width="150">卡商运营类型</th>
            <th width="150">代付款类型</th>
            <th width="150">使用状态</th>
            <th width="150">备注</th>
            <th width="150">创建时间</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/merchant/merchant.js'></script>
</body>
</html>
