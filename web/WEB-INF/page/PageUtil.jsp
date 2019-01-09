<%@ page contentType="text/html;charset=UTF-8" language="java" %>
第<b>${sessionScope.Map.pageNumber}</b>页&nbsp;&nbsp;
<a class="layui-btn" href="${sessionScope.Map.ServletUrl}1">首页</a>
    <a class="layui-btn" href="${sessionScope.Map.ServletUrl}${sessionScope.Map.pageNumber-1>0?sessionScope.Map.pageNumber-1:1}">上一页</a>
<a class="layui-btn" href="${sessionScope.Map.ServletUrl}${sessionScope.Map.pageNumber+1>sessionScope.Map.totalPage?sessionScope.Map.totalPage:sessionScope.Map.pageNumber+1}">下一页</a>
<a class="layui-btn" href="${sessionScope.Map.ServletUrl}${sessionScope.Map.totalPage}">尾页</a>
&nbsp;&nbsp;共<b>${sessionScope.Map.totalPage}</b>页 &nbsp;&nbsp;

<select id="jumpNumber">
    <c:forEach begin="1" varStatus="num" end="${sessionScope.Map.totalPage}">
        <option name="option1" value="${num.count}">-${num.count}页-</option>
    </c:forEach>
</select>
&nbsp;&nbsp;
<b class="layui-btn" onclick="jump()">跳转</b>
<script>
    document.getElementsByName("option1")[${sessionScope.Map.pageNumber-1}].selected = true;
    function jump() {
        var number = document.getElementById("jumpNumber").value;
        //open("doTea?action=queryAll&pageNumber="+number,"iframe_context");
        location = "${sessionScope.Map.ServletUrl}"+number;
    }
</script>
