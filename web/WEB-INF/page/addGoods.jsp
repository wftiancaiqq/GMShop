<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <base href="<%=request.getContextPath() %>/" />
    <%@include file="layUI.jsp"%>
    <script src="static/js/jquery-1.8.2.min.js"></script>
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
<form action="/doGoods/addGoods" method="post" enctype="multipart/form-data">
    <label>商品名称:</label><input type="text" required id="goodsName" name="goodsName"  />
    <span id="sameName"></span><br>
    <label>商品所属小分类:</label>
    <select name="goodsSmalId">
    </select><br>
    <label>商品原价：</label>
    <input type="number" name="goodsMoney"  required/><br>
    <label>剩余数量:</label>
    <input type="number" value="1" name="goodsNumber"  required/><br>
    <label>运费:</label>
    <input type="number" required name="goodsCarriage" value="0"  /><br>
    <label>折扣:</label>
    <select name="goodsDiscId">
    </select><br>
    <label>是否新品:</label>
    <select name="goodsType">
        <option value="1">新品</option>
        <option value="0">二手</option>
    </select><br>
    <label>商店名称:</label>
    <select name="goodsSellerId">
        <option value="1">马云电子</option>
    </select>
    <br>
    <label>上传商品图片:</label>
    <input type="file" name="goodsImageX" required />
    <b id="imageMsg">文件路径必须是 (文件大小不能超过5M)</b>
    <br>

    <input  type="submit" id="sub"  value="添加" class="layui-btn"/>
</form>
</body>
<script type="text/javascript">
    $(function () {
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
            $.post(url4,function(status){
                if(status=="exists"){
                    alert("该商品名称已经存在！");
                    $("#sub").attr("disabled","disabled")
                }else{
                    $("#sub").removeAttr("disabled");
                }
            },"text");
        });
    })
</script>
</html>


