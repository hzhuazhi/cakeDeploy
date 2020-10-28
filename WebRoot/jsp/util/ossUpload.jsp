<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>阿里云上传</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<form id="form" method="POST" enctype="multipart/form-data"
      action='${ctxData}upload/ossUpload.do'>
    <input type="file" accept="files/*" name="files" id="files"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
