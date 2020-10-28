<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>异常监听信息</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<style>
    .h_wrap{
        display: flex;flex-wrap: wrap;justify-content: space-between;padding: 20px;text-align: center;
    }
    .h_item{
        width:48%;height:7rem;line-height:7rem;background-color: #1aff23;margin-bottom: 10px;
    }
    .h_item1{
        position: relative;
        background-color: #ff301d;
        border: 1px solid #ff301d;
    }
    .h_item1_left{
        position: absolute;
        top:0;
        left: 0;
        background-color: #39af34;
        color: #2337ff;
        text-align: center;
        height: 2rem;
        line-height: 2rem;
        padding: 0 4px;
    }
</style>
<body>
<div class="col_main">
    <div>
        <div class="h_wrap">
            <div class="h_item " id="div_phone" style="font-size: 20px;color: #110302" onclick="queryPhone()"><strong id="sphone">手机号异常数:0</strong></div>
            <div class="h_item " id="div_bank" style="font-size: 20px;color: #110302" onclick="queryBank()"><strong id="sbank">失效的银行卡:0</strong></div>
            <c:if test="${ACCOUNT.roleId==1}">
                <div class="h_item " id="div_sms" style="font-size: 20px;color: #110302;" onclick="querySmsMessageNum()"><strong id="ssms">短信未匹配数:0</strong></div>
            </c:if>
            <div class="h_item " id="div_paymentnum" style="font-size: 20px;color: #110302;" onclick="queryPaymentnum()"><strong id="spaymentnum">未处理下发条数:0</strong></div>
        </div>
    </div>

    <div style="width: 100%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style="font-size: 20px;">列表显示(最多显示10条，多了需要去模块自己查看)</strong> </div>
    <div id="tables" ></div>
    <%--<table class="datatable tables">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th width="150" rowspan="2">手机卡别名</th>--%>
            <%--<th width="150" rowspan="2">银行卡id</th>--%>
            <%--<th width="150" rowspan="2">银行卡账号</th>--%>
            <%--<th width="150" rowspan="2">银行名称</th>--%>
            <%--<th width="150" rowspan="2">开户人</th>--%>
            <%--&lt;%&ndash;<th width="150">开户名</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th width="150">凭证短信</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th width="150">凭证尾号</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th width="150">银行码</th>&ndash;%&gt;--%>
            <%--<th width="150" colspan="3">支付宝</th>--%>
            <%--<th width="150" colspan="3">卡转卡</th>--%>
            <%--<th width="150" rowspan="2">优先级</th>--%>
            <%--<th width="150" rowspan="2">放量时间段</th>--%>
            <%--<th width="150" rowspan="2">使用状态</th>--%>
            <%--<th width="150" rowspan="2">创建时间</th>--%>
            <%--<th width="380" rowspan="2">操作</th>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th width="150">日上限金额</th>--%>
            <%--<th width="150">月上限金额</th>--%>
            <%--<th width="150">日上限次数</th>--%>
            <%--<th width="150">日上限金额</th>--%>
            <%--<th width="150">月上限金额</th>--%>
            <%--<th width="150">日上限次数</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--</tbody>--%>
    <%--</table>--%>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/abnormal/abnormal.js'></script>
</body>
</html>
