
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/issue/list.do',
        dataList_url : ctx + "/issue/dataList.do",
        add_url : ctx+ "/issue/add.do",
        update_url : ctx+ "/issue/update.do",
        queryId_url: ctx+ "/issue/getId.do",
        delete_url: ctx+ "/issue/delete.do",
        manyOperation_url: ctx+ "/issue/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"outTradeNo",},
        {"data":"orderMoney",},
        // {"data":"bankName",},
        // {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>驳回</span>';
                }else if(oData.orderStatus==3){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"ascriptionType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.ascriptionType==1){
                    html='<span>卡商</span>';
                }else if(oData.ascriptionType==2){
                    html='<span>平台</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"isDistribution",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isDistribution==1){
                    html='<span>未分配</span>';
                }else if(oData.isDistribution==2){
                    html='<span>已分配</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"isComplete",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isComplete==1){
                    html='<span>未归集完毕</span>';
                }else if(oData.isComplete==2){
                    html='<span>已归集完毕</span>';
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
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.ascriptionType == 2){
                    if (oData.orderStatus != 3){
                        html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/issue/jumpUpdate.do?id='+oData.id+'"> 下发 </a>';
                    }

                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/issue/jumpInfo.do?id='+oData.id+'"> 详情 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        orderNo:null,
        outTradeNo:null,
        bankName:null,
        bankCard:null,
        accountName:null,
        orderStatus:0,
        ascriptionType:0,
        isDistribution:0,
        isComplete:0,
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
            window.location.href = ctx + "/issue/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['ascriptionType'] = $("#ascriptionType").val();
            account.condJsonData['isDistribution'] = $("#isDistribution").val();
            account.condJsonData['isComplete'] = $("#isComplete").val();
            account.condJsonData['checkStatus'] = $("#checkStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();



            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            account.condJsonData['bankName'] = "";
            $("#bankName").val("");
            account.condJsonData['bankCard'] = "";
            $("#bankCard").val("");
            account.condJsonData['accountName'] = "";
            $("#accountName").val("");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['ascriptionType'] = "0";
            $("#ascriptionType").val("0");
            account.condJsonData['isDistribution'] = "0";
            $("#isDistribution").val("0");
            account.condJsonData['isComplete'] = "0";
            $("#isComplete").val("0");
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
