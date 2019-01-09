<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <style>
        input{
            border-radius: 5px;
            height: 30px;
            font-size: 15px;
            padding-left: 5px;
        }
    </style>
</head>
<body>
<form action="/doCustomer/unionSelect" method="post">
    <label>编号:</label>
    <input type="text" name="id" placeholder="请输入用户编号" />
    <label>姓名:</label>
    <input type="text" name="cusName" placeholder="请输入用户姓名"  />
    <label>性别:</label>
    <select name="cusSex">
        <option value="">请选择</option>
        <option value="男">男</option>
        <option value="女">女</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
    <c:choose>
        <c:when test="${not empty sessionScope.Map.customerList}">
            ${error}
            <table id="stu" width="90%" class="layui-table" lay-size="sm">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>昵称</th>
                    <th>生日</th>
                    <th>爱好</th>
                    <th>邮箱</th>
                    <th>身份证</th>
                    <th>头像</th>
                    <th colspan="2">操作</th>
                </tr>
                <c:forEach items="${sessionScope.Map.customerList}" var="cus">
                    <tr>
                        <td>${cus.id}</td>
                        <td>${cus.cusName}</td>
                        <td>${cus.cusSex}</td>
                        <td>${cus.cusLoginName}</td>
                        <td><fmt:formatDate value="${cus.cusBirthday}" pattern="yyyy-MM-dd"/></td>
                        <td>${cus.cusHobby}</td>
                        <td>${cus.cusEmail}</td>
                        <td>${cus.cusCode}</td>
                        <td><img class="layui-nav-img" width="36px" src="static/back/Image/${cus.cusPhoto}"></td>
                        <td><a class="layui-btn layui-btn-sm" href="doCustomer?action=delById&id=${cus.id}">删除</a></td>
                        <td><a class="layui-btn layui-btn-sm" href="/doCustomer/cusPersonalInfo?id=${cus.id}">修改</a></td>

                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="9">
                        <%@include file="PageUtil.jsp"%>
                    </td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <h3>暂无顾客信息</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
