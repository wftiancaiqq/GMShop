<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <script type="text/javascript" src="/static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        function del(id) {
            var delStatus = window.confirm("是否确认删除id="+id+"小分类");
            if(delStatus){
                open("/doSmallClass/deleteById?smallId="+id,"iframe_context");
            }
        }
        if(${not empty error}){
            alert("${error}")
        }
    </script>
</head>
<body>
<form action="/doSmallClass/query" method="post">
    <label>编号:</label>
    <input type="text" name="id" placeholder="请输入小分类编号" />
    <label>小分类名称:</label>
    <input type="text" name="smallName" placeholder="请输入小分类名称"  />
    <label>所属大分类:</label>
    <select name="smallBigId">
        <option value="0">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
<c:choose>
    <c:when test="${not empty sessionScope.Map}">
        ${error}
        <table id="stu" width="90%" class="layui-table" lay-size="sm">
            <tr>
                <th>编号</th>
                <th>小分类名称</th>
                <th>所属大分类名称</th>
                <th>小分类描述</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${sessionScope.Map.smallClassList}" var="smallClass">
                <tr>
                    <td>${smallClass.id}</td>
                    <td>${smallClass.smallName}</td>
                    <td>${smallClass.bigclass.bigName}</td>
                    <td>${smallClass.smallText}</td>
                    <td><b class="layui-btn layui-btn-sm" onclick="del(${smallClass.id})" >删除</b></td>
                    <td><a class="layui-btn layui-btn-sm" href="/doSmallClass/queryById?id=${smallClass.id}">修改</a></td>
               </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    <%@include file="PageUtil.jsp"%>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3>暂无大分类信息</h3>
    </c:otherwise>
</c:choose>
</body>
<script type="text/javascript">
    $(function () {
        var url="/doSmallClass/ajaxLoadBigClass";
        $.getJSON(url,function (bigClassList) {
            $.each(bigClassList,function (index,bigClass) {
                $("select[name='smallBigId']").append(" <option value=\""+bigClass.id+"\">"+bigClass.bigName+"</option>")
            })
        });
    })
</script>
</html>
