<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>充值订单列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl" style="width: 100%;">
                <div class = "condQueryLabelDiv">公司名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="companyName" name ="companyName">
                </div>
                <div class = "condQueryLabelDiv">开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
                <%--</br>--%>
            </div>

        </form>
    </div>

    <table class="datatable tables">

        <thead>
        <tr>
            <th width="100">id</th>
            <th width="110">公司名称</th>
            <th width="120">公司地址</th>
            <th width="100">银行名称</th>
            <th width="100">银行卡账号</th>
            <th width="100">银行支行</th>
            <th width="100">开户名</th>
            <th width="100">余额</th>
            <th width="100">可用余额</th>
            <th width="100">回调地址</th>
            <th width="100">余额地址</th>
            <th width="100">商户ID</th>
            <th width="100">平台商户ID</th>
            <th width="100">账号属性</th>
            <th width="100">账号类型</th>
            <th width="100">负责人电话</th>
            <th width="100">衫德秘钥路径</th>
            <th width="130">创建时间</th>
            <th width="130">是否测试通过</th>
            <th width="250">操作</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>



<div id="show" style="display:none;width:400px;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red">下发订单分派卡站点</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderNo" name="divOrderNo" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderMoney" name="divOrderMoney" disabled="disabled"/>
                    </div>
                </dd>
                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        分派卡站点
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="cardSiteId" id="cardSiteId">
                            <option value="0">==请选择==</option>
                        </select>
                    </div>
                </dd>
                <dd style="border-top: none;">
                    <div class="formTextDiv"></div>
                    <%--<div class="formCtrlDiv">
                        -------------------------------------------------------------------------------
                    </div>--%>
                </dd>
                <dd style=" height: 60px; line-height: 58px;">
                    <div class="formCtrlDiv">
							<span style="margin-left: 100px;">
								<input type="submit" style="background-color: #767DC3" class="formBtn" value="保　存" />
								<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />
								<%--<input type="reset" onClick="javascript :closeDialog('show')" class="formBtn" value=" 返 回 " /> --%>
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>


<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/replacepay/replacepay.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
