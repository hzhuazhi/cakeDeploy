
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/merchantservicecharge/list.do',
        dataList_url : ctx + "/merchantservicecharge/dataList.do",
        add_url : ctx+ "/merchantservicecharge/add.do",
        update_url : ctx+ "/merchantservicecharge/update.do",
        queryId_url: ctx+ "/merchantservicecharge/getId.do",
        delete_url: ctx+ "/merchantservicecharge/delete.do",
        manyOperation_url: ctx+ "/merchantservicecharge/manyOperation.do"
    },
    //列表显示参数
    list:[
        // {"data":"alias",},
        {"data":"merchantName",},
        {"data":"channelName",},
        {"data":"serviceCharge",},
        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        channelName:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['channelName'] = $("#channelName").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            common.showDatas(account.condJsonData,account.list);
        });
    }


}

$(function(){
    account.indexInit();
})
