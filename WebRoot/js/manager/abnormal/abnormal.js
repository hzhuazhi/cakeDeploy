var datatable;
function  queryList(){

    var date = new Date();
    // var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year+ month + strDate;

    let  data={
        "curday":currentdate
    }
    $.ajax({
        url: ctx+ '/abnormal/dataList.do',
        type: 'post',
        dataType: 'json',
        contentType:"application/json",
        data:data,
        // 成功执行
        success (data) {


            if(data.phoneNum==0){
                $('#div_phone').attr("class","h_item");
            }else if(data.phoneNum!=0){
                $('#div_phone').attr("class","h_item h_item1");
            }

            // if(data.merchantReplenishNum==0){
            //     $('#div_merchantReplenish').attr("class","h_item");
            // }else if(data.bankNum!=0){
            //     $('#div_merchantReplenish').attr("class","h_item h_item1");
            // }

            if(data.withdrawNum==0){
                $('#div_withdraw').attr("class","h_item");
            }else if(data.withdrawNum!=0){
                $('#div_withdraw').attr("class","h_item h_item1");
            }


            if(data.bankNum==0){
                $('#div_bank').attr("class","h_item");
            }else if(data.bankNum!=0){
                $('#div_bank').attr("class","h_item h_item1");
            }

            if(data.orderOutNum==0){
                $('#div_orderout').attr("class","h_item");
            }else if(data.orderOutNum!=0){
                $('#div_orderout').attr("class","h_item h_item1");
            }

            $("#sphone").text("手机号异常数:"+data.phoneNum);
            // $("#sbank").text("需要补单数据:"+data.merchantReplenishNum);
            $("#spaymentnum").text("未处理下发条数:"+data.withdrawNum);
            $("#banknum").text("需要换卡条数:"+data.bankNum);
            $("#orderout").text("需要代付条数:"+data.orderOutNum);

            if(data.phoneNum!=0||data.bankNum!=0||data.withdrawNum!=0){
                audioPlay();
            }

        }
    })
}


/***
 * 查询手机号码异常
 */
function  queryPhone(){

    var condJsonData={
        "heartbeatStatus":1
    }
    let  table='';
    $.ajax({
        url: ctx+ '/mobilecard/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            table+='<table class="datatable tables">';
            table+='<thead>';
                table+='<tr>';
                    table+='<td>手机卡名称</td>';
                    table+='<td>归属人的名称</td>';
                    table+='<td>手机号码</td>';
                    table+='<td>是否欠费</td>';
                    table+='<td>心跳状态</td>';
                table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                    table+='<td>'+data.rows[i].cardName+'</td>';
                    table+='<td>'+data.rows[i].useName+'</td>';
                    table+='<td>'+data.rows[i].phoneNum+'</td>';
                    if(data.rows[i].isArrears==1){
                        table+='<td>未欠费</td>';
                    }else{
                        table+='<td>欠费</td>';
                    }

                    if(data.rows[i].heartbeatStatus==1){
                        table+='<td style="color: #ff301d">异常</td>';
                    }else{
                        table+='<td>正常</td>';
                    }
                table+='</tr>';
            }
            table+='</table>';
            $("#tables").html(table);

        }
    })
}


/***
 * 查询未打款的
 */
function  queryWithdraw(){
    var condJsonData={
        "orderStatus":1
    }
    let  table='';
    $.ajax({
        url: ctx+ '/withdraw/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td><b>渠道名称</b></td>';
            table+='<td><b>下发表的订单号</b></td>';
            table+='<td><b>银行名称</b></td>';
            table+='<td><b>银行卡账号</b></td>';
            table+='<td><b>开户名</b></td>';
            table+='<td><b>订单金额</b></td>';
            table+='<td><b>订单状态</b></td>';
            table+='<td><b>创建时间</b></td>';
            table+='<td><b>操作</b></td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].channelName+'</td>';
                table+='<td>'+data.rows[i].orderNo+'</td>';
                table+='<td>'+data.rows[i].inBankName+'</td>';
                table+='<td>'+data.rows[i].inBankCard+'</td>';
                table+='<td>'+data.rows[i].inAccountName+'</td>';
                table+='<td>'+data.rows[i].orderMoney+'</td>';
                if(data.rows[i].orderStatus==1){
                    table+='<td>初始化</td>';
                }
                table+='<td>'+data.rows[i].createTime+'</td>';
                table+='<td> <a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdraw/jumpUpdate.do?id='+data.rows[i].id+'"> 审核 </a></td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}


function queryOrderOut(){
    window.location.href =ctx+ '/jsp/manager/orderout/orderoutIndex.jsp';
}

function queryBank(){
    var condJsonData={
    }
    let  table='';
    $.ajax({
        url: ctx+ '/bank/queryReplaceList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td><b>银行名称</b></td>';
            table+='<td><b>银行卡账号</b></td>';
            table+='<td><b>原卡号后4位</b></td>';
            table+='<td><b>开户名</b></td>';
            table+='<td><b>换卡原因</b></td>';
            table+='<td><b>绑定设备号</b></td>';
            table+='<td><b>操作</b></td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].bankName+'</td>';
                table+='<td>'+data.rows[i].bankCard+'</td>';
                table+='<td>'+data.rows[i].leadBankCard+'</td>';
                table+='<td>'+data.rows[i].accountName+'</td>';
                table+='<td><strong style="color: #bb0000">'+data.rows[i].checkChange+'</strong></td>';
                table+='<td><strong style="color: #bb0000">'+data.rows[i].phoneDeviceNum+'</strong></td>';
                table+='<td> <a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/bank/jumpBankUpdate.do?id='+data.rows[i].id+'"> 修改卡号 </a></td>';
                table+='</tr>';
            }
            table+='</table>';
            $("#tables").html(table);
        }
    })




}

/***
 * 查询银行卡异常
 */
function  queryMerchantReplenish(){
     var condJsonData={
        "checkStatus":1
    }
    let  table='';
    $.ajax({
        url: ctx+ '/merchantreplenish/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td><b>订单号</b></td>';
            table+='<td><b>订单金额</b></td>';
            table+='<td><b>派单金额</b></td>';
            table+='<td><b>卡商</b></td>';
            table+='<td><b>商户</b></td>';
            table+='<td><b>处理状态</b></td>';
            table+='<td><b>创建时间</b></td>';
            table+='<td><b>操作</b></td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].orderNo+'</td>';
                table+='<td>'+data.rows[i].orderMoney+'</td>';
                table+='<td>'+data.rows[i].distributionMoney+'</td>';
                table+='<td>'+data.rows[i].merchantName+'</td>';
                table+='<td >'+data.rows[i].channelName+'</td>';
                table+='<td style="color: #ff301d">未处理</td>';
                table+='<td>'+data.rows[i].createTime+'</td>';
                table+='<td><a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/merchantreplenish/jumpUpdateCheck.do?id='+data.rows[i].id+'"> 审核 </a></td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}


/***
 * 查询短信解析异常
 */
function  querySmsMessageNum(){
    var date = new Date();
    // var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year+""+ month+"" + strDate;

    var condJsonData={
        "workType":2,
        "handleType":1,
        "curday":currentdate
    }
    let  table='';
    $.ajax({
        url: ctx+ '/bankshortmsg/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td>id</td>';
            table+='<td>手机号</td>';
            table+='<td>银行卡类型</td>';
            table+='<td>银行卡号</td>';
            table+='<td>端口号</td>';
            table+='<td>短信内容</td>';
            table+='<td>状态</td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].id+'</td>';
                table+='<td>'+data.rows[i].phoneNum+'</td>';
                table+='<td>'+data.rows[i].bankTypeId+'</td>';
                table+='<td>'+data.rows[i].bankId+'</td>';
                table+='<td>'+data.rows[i].smsNum+'</td>';
                table+='<td>'+data.rows[i].smsContent+'</td>';
                table+='<td><a class = "dataTableBtn dataTableDeleteBtn " href="#" onclick="updatahandleType('+data.rows[i].id+')"> 已处理 </a></td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}

function  updatahandleType(id){
    if(confirm("确定修改状态吗？")){
        var condJsonData={
            "id":id,
            "handleType":2
        }
        let  table='';
        $.ajax({
            url: ctx+ '/bankshortmsg/updateHandleType.do',
            type: 'post',
            data:condJsonData,
            // 成功执行
            success (data) {
                alert(data.msg);
                queryList();
                querySmsMessageNum();
            }
        })
    }
}



function  audioPlay() {
    let audio =$("#audio");
    audio.get(0).play();
}

queryList();
$(function(){
    self.setInterval("queryList()", 1000*10);
})
