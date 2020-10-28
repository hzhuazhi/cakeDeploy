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
            <div class = "condQueryCtrl" style="width: 100%">
                <strong style="color: #00a0e9">查询条件</strong>
            </div>
            <div class = "condQueryCtrl" style="width: 100%">
                <div class = "condQueryLabelDiv">手机卡别名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
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
                <div class = "condQueryLabelDiv">优先级：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="priority" name ="priority">
                </div>


                <%--</c:if>--%>
            </div>
            <div class = "condQueryCtrl" style="width: 100%">
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
                    <input type="button" class = "buttonClass imginput addbtn" value="导入可用银行卡" style="margin-left: 30px;" >
                </div>
            </div>


        </form>

        <form id = "savaForm">
            <br>
            <div class = "condQueryCtrl" style="width: 100%">
                <strong style="color: #00a0e9">批量修改字段</strong>
            </div>
            <div class = "condQueryCtrl" style="width: 100%">
                <div class = "condQueryLabelDiv">支转日上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="zfbInDayMoney" name ="zfbInDayMoney" size="12">
                </div>
                <div class = "condQueryLabelDiv">支转月上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="zfbInMonthMoney" name ="zfbInMonthMoney" size="12">
                </div>
                <div class = "condQueryLabelDiv">支转日笔数上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="zfbInDayNum" name ="zfbInDayNum" size="12">
                </div>
                <div class = "condQueryLabelDiv">卡转日上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="cardInDayMoney" name ="cardInDayMoney" size="12">
                </div>
                <div class = "condQueryLabelDiv">卡转月上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="cardInMonthMoney" name ="cardInMonthMoney" size="12">
                </div>
                <div class = "condQueryLabelDiv">卡转日笔数上限：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="cardInDayNum" name ="cardInDayNum" size="12">
                </div>
            </div>
            <div class = "condQueryCtrl" style="width: 100%">
                <div class = "condQueryLabelDiv">放量时间段：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="openTimeSlot" name ="openTimeSlot" size="36">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butUqdate" class = "buttonClass butUqdate" value = "批量修改" />
                </div>
            </div>
        </form>

    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150" rowspan="2"><input type="checkbox" id="all" />&nbsp;全选</th>
            <th width="150" rowspan="2">手机卡别名</th>
            <th width="150" rowspan="2">银行卡id</th>
            <th width="150" rowspan="2">银行卡账号</th>
            <th width="150" rowspan="2">银行名称</th>
            <th width="150" rowspan="2">开户人</th>
            <%--<th width="150">开户名</th>--%>
            <%--<th width="150">凭证短信</th>--%>
            <%--<th width="150">凭证尾号</th>--%>
            <%--<th width="150">银行码</th>--%>
            <th width="150" colspan="3">支付宝</th>
            <th width="150" colspan="3">卡转卡</th>
            <%--<th width="150" rowspan="2">优先级</th>--%>
            <th width="150" rowspan="2">放量时间段</th>
            <th width="150" rowspan="2">使用状态</th>
            <th width="150" rowspan="2">创建时间</th>
            <th width="380" rowspan="2">操作</th>
        </tr>
        <tr>
            <th width="150">日上限金额</th>
            <th width="150">月上限金额</th>
            <th width="150">日上限次数</th>
            <th width="150">日上限金额</th>
            <th width="150">月上限金额</th>
            <th width="150">日上限次数</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/bankstrategy/bankstrategy.js'></script>
</body>
</html>
