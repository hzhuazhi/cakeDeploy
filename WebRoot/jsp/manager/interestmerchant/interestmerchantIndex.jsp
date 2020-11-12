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
        <c:set var="dl" value="${dl}"/>
        <input type="hidden" id="interestId" name ="interestId" value="${dl.id}" >

        <div class = "searchdiv">
            <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
        </div>

    </div>
    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">卡商信息</th>
            <th width="150">手续费</th>
            <th width="150">绑定时间</th>
            <th width="150">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/interestmerchant/interestmerchant.js'></script>
</body>
</html>
