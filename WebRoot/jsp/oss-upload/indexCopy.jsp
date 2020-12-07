<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>数据查询系统</title>
    <%--<%@ include file="/jsp/manager/common/head-meta.jsp"%>--%>
    <script type='text/javascript' src='<%=basePath %>js/common/jquery.js'></script>
    <script type='text/javascript' src='https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js'></script>
    <script type="text/javascript">
    </script>
</head>
<style>
    body,html{
        text-align: center;
    }
    #editDiv{
        width:1000px;
        display: inline-block;
        height: 800px;
        background:#fff;
        border-radius:10px;
        line-height: 20px;
        padding:10px;
        font-size: 14px;
        color:#666;
        resize: none;
        outline: none;
        overflow-y: scroll;
    }
    #editDiv{
        border:1px solid #333;
        border-color:rgba(169,169,169,1);
        text-align: left;
    }
</style>
<body>

<div id="login_pan">
    <div class="imgBox"></div>
    <form id="login_form" method="post" action="<%=basePath %>admin/login.do">
        <div class="formBox">
            <div id="editDiv" contenteditable="true">
            </div>

            <div id="test1">
            </div>
            <input type="button" id="login_btn" value="提 交">
        </div>
    </form>
</div>
</body>
<script type="text/javascript">
    document.querySelector('#editDiv').addEventListener('paste',function(e){
        var cbd = e.clipboardData;
        var ua = window.navigator.userAgent;
        // 如果是 Safari 直接 return
        if ( !(e.clipboardData && e.clipboardData.items) ) {
            return ;
        }
        // Mac平台下Chrome49版本以下 复制Finder中的文件的Bug Hack掉
        if(cbd.items && cbd.items.length === 2 && cbd.items[0].kind === "string" && cbd.items[1].kind === "file" &&
            cbd.types && cbd.types.length === 2 && cbd.types[0] === "text/plain" && cbd.types[1] === "Files" &&
            ua.match(/Macintosh/i) && Number(ua.match(/Chrome\/(\d{2})/i)[1]) < 49){
            return;
        }
        for(var i = 0; i < cbd.items.length; i++) {
            var item = cbd.items[i];
            if(item.kind == "file"){
                var blob = item.getAsFile();
                if (blob.size === 0) {
                    return;
                }
                // blob 就是从剪切板获得的文件 可以进行上传或其他操作
                /*-----------------------与后台进行交互 start-----------------------*/
                /*var data = new FormData();
                data.append('discoverPics', blob);
                $.ajax({
                    url: '/discover/addDiscoverPicjson.htm',
                    type: 'POST',
                    cache: false,
                    data: data,
                    processData: false,
                    contentType: false,
                    success:function(res){
                        var obj = JSON.parse(res);
                        var wrap = $('#editDiv');
                        var file = obj.data.toString();
                        var img = document.createElement("img");
                        img.src = file;
                        wrap.appendChild(img);
                    },error:function(){

                    }
                })*/
                var data = new FormData();
                data.append('files', blob);
                $.ajax({
                    url: '<%=basePath%>upload/ossUploadTest.do',
                    type: 'POST',
                    cache: false,
                    data: data,
                    processData: false,
                    contentType: false,
                    dataType : 'json',
                    success:function(data){
                        debugger
                        var obj = data.msg;
                        var file = obj;
                        var img1 = document.createElement("img");
                        img1.src = file;
                        var node = document.getElementById("test1");
                        var childNodes = node.childNodes;
                        // node.removeChild(node);
                        if(childNodes.length > 0){
                            for(i = 0; i <= childNodes.length; i ++){
                                //删除之前的元素
                                node.removeChild(childNodes[i]);
                            }
                        }
                        node.appendChild(img1);
                    },error:function(){

                    }
                })

                /*-----------------------与后台进行交互 end-----------------------*/
                /*-----------------------不与后台进行交互 直接预览start-----------------------*/
                var reader = new FileReader();
                var imgs = new Image();
                imgs.file = blob;
                reader.onload = (function(aImg) {
                    return function(e) {
                        aImg.src = e.target.result;
                    };
                })(imgs);
                reader.readAsDataURL(blob);
                document.querySelector('#editDiv').appendChild(imgs);
                /*-----------------------不与后台进行交互 直接预览end-----------------------*/
            }
        }
    }, false);
</script>
</html>