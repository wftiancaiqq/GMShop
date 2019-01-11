<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <script type="text/javascript" src="/static/js/jquery-1.8.2.min.js"></script>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
        form{
            width: 80%;
            margin: auto;
            padding: 40px;
        }
    </style>

</head>
<body>
<h3 style="color: red">${error}</h3>
<form action="/admin/updateSuperUser" method="post" enctype="multipart/form-data">
    <input type="hidden" id="result" value="${result}">
    <label>编号:</label><input type="hidden" id="id"  name="id" value="${sessionScope.superUser.id}" readonly/><br>
    <label>用户ID:</label><input type="text"  name="userId" value="${sessionScope.superUser.userId}" readonly/><br>
    <label>用户名：</label><input type="text" name="userLoginName" value="${sessionScope.superUser.userLoginName}" readonly /><br>
    <label>昵称:</label><input type="text" id="userName" name="userName" value="${sessionScope.superUser.userName}" required /><br>
    <label>登录密码:</label><input type="password" id="userPassword" name="userPassword" value="${sessionScope.superUser.userPassword}" required /><br>
    <label>上传头像:</label><input type="file" name="userImageX" value="" required /><br>
    <input type="submit" id="sub"  value="保存设置" class="layui-btn"/>
</form>
</body>
<script type="text/javascript">
    $(function () {
        var result=$("#result").val();
        if (result!=null&&result!=''){
            if (result=="success"){
                alert("修改成功！");
                top.location.href="/admin/login?userLoginName=${sessionScope.superUser.userLoginName}&userPassword=${sessionScope.superUser.userPassword}";
            }else {
                alert("修改失败！")
            }
        }
    })
</script>
</html>


