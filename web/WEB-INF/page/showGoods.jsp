<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <script type="text/javascript" src="/static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        $(function () {
            var url1="/doGoods/ajaxLoadBigClass";
            $.getJSON(url1,function (bigClassList) {
                $.each(bigClassList,function (index,bigClass) {
                    $("select[name='goodsBigId']").append(" <option value=\""+bigClass.id+"\">"+bigClass.bigName+"</option>")
                })
            });
            $("select[name='goodsBigId']").change(function () {
                var bigId=$(this).val();
                alert(bigId);
                if (bigId!=null && bigId!=''){
                    $("select[name='goodsSmalId']").empty();
                    var url2="/doGoods/ajaxLoadSmallClass?smallBigId="+bigId;
                    $.getJSON(url2,function (smallClassList) {
                        $.each(smallClassList,function (index,smallclass) {
                            $("select[name='goodsSmalId']").append("<option value=\""+smallclass.id+"\">"+smallclass.smallName+"</option>")
                        })
                    })
                }else {
                    $("select[name='goodsSmalId']").empty();
                    var url2="/doGoods/ajaxLoadSmallClass?smallBigId=0";
                    $.getJSON(url2,function (smallClassList) {
                        $.each(smallClassList,function (index,smallclass) {
                            $("select[name='goodsSmalId']").append("<option value=\""+smallclass.id+"\">"+smallclass.smallName+"</option>")
                        })
                    })
                }
            })
        })
    </script>
</head>
<body>
<form action="/doGoods/query" method="post">
    <label>商品编号:</label>
    <input type="text" name="id" placeholder="请输入商品编号" />
    <label>商品名称:</label>
    <input type="text" name="goodsName" placeholder="请输入商品名称"  />
    <label>商品所属大分类:</label>
    <select name="goodsBigId" id="goodsBigId">
        <option value="">请选择</option>
    </select>
    <label>商品所属小分类:</label>
    <select name="goodsSmalId">
        <option value="">请选择</option>
    </select>
    <input class="layui-btn " type="submit"  value="查询"/>
</form>
<c:choose>

    <c:when test="${not empty sessionScope.Map.Goods}">
        ${error}
        <table id="stu" width="90%" class="layui-table" lay-size="sm">
            <tr>
                <th>编号</th>
                <th>商品名称</th>
                <th>所属小分类</th>
                <th>商店名称</th>
                <th>原价</th>
                <th>折扣(折)</th>
                <th>剩余数量</th>
                <th>图片</th>
                <th colspan="2">操作</th>
            </tr>
            <c:forEach items="${sessionScope.Map.Goods}" var="goods">
                <tr>
                    <td>${goods.id}</td>
                    <td>${goods.goodsName}</td>
                    <td>${goods.smallclass.smallName}</td>
                    <td>${goods.seller.sellerName}</td>
                    <td>${goods.goodsMoney}</td>
                    <td>${goods.discount.discRate*10}</td>
                    <td>${goods.goodsNumber}</td>
                    <td><img src="/static/images/goodsImage/${goods.goodsImage}" alt="商品图片"></td>
                    <td><b class="layui-btn layui-btn-sm" onclick="del(${goods.id})">删除</b></td>
                    <td><a class="layui-btn layui-btn-sm" href="/doGoods/queryById?id=${goods.id}">修改</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10">
                    <%@include file="PageUtil.jsp"%>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3>暂无商品信息</h3>
    </c:otherwise>
</c:choose>
</body>
<script type="text/javascript">
    function del(id) {
        var delStatus = window.confirm("是否确认删除编号为"+id+"商品");
        if(delStatus){
            open("/doGoods/deleteById?id="+id,"iframe_context");
        }
    }
    if(${not empty error}){
        alert("${error}")
    }

</script>
</html>
