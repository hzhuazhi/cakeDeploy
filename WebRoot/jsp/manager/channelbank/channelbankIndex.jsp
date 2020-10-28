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
                <div class = "condQueryLabelDiv">名称/别名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>
                <div class = "condQueryLabelDiv">已绑定银行卡：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="secretKey" name ="secretKey">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>

                <%--<c:if test="${ACCOUNT.roleId==1}">--%>
                    <%--<div class = "searchdiv">--%>
                        <%--<input type="button" class = "buttonClass imginput addbtn" value="新增账号" style="margin-left: 30px;" >--%>
                    <%--</div>--%>
                <%--</c:if>--%>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">商户ID</th>
            <th width="150">名称/别名</th>
            <th width="150">已绑定银行卡</th>
            <%--<th width="150">创建时间</th>--%>
            <th width="150">使用状态</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/channelbank/channelbank.js'></script>
</body>
</html>
