<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <form action="#" method="post">
            <input type="hidden" id="result" value="${requestScope.user.id}" readonly />
            <label>用户名:</label>
            <input type="text" name="stuId" value="${requestScope.user.userLoginName}" readonly /><br>
            <label>昵称:</label>
            <input type="text" name="stuName" value="${requestScope.user.userName}" readonly /><br>
            <label>密码:</label>
            <input type="text"  name="stuPwd" value="${requestScope.user.userPassword}" readonly /><br>
            <label>用户ID</label>
            <input type="text" name="userId" value="${requestScope.user.userId}" readonly />
        </form>
    </div>
</body>
</html>
