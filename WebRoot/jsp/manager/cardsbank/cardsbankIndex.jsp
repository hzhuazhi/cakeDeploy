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

                <c:if test="${ACCOUNT.roleId==1}">
                    <div class = "searchdiv">
                        <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                    </div>
                </c:if>
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
                        cursor: pointer;width: 70px;overflow: hidden;position: relative;top: 15px;cursor: pointer">
                        <span style="cursor: pointer">选择批量文件</span>
                        <input type="file" name="file" style="position: absolute;left: 0;top: 0;opacity: 0"></span>
            <input type="submit" value="保存" class="buttonClass imginput addbtn" style="background-color: #1E9FFF">
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">卡id</th>
            <th width="150">手机号</th>
            <th width="150">银行名称</th>
            <th width="150">银行卡账号</th>
            <th width="150">开户名</th>
            <th width="150">收款日限金额</th>
            <th width="150">收款月限金额</th>
            <th width="150">补充状态</th>
            <th width="150">测试状态</th>
            <th width="150">测试金额</th>
            <th width="150">创建时间</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/cardsbank/cardsbank.js'></script>
</body>
</html>
