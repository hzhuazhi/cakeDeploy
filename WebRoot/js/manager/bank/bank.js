
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bank/list.do',
        dataList_url : ctx + "/bank/dataList.do",
        add_url : ctx+ "/bank/add.do",
        update_url : ctx+ "/bank/update.do",
        queryId_url: ctx+ "/bank/getId.do",
        delete_url: ctx+ "/bank/delete.do",
        manyOperation_url: ctx+ "/bank/manyOperation.do",
        // exportData_url : ctx +  "/bank/exportData.do"
        exportData_url : "http://45.207.62.104:8002/template/bank_template.xls"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"alias",},
        {"data":"phoneNum",},
        {"data":"bankCard",},
        {"data":"bankName",},
        {"data":"accountName",},
        {"data":"smsNum",},
        {"data":"lastNum",},
        {"data":"bankCode",},
        {"data":"inDayMoney",},
        {"data":"inMonthMoney",},
        {"data":"isArrears",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isArrears==1){
                    html+= '<span style="color: #2f9833">未欠费</span>';
                }else if(oData.isArrears==2){
                    html+= '<span style="color: #ff3710">欠费</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.checkStatus==1){
                    html+= '<span style="color: #2f9833">初始化</span>';
                }else if(oData.checkStatus==2){
                    html+= '<span style="color: #ff3710">不正常</span>';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"dataExplain",},
        {"data":"createTime",},
        {"data":"isOk",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isOk==1){
                    html+= '<span style="color: #ff3710">未通过</span>';
                }else if(oData.isOk==2){
                    html+= '<span style="color: #2f9833">通过</span>';
                }
                $(nTd).html(html);
            }
        },
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
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/bank/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
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
            window.location.href = ctx + "/bank/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['phoneNum'] = $("#phoneNum").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['isArrears'] = $("#isArrears").val();
            account.condJsonData['isOk'] = $("#isOk").val();
            account.condJsonData['useStatus'] = $("#useStatus").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            account.condJsonData['phoneNum'] = "";
            account.condJsonData['bankCard'] = "";
            account.condJsonData['bankName'] = "";
            account.condJsonData['accountName'] = "";
            account.condJsonData['isArrears'] = "";
            account.condJsonData['isOk'] = "";
            account.condJsonData['useStatus'] = "";
            $("#alias").val("");
            $("#phoneNum").val("");
            $("#bankCard").val("");
            $("#bankName").val("");
            $("#accountName").val("");
            $("#isArrears").val("0");
            $("#isOk").val("0");
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
        var url = basePath + "bank/dataAllList.do";
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
// 数据按照Excel格式导出
$("#butExcelExport").click(function () {
    common.dataExportExcel($("#condForm"));
});

$(function(){
    account.indexInit();
})
