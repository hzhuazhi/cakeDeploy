
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/adminrecharge/list.do',
        dataList_url : ctx + "/adminrecharge/dataList.do",
        add_url : ctx+ "/adminrecharge/add.do",
        update_url : ctx+ "/adminrecharge/update.do",
        queryId_url: ctx+ "/adminrecharge/getId.do",
        delete_url: ctx+ "/adminrecharge/delete.do",
        manyOperation_url: ctx+ "/adminrecharge/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"merchantName",},
        {"data":"cardSiteName",},
        {"data":"orderNo",},
        {"data":"orderType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderType==1){
                    html='<span>预付款</span>';
                }else if(oData.orderType==2){
                    html='<span>平台</span>';
                }else if(oData.orderType==3){
                    html='<span>下发</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"issueOrderNo",},
        {"data":"orderMoney",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>驳回</span>';
                }else if(oData.orderStatus==3){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"bankName",},
        // {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"operateStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.operateStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.operateStatus==2){
                    html='<span>系统放弃</span>';
                }else if(oData.operateStatus==3){
                    html='<span>手动放弃</span>';
                }else if(oData.operateStatus==4){
                    html='<span>锁定</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.checkStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.checkStatus==2){
                    html='<span>失败</span>';
                }else if(oData.checkStatus==3){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.orderType != 3){
                    if (oData.orderStatus == 3){
                        if (oData.checkStatus != 3){
                            html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminrecharge/jumpCheck.do?id='+oData.id+'"> 审核 </a>';
                        }
                    }
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminrecharge/jumpInfo.do?id='+oData.id+'"> 详情 </a>';
                if (oData.orderType != 3){
                    if (oData.orderStatus != 3){
                        html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminrecharge/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                    }
                }
                if (oData.orderType != 3){
                    if (oData.orderStatus != 3){
                        if (oData.operateStatus != 4){
                            html += '<a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                        }
                    }
                }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        merchantName:null,
        cardSiteName:null,
        orderNo:null,
        orderType:0,
        issueOrderNo:null,
        orderStatus:0,
        bankName:null,
        bankCard:null,
        accountName:null,
        operateStatus:0,
        checkStatus:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminrecharge/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['cardSiteName'] = $("#cardSiteName").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['issueOrderNo'] = $("#issueOrderNo").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['operateStatus'] = $("#operateStatus").val();
            account.condJsonData['checkStatus'] = $("#checkStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();



            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){

            account.condJsonData['merchantName'] = "";
            $("#merchantName").val("");
            account.condJsonData['cardSiteName'] = "";
            $("#cardSiteName").val("");
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['orderType'] = "0";
            $("#orderType").val("0");
            account.condJsonData['issueOrderNo'] = "";
            $("#issueOrderNo").val("");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['bankName'] = "";
            $("#bankName").val("");
            account.condJsonData['bankCard'] = "";
            $("#bankCard").val("");
            account.condJsonData['accountName'] = "";
            $("#accountName").val("");
            account.condJsonData['operateStatus'] = "0";
            $("#operateStatus").val("0");
            account.condJsonData['checkStatus'] = "0";
            $("#checkStatus").val("0");
            account.condJsonData['curdayStart'] = "0";
            $("#curdayStart").val("0");
            account.condJsonData['curdayEnd'] = "0";
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableResetBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });
    }

}

$(function(){
    account.indexInit();
})
