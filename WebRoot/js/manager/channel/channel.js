
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channel/list.do',
        dataList_url : ctx + "/channel/dataList.do",
        add_url : ctx+ "/channel/add.do",
        update_url : ctx+ "/channel/update.do",
        queryId_url: ctx+ "/channel/getId.do",
        delete_url: ctx+ "/channel/delete.do",
        manyOperation_url: ctx+ "/channel/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"alias",},
        {"data":"secretKey",},
        {"data":"bankBindingType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.bankBindingType==1){
                    html+= '<span >无需绑定银行卡</span>';
                }else if(oData.bankBindingType==2){
                    html+= '<span >需要绑定银行卡</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.useStatus==1){
                    html+= '<span style="color: #2f9833">正常使用</span>';
                }else if(oData.useStatus==2){
                    html+= '<span style="color: #ff3710">暂停使用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html  = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channel/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
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
            window.location.href = ctx + "/channel/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['secretKey'] = $("#secretKey").val();
            account.condJsonData['useStatus'] = $("#useStatus").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            account.condJsonData['secretKey'] = "";
            account.condJsonData['useStatus'] = "";
            $("#alias").val("");
            $("#secretKey").val("");
            $("#useStatus").val("0");
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
    },

    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryBankAll:function(){
        var url = basePath + "channel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">==="+dataList[i].agentName+"==="+dataList[i].accountNum+"</option>";
            }
            shtml +="</select>";
            $("#bankDiv").html(shtml);
        });
    }

}

$(function(){
    account.indexInit();
})
