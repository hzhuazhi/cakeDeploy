
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bankShortMsgStrategy/list.do',
        dataList_url : ctx + "/bankShortMsgStrategy/dataList.do",
        add_url : ctx+ "/bankShortMsgStrategy/add.do",
        update_url : ctx+ "/bankShortMsgStrategy/update.do",
        queryId_url: ctx+ "/bankShortMsgStrategy/getId.do",
        delete_url: ctx+ "/bankShortMsgStrategy/delete.do",
        manyOperation_url: ctx+ "/bankShortMsgStrategy/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"bankName",},
        {"data":"alias",},
        {"data":"smsNum",},
        {"data":"startMoney",},
        {"data":"endMoney",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/bankShortMsgStrategy/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        bankName:null,
        alias:null,
        smsNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/bankShortMsgStrategy/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['smsNum'] = $("#smsNum").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['bankName'] = "";
            $("#bankName").val("");
            account.condJsonData['alias'] = "";
            $("#alias").val("");
            account.condJsonData['smsNum'] = "";
            $("#smsNum").val("");
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
