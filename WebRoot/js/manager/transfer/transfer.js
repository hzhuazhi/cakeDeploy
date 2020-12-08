
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/transfer/list.do',
        dataList_url : ctx + "/transfer/dataList.do",
        add_url : ctx+ "/transfer/add.do",
        update_url : ctx+ "/transfer/update.do",
        queryId_url: ctx+ "/transfer/getId.do",
        delete_url: ctx+ "/transfer/delete.do",
        manyOperation_url: ctx+ "/transfer/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        {"data":"acName",},
        {"data":"totalMoney",},
        {"data":"leastMoney",},
        {"data":"balance",},
        {"data":"lockMoney",},
        {"data":"totalProfit",},
        {"data":"profit",},
        {"data":"serviceCharge",},
        {"data":"totalInMoney",},
        {"data":"totalOutMoney",},
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.useStatus==1){
                    html='<span>启用</span>';
                }else if(oData.useStatus==2){
                    html='<span>禁用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = '<a class = "dataTableBtn" href="'+ctx+'/transfer/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>';
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

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            common.showDatas(account.condJsonData,account.list);
        });

    }


}

$(function(){
    account.indexInit();
})
