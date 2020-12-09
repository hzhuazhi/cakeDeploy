
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/merchantprofit/list.do',
        dataList_url : ctx + "/merchantprofit/dataList.do",
        // add_url : ctx+ "/merchantprofit/add.do",
        // update_url : ctx+ "/merchantprofit/update.do",
        // queryId_url: ctx+ "/merchantprofit/getId.do",
        // delete_url: ctx+ "/merchantprofit/delete.do",
        manyOperation_url: ctx+ "/merchantprofit/manyOperation.do",
        exportData_url : ctx +  "/merchantprofit/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"merchantName",},
        {"data":"orderNo",},
        {"data":"orderType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.orderType==1){
                    html+= '<span >代收订单</span>';
                }else if(oData.orderType==2){
                    html+= '<span >代付订单</span>';
                }else if(oData.orderType==3){
                    html='<span><font color="red">下发订单</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"orderMoney",},
        {"data":"distributionMoney",},
        // {"data":"serviceCharge",},
        {"data":"profitRatio",},
        {"data":"profit",},
        {"data":"replenishType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.replenishType==1){
                    html+= '<span >不是补单</span>';
                }else if(oData.replenishType==2){
                    html='<span><font color="red">是补单</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        merchantId:0,
        orderNo:null,
        orderType:0,
        replenishType:0,
        curdayStart:0,
        curdayEnd:0,
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryMerchantAll();
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['merchantId'] = $("#merchantId").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['replenishType'] = $("#replenishType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['merchantId'] = "0";
            $("#merchantId").val("0");
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['orderType'] = "0";
            $("#orderType").val("0");
            account.condJsonData['replenishType'] = "0";
            $("#replenishType").val("0");
            account.condJsonData['curdayStart'] = "";
            $("#curdayStart").val("");
            account.condJsonData['curdayEnd'] = "";
            $("#curdayEnd").val("");
            common.showDatas(account.condJsonData,account.list);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.cf(data);
        });

        // 数据按照Excel格式导出
        $("#butExcelExport").click(function () {
            common.dataExportExcel($("#condForm"));
        });
    },

    // //汇总数据填充
    // //查询所有订单汇总数据
    // queryTotal:function(){
    //     var url = basePath + "tpdata/totalData.do";
    //     var myTradeNo = $("#myTradeNo").val();
    //     var outTradeNo = $("#outTradeNo").val();
    //     var tradeStatus = $("#tradeStatus").val();
    //     var runStatus = $("#runStatus").val();
    //     var curdayStart = $("#curdayStart").val();
    //     var curdayEnd = $("#curdayEnd").val();
    //     var data = {
    //         "myTradeNo":myTradeNo,
    //         "outTradeNo":outTradeNo,
    //         "tradeStatus":tradeStatus,
    //         "runStatus":runStatus,
    //         "curdayStart":curdayStart,
    //         "curdayEnd":curdayEnd
    //     };
    //     common.ajax(url,data,function(data){
    //         var data=data;
    //         var shtml="";
    //         shtml += "汇总：         订单金额：";
    //         shtml += "<font color='red'>" + data.totalMoney + "</font>";
    //         shtml += "      手续费：";
    //         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
    //         shtml += "      实际金额：";
    //         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
    //         $("#totalDiv").html(shtml);
    //     });
    // }

    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "merchantprofit/totalData.do";
        var merchantId = $("#merchantId").val();
        var orderNo = $("#orderNo").val();
        var orderType = $("#orderType").val();
        var replenishType = $("#replenishType").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "merchantId":merchantId,
            "orderNo":orderNo,
            "orderType":orderType,
            "replenishType":replenishType,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            // shtml += "      手续费：";
            // shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
            // shtml += "      实际金额：";
            // shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      收益：";
            shtml += "<font color='red'>" + data.totalProfit + "</font>";
            $("#totalDiv").html(shtml);
        });
    },



    //下拉框数据填充
    //查询卡商-无分页-下拉框选项:
    queryMerchantAll:function(){
        var url = basePath + "merchant/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='merchantId' name='merchantId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
            }
            shtml +="</select>";
            $("#merchantDiv").html(shtml);
        });
    },

}

// function outQueryTotal(){
//     var url = basePath + "tpdata/totalData.do";
//     var myTradeNo = $("#myTradeNo").val();
//     var outTradeNo = $("#outTradeNo").val();
//     var tradeStatus = $("#tradeStatus").val();
//     var runStatus = $("#runStatus").val();
//     var curdayStart = $("#curdayStart").val();
//     var curdayEnd = $("#curdayEnd").val();
//     var data = {
//         "myTradeNo":myTradeNo,
//         "outTradeNo":outTradeNo,
//         "tradeStatus":tradeStatus,
//         "runStatus":runStatus,
//         "curdayStart":curdayStart,
//         "curdayEnd":curdayEnd
//     };
//     common.ajax(url,data,function(data){
//         var data=data;
//         var shtml="";
//         shtml += "汇总：         订单金额：";
//         shtml += "<font color='red'>" + data.totalMoney + "</font>";
//         shtml += "      手续费：";
//         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
//         shtml += "      实际金额：";
//         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
//         $("#totalDiv").html(shtml);
//     });
// }

$(function(){
    account.indexInit();
})
