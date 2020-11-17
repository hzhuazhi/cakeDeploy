
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/changemoney/list.do',
        dataList_url : ctx + "/changemoney/dataList.do",
        add_url : ctx+ "/changemoney/add.do",
        update_url : ctx+ "/changemoney/update.do",
        queryId_url: ctx+ "/changemoney/getId.do",
        delete_url: ctx+ "/changemoney/delete.do",
        manyOperation_url: ctx+ "/changemoney/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"alias",},
        {"data":"money",},
        {"data":"ascriptionName",},
        {"data":"ascriptionType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.ascriptionType==1){
                    html+= '<span >卡商</span>';
                }else if(oData.ascriptionType==2){
                    html+= '<span >中转站</span>';
                }else if(oData.ascriptionType==3){
                    html+= '<span >利益人</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"changeField",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.changeField==1){
                    html+= '<span style="color: #2f9833">余额变更</span>';
                }else if(oData.changeField==2){
                    html+= '<span style="color: #ff3710">收益变更</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"changeField",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.changeField==1){
                    html+= '<span style="color: #2f9833">加金额</span>';
                }else if(oData.changeField==2){
                    html+= '<span style="color: #ff3710">减金额</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"dataExplain",},
        {"data":"createTime",}

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/changemoney/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['money'] = $("#money").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['money'] = "";
            $("#money").val("");
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


}

$(function(){
    account.indexInit();
})
