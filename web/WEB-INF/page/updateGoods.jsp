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
<form action="/doGoods/update" method="post" enctype="multipart/form-data">
    <input type="hidden" id="result" value="${result}">
    <label>编号:</label><input type="text"  name="id" value="${goods.id}" readonly/><br>
    <label>商品名称:</label><input type="text" id="goodsName" name="goodsName" value="${goods.goodsName}" /><span id="sameName"></span><br>
    <label>商品所属小分类:</label>
    <select name="goodsSmalId">
    </select><br>
    <label>是否新品:</label>
    <select name="goodsType">
        <option value="1">新品</option>
        <option value="0">二手</option>
    </select><br>
    <label>商店名称:</label>
    <select name="goodsSellerId">
    </select><br>
    <label>商品原价：</label><input type="number" name="goodsMoney" value="${goods.goodsMoney}" /><br>
    <label>剩余数量:</label><input type="number" name="goodsNumber" value="${goods.goodsNumber}" /><br>
    <label>运费:</label><input type="number" name="goodsCarriage" value="${goods.goodsCarriage}" /><br>
    <label>折扣:</label>
    <select name="goodsDiscId">
    </select><br>
    <label>上传商品图片:</label><input type="file" name="goodsImageX" value="" /><br>
    <input disabled="disabled" type="submit" id="sub"  value="保存修改" class="layui-btn"/>
</form>
</body>
<script type="text/javascript">
    $(function () {
        $("select[name=goodsType] option[value='${goods.goodsType}']").attr("selected",true);
        //加载数据库中的小分类
        var url1="/doGoods/ajaxLoadSmallClass";
        $.getJSON(url1,function (smallClassList) {
            $.each(smallClassList,function (index,smallclass) {
                $("select[name=goodsSmalId]").append(" <option value=\""+smallclass.id+"\">"+smallclass.smallName+"</option>");
            });
            $("select[name=goodsSmalId] option[value='${goods.goodsSmalId}']").attr("selected",true);
        });
        //加载数据库中的商品折扣
        var url2="/doGoods/ajaxLoadDiscount";
        $.getJSON(url2,function (discountList) {
            $.each(discountList,function (index,discount) {
                $("select[name=goodsDiscId]").append(" <option value=\""+discount.id+"\">"+discount.discRate+"</option>")
            })
            $("select[name=goodsDiscId] option[value='${goods.goodsDiscId}']").attr("selected",true);
        });
        //加载数据库中的卖家
        var url3="/doGoods/ajaxLoadSeller";
        $.getJSON(url3,function (sellerList) {
            $.each(sellerList,function (index,seller) {
                $("select[name=goodsSellerId]").append(" <option value=\""+seller.id+"\">"+seller.sellerName+"</option>")
            })
            $("select[name=goodsSellerId] option[value='${goods.goodsSellerId}']").attr("selected",true);
        });
        //验证输入的商品名称是否重名
        $("input[name='goodsName']").blur(function(){
            var url4 = "/doGoods/ajaxQueryByGoodsName?goodsName="+$("#goodsName").val();
            var goodsName=$("#goodsName").val();
            if (goodsName=='${goods.goodsName}') {
                $("#sameName").html("当前商品名称与原名称一样！");
            }else {
                $("#sameName").html("");
            }
            $.post(url4,function(status){
                if(status=="exists"){
                    alert("该商品名称已经存在！");
                    $("#sub").attr("disabled","disabled")
                    //$("input[type='submit']").attr("aria-disabled",true);
                }else{
                    $("#sub").removeAttr("disabled");
                    //$("input[type='submit']").attr("aria-disabled",false);
                }
            },"text");
        });
        var result=$("#result").val();
        if (result!=null&&result!=''){
            if (result=="success"){
                alert("修改成功！")
                location.href="/doGoods/query";
            }else {
                alert("修改失败！")
            }
        }
    })
</script>
</html>


