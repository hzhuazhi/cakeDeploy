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
                <div class = "condQueryLabelDiv">商户名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>
                <div class = "condQueryLabelDiv">商户秘钥：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="secretKey" name ="secretKey">
                </div>

                <div class = "condQueryLabelDiv">绑定类型：</div>
                <div class="formCtrlDiv">
                    <select id="bankBindingType" name="bankBindingType">
                        <option value="0">===请选择===</option>
                        <option value="1">无需绑定银行卡</option>
                        <option value="2">需要绑定银行卡</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">商户类型：</div>
                <div class="formCtrlDiv">
                    <select id="channelType" name="channelType">
                        <option value="0">===请选择===</option>
                        <option value="1">代收</option>
                        <option value="2">大包</option>
                        <option value="3">代付</option>
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

                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="120">商户名称</th>
            <th width="150">商户秘钥</th>
            <th width="150">绑定类型</th>
            <th width="120">商户类型</th>
            <th width="150">代收金额范围</th>
            <th width="150">代付金额范围</th>
            <th width="150">订单支付时间</th>
            <th width="150">银行卡锁定时间</th>
            <th width="150">代付方式</th>
            <th width="150">备注</th>
            <th width="150">使用状态</th>
            <th width="150">创建时间</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/channel/channel.js'></script>
</body>
</html>
