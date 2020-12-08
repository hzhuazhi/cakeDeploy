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

    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">登录账号</th>
            <th width="150">账号名称</th>
            <th width="100">总额</th>
            <th width="120">保证金</th>
            <th width="100">余额</th>
            <th width="150">锁定金额</th>
            <th width="120">总收益</th>
            <th width="100">收益</th>
            <th width="120">费率</th>
            <th width="150">总进账</th>
            <th width="150">总转账</th>
            <th width="100">状态</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/transfer/transfer.js'></script>
</body>
</html>
