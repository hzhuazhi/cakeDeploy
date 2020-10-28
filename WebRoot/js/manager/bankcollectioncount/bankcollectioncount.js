
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bankcollection/list.do',
        dataList_url : ctx + "/bankcollection/dataList.do",
    },
    //列表显示参数
    list:[
        {"data":"bankId",},
        {"data":"bankName",},
        {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"money",},
        {"data":"accountIdName",},
        {"data":"cardSiteIdName",},
        {"data":"curday",},
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/bankcollection/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            // account.condJsonData['money'] = $("#money").val();
            account.condJsonData['cardSiteId'] = $("#cardSiteId").val();
            account.condJsonData['accountId'] = $("#accountId").val();
            account.condJsonData['beginTime'] = $("#beginTime").val();
            account.condJsonData['endTime'] = $("#endTime").val();

            var url = ctx + "/bankcollection/dataBankAllList.do";
            common.ajax(url,account.condJsonData,function(data){
                if(data==null||data=="null"){
                    $("#countMoney").html("0");
                }else{
                    $("#countMoney").html(data.money);
                }

            });
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['bankName'] = "";
            account.condJsonData['bankCard'] = "";
            account.condJsonData['accountName'] = "";
            // account.condJsonData['money'] = "";
            account.condJsonData['cardSiteId'] = "";
            account.condJsonData['accountId'] = "";
            // account.condJsonData['curday'] = "";
            account.condJsonData['beginTime'] = "";
            account.condJsonData['endTime'] = "";
            $("#bankName").val("");
            $("#bankCard").val("");
            $("#accountName").val("");
            // $("#money").val("");
            $("#cardSiteId").val("0");
            $("#accountId").val("0");
            // $("#curday").val("");
            $("#beginTime").val("");
            $("#endTime").val("");
            common.showDatas(account.condJsonData,account.list);
            var url = ctx + "/bankcollection/dataBankAllList.do";
            common.ajax(url,account.condJsonData,function(data){
                if(data==null||data=="null"){
                    $("#countMoney").html("0");
                }else{
                    $("#countMoney").html(data.money);
                }

            });
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
    },

}


// 卡站点-无分页-下拉框选项:
function queryCardSiteAll(){
    var url = ctx + "/bankcollection/queryCardSite.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml += "<select id='cardSiteId' name='cardSiteId'  class='text-input medium-input'>";
        shtml +="<option value=''>===请选择===</option>";
        for (var i=0;i<dataList.length>0;i++) {
            shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
        }
        shtml +="</select>";
        $("#divCardSite").html(shtml);
    });
}


//卡商-无分页-下拉框选项:
function queryAccountAll(){
    var url = ctx + "/bankcollection/queryAccount.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml += "<select id='accountId' name='accountId'  class='text-input medium-input'>";
        shtml +="<option value=''>===请选择===</option>";
        for (var i=0;i<dataList.length>0;i++) {
            shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
        }
        shtml +="</select>";
        $("#divAccount").html(shtml);
    });
}


//查询汇总额度:
function queryCountMoney(){
    var url = ctx + "/bankcollection/dataBankAllList.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        $("#countMoney").html(data.money);
    });
}

queryCountMoney();
queryCardSiteAll();
queryAccountAll();
$(function(){
    account.indexInit();
})
