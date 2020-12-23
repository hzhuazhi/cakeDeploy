
var datatable;
var merchantId = 0;
var account = {
    //地址
    url:{
        list_url : ctx + '/adminwithdraw/list.do',
        dataList_url : ctx + "/adminwithdraw/dataList.do",
        add_url : ctx+ "/adminwithdraw/add.do",
        update_url : ctx+ "/adminwithdraw/update.do",
        queryId_url: ctx+ "/adminwithdraw/getId.do",
        delete_url: ctx+ "/adminwithdraw/delete.do",
        manyOperation_url: ctx+ "/adminwithdraw/manyOperation.do",
        distribution_url: ctx+ "/adminwithdraw/distribution.do",
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
                        merchantId = 0;
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
        {"data":"orderNo",},
        {"data":"orderMoney",},
        {"data":"withdrawType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawType==1){
                    html='<span>利益者</span>';
                }else if(oData.withdrawType==2){
                    html='<span>卡商</span>';
                }else if(oData.withdrawType==3){
                    html='<span>渠道</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"outType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.outType==1){
                    html='<span>卡商</span>';
                }else if(oData.outType==2){
                    html='<span>平台</span>';
                }else if(oData.outType==3){
                    html='<span>中转站</span>';
                }else{
                    html='<span><font color="red">初始化</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"merchantName",},
        {"data":"inBankName",},
        {"data":"inBankCard",},
        {"data":"inAccountName",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>失败</span>';
                }else if(oData.orderStatus==3){
                    html='<span>失败</span>';
                }else if(oData.orderStatus==4){
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"outType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.outType != 0){
                    html='<span>已分配</span>';
                }else{
                    html='<span><font color="red">未分配</font></span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.orderStatus == 1){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 分配 </a>'
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminwithdraw/jumpInfo.do?id='+oData.id+'"> 详情 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        merchantName:null,
        orderNo:null,
        inBankName:null,
        inBankCard:null,
        inAccountName:null,
        orderStatus:0,

        outType:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);


        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['inBankName'] = $("#inBankName").val();
            account.condJsonData['inBankCard'] = $("#inBankCard").val();
            account.condJsonData['inAccountName'] = $("#inAccountName").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['outType'] = $("#outType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();



            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){

            account.condJsonData['merchantName'] = "";
            $("#merchantName").val("");
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['inBankName'] = "";
            $("#inBankName").val("");
            account.condJsonData['inBankCard'] = "";
            $("#inBankCard").val("");
            account.condJsonData['inAccountName'] = "";
            $("#inAccountName").val("");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['outType'] = "0";
            $("#outType").val("0");
            account.condJsonData['curdayStart'] = "0";
            $("#curdayStart").val("0");
            account.condJsonData['curdayEnd'] = "0";
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

        //分配
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/adminwithdraw/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        merchantId = m.merchantId;
                        common.addInit(account.validate);
                        $("#divOrderNo").val(m.orderNo);
                        $("#id").val(id);
                        $("#divOrderMoney").val(m.orderMoney);
                        $("#divInBankName").val(m.inBankName);
                        $("#divInBankCard").val(m.inBankCard);
                        $("#divInAccountName").val(m.inAccountName);
                        selectMerchant();
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

    }

}

$(function(){
    account.indexInit();
})


function selectMerchant(){
    $.ajax({url : ctx+ "/merchant/dataJsonAllList.do",
        type : 'post',
        dataType : 'json',
        data :{
        },
        success : function(data) {
            if (data.success) {
                appendOption(data.data,"#merchantId");
                // appendOption(data,"#cardSiteId");
                $("#merchantId").val(merchantId);
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
