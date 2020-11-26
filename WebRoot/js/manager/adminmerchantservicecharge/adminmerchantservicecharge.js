
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminmerchantservicecharge/list.do',
        dataList_url : ctx + "/adminmerchantservicecharge/dataList.do",
        add_url : ctx+ "/adminmerchantservicecharge/add.do",
        update_url : ctx+ "/adminmerchantservicecharge/update.do",
        queryId_url: ctx+ "/adminmerchantservicecharge/getId.do",
        delete_url: ctx+ "/adminmerchantservicecharge/delete.do",
        manyOperation_url: ctx+ "/adminmerchantservicecharge/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"alias",},
        {"data":"merchantName",},
        {"data":"channelName",},
        {"data":"serviceCharge",},
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.useStatus==1){
                    html='<span>启用</span>';
                }else if(oData.useStatus==2){
                    html='<span><font color="red">禁用</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/adminmerchantservicecharge/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.useStatus == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 禁用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 启用 </a>';
                }
                html +=' <a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);"> 删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        alias:null,
        merchantId:0,
        channelId:0,
        useStatus:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryMerchantAll();
        this.queryChannelAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminmerchantservicecharge/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['merchantId'] = $("#merchantId").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['useStatus'] = $("#useStatus").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            $("#alias").val("");
            account.condJsonData['merchantId'] = "0";
            $("#merchantId").val("0");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['useStatus'] = "0";
            $("#useStatus").val("0");
            common.showDatas(account.condJsonData,account.list);
        });


        //启用/暂停
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var useStatus = $(this).attr('directValue');
            var data = {
                id:id,
                useStatus:useStatus
            }
            common.manyOperationUse(data);
        });

        //删除
        $(".dataTableDeleteBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

    },

    //下拉框数据填充
    //查询所有卡商-无分页-下拉框选项:
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

    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryChannelAll:function(){
        var url = basePath + "channel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].alias+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    }

}

$(function(){
    account.indexInit();
})
