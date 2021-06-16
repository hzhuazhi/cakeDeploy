
var datatable;
var cardSiteId = 0;
var account = {
    //地址
    url:{
        list_url : ctx + '/replacePayStrategy/list.do',
        dataList_url : ctx + "/replacePayStrategy/dataList.do",
        add_url : ctx+ "/replacePayStrategy/add.do",
        update_url : ctx+ "/replacePayStrategy/update.do",
        queryId_url: ctx+ "/replacePayStrategy/getId.do",
        delete_url: ctx+ "/replacePayStrategy/delete.do",
        manyOperation_url: ctx+ "/replacePayStrategy/manyOperation.do",
        distribution_url: ctx+ "/replacePayStrategy/distribution.do",
    },
    //添加修改验证参数
    validate:{
        submitHandler : function() {
            var id = $("#show input[type='hidden']").val();
            var url = "";
            if(id){
                url = account.url.distribution_url;
            }else{
                url = account.url.add_url;
            }

            var formData = $("#newFirstStoreForm").serialize();
            $.ajax({
                url : url,
                type : 'post',
                dataType : 'json',
                data :formData,
                success : function(data) {
                    if(data.success){
                        cardSiteId = 0;
                        promptMessage ('保存成功！','success',true);
                        common.goList();
                    }else{
                        promptMessage(data.msg, 'warning', false);
                    }

                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
            return false;
            //阻止表单提交
        }
    },

    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"companyName",},
        {"data":"openTimeSlot",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='openTimeSlot'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.openTimeSlot+" >";
                $(nTd).html(html);
            }
        },
        {"data":"outDayMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='outDayMoney'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.outDayMoney+" >";
                $(nTd).html(html);
            }
        },
        {"data":"outMonthMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='outMonthMoney'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.outMonthMoney+" >";
                $(nTd).html(html);
            }
        },
        {"data":"outDayNum",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='outDayNum'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.outDayNum+" >";
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " onclick="queryValue('+iRow+','+oData.id+')"> 保存 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        merchantName:null,
        cardSiteName:null,
        orderNo:null,
        orderStatus:0,
        bankName:null,
        bankCard:null,
        accountName:null,
        operateStatus:0,
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
            // window.location.href = ctx + "/replacePayStrategy/loadDataAllList.do";

            $.ajax({
                url : ctx+ "/replacePayStrategy/loadDataAllList.do",
                type : 'post',
                dataType : 'json',
                // data :data,
                success : function(data) {
                    if (data.success) {
                        alert("导入成功");
                        window.location.href = ctx + "/replacePayStrategy/list.do";
                    } else {
                        alert(data.msg);
                    }
                },
                error : function(data) {
                    alert(data.info);
                }
            });
        });


        //添加
        // $(".addbtn").live("click",function(){
        //     // window.location.href = ctx + "/replacePayStrategy/loadDataAllList.do";
        //
        // });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            // account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['companyName'] = $("#companyName").val();


            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['companyName'] = "";
            $("#companyName").val("");
            common.showDatas(account.condJsonData,account.list);
        });

        //分配
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/recharge/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        cardSiteId = m.cardSiteId;
                        common.addInit(account.validate);
                        $("#divOrderNo").val(m.orderNo);
                        $("#id").val(id);
                        $("#divOrderMoney").val(m.orderMoney);
                        selectCardSite();
                        openDialog("show","");
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
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
        $(".dataTableOperateBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var operateStatus = $(this).attr('directkey1');
            var data = {
                id:id,
                operateStatus:operateStatus
            }
            // common.manyOperation(data);
            //更多操作：提示语
            var showMsg = '';
            if (data.operateStatus == 4){
                if(!confirm("确认要锁定吗？")){
                    return;
                }
                showMsg = '锁定成功!';
            }else if(data.operateStatus == 3){
                if(!confirm("确认要放弃吗？")){
                    return;
                }
                showMsg = '放弃成功!';
            }
            // this.ajax(account.url.manyOperation_url,data,function(data){
            //     if (data.success) {
            //         promptMessage (showMsg,'success',false);
            //         common.goList();
            //     } else {
            //         art.alert(data.msg);
            //     }
            // });

            $.ajax({url : account.url.manyOperation_url,
                type : 'post',
                dataType : 'json',
                data :{
                    id:id,
                    operateStatus:operateStatus
                },
                success : function(data) {
                    if (data.success) {
                        promptMessage (showMsg,'success',false);
                        common.goList();
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });



        });
    }

}



function  queryValue(i,id){
    let openTimeSlot= $("#"+'openTimeSlot'+i).val();
    let outDayMoney= $("#"+'outDayMoney'+i).val();
    let outMonthMoney= $("#"+'outMonthMoney'+i).val();
    let outDayNum= $("#"+'outDayNum'+i).val();
    let  data={
        "openTimeSlot":openTimeSlot,
        "outDayMoney":outDayMoney,
        "outMonthMoney":outMonthMoney,
        "outDayNum":outDayNum,
        "id":id
    }

    $.ajax({
        url : ctx+ "/replacePayStrategy/update.do",
        type : 'post',
        dataType : 'json',
        data :data,
        success : function(data) {
            if (data.success) {
                alert("修改成功！");
                window.location.href = ctx + "/replacePayStrategy/list.do";
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert(data.info);
        }
    });


}

$(function(){
    account.indexInit();
})


function selectCardSite(){

    $.ajax({url : ctx+ "/system/account/dataAllList.do",
        type : 'post',
        dataType : 'json',
        data :{
        },
        success : function(data) {
            if (data.success) {
                appendOption(data.data,"#cardSiteId");
                // appendOption(data,"#cardSiteId");
                $("#cardSiteId").val(cardSiteId);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}

function appendOption(list,selectId){
    var html='<option value="0">==请选择==</option>';
    for(var i=0;i<list.length;i++){
        html+='<option value="'+list[i].id+'">'+list[i].acName+'</option>';
    }
    $(selectId).html(html);


}
