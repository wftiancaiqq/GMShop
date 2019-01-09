<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <style>
        .personWarp{
            height: 400px;
            width: 75%;
            overflow: hidden;
            margin: auto;
            background-color: #ffffff;
            padding-left:25%;
            font-size: 16px;
            color: #696969;
        }

        .personWarp input{
            margin-top: 20px;
        }
        input[type='text']{
            border-radius: 5px;
            height: 40px;
            font-size: 15px;
            padding-left: 10px;
        }
        input[name='stuId']{
            background-color: #999999;
        }
        input[type='submit']{
            width: 225px;
        }
    </style>
</head>
<body>
<div class="personWarp">
    <form action="/doCustomer/updateCustomer" method="post">
        <input type="hidden" value="${result}" id="result"/>
        <input type="hidden" name="id" value="${requestScope.customer.id}" />
        <label>用户名:</label>
        <input type="text" name="cusLoginName" value="${requestScope.customer.cusLoginName}" readonly /><br>
        <label>昵称:</label>
        <input type="text" name="cusName" value="${requestScope.customer.cusName}" /><br>
        <label>密码:</label>
        <input type="text"  name="cusPassword" value="${requestScope.customer.cusPassword}" /><br>
        <label>性别：</label>
        <input type="radio" name="cusSex" value="男" <c:if test="${requestScope.customer.cusSex=='男'}">checked</c:if> />男
        <input type="radio" name="cusSex" value="女" <c:if test="${requestScope.customer.cusSex=='女'}">checked</c:if> />女 <br>
        <label>爱好：</label>
        <input type="text" name="cusHobby" value="${requestScope.customer.cusHobby}" readonly /><br>
        <label>出生日期：</label>
        <input type="text" name="birthday" value="${requestScope.birthday}" readonly /><br>
        <input type="submit" value="提交" />
    </form>
</div>
<script type="text/javascript">
    var result=document.getElementById("result").value;
    if (result!=null&&result!=''){
        if (result=="success"){
            alert("修改成功!")
            location.href="/doCustomer/cusPersonalInfo?id="+document.getElementsByName("id")[0].value;
        } else {
            alert("修改失败！")
        }
    }
</script>
</body>
</html>
