
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/merchantreplenish/list.do',
        dataList_url : ctx + "/merchantreplenish/dataList.do",
        add_url : ctx+ "/merchantreplenish/add.do",
        update_url : ctx+ "/merchantreplenish/update.do",
        queryId_url: ctx+ "/merchantreplenish/getId.do",
        delete_url: ctx+ "/merchantreplenish/delete.do",
        manyOperation_url: ctx+ "/merchantreplenish/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"outTradeNo",},
        {"data":"orderMoney",},
        {"data":"distributionMoney",},
        {"data":"merchantName",},
        {"data":"cardSiteName",},
        {"data":"channelName",},
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.checkStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.checkStatus==2){
                    // html='<span>失败</span>';
                    html='<span><font color="red">失败</font></span>';
                }else if(oData.checkStatus==3){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"handleType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.handleType==1){
                    // html='<span>未处理</span>';
                    html='<span><font color="red">未处理</font></span>';
                }else if(oData.handleType==2){
                    html='<span>已处理</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"handlePeopleName",},
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/merchantreplenish/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.handleType == 1){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/merchantreplenish/jumpUpdateCheck.do?id='+oData.id+'"> 审核 </a>';
                }else if(oData.handleType == 2){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/merchantreplenish/jumpInfo.do?id='+oData.id+'"> 详情 </a>';
                }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        orderNo:null,
        outTradeNo:null,
        merchantName:null,
        cardSiteName:null,
        channelName:null,
        handlePeopleName:null,
        checkStatus:0,
        handleType:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/merchantreplenish/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['cardSiteName'] = $("#cardSiteName").val();
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['handlePeopleName'] = $("#handlePeopleName").val();
            account.condJsonData['checkStatus'] = $("#checkStatus").val();
            account.condJsonData['handleType'] = $("#handleType").val();

            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            account.condJsonData['merchantName'] = "";
            $("#merchantName").val("");
            account.condJsonData['cardSiteName'] = "";
            $("#cardSiteName").val("");
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            account.condJsonData['handlePeopleName'] = "";
            $("#handlePeopleName").val("");
            account.condJsonData['checkStatus'] = "0";
            $("#checkStatus").val("0");
            account.condJsonData['handleType'] = "0";
            $("#handleType").val("0");
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
