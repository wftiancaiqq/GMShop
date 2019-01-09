<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script type="text/javascript">
        $(function(){
            var url = "/doSmallClass/ajaxLoadBigClass";
            $.getJSON(url,function(bigClass){
                $.each(bigClass,function (index,bg) {
                    $("select[name='smallBigId']").append("<option value='"+bg.id+"'>"+bg.bigName+"</option>");
                });
                $("option[value='${smallClass.smallBigId}']").attr("selected",true);

            });
        });
    </script>

</head>
<body>
    <form action="/doSmallClass/update" method="post">
        <input type="hidden" id="result" value="${result}">
        <label>编号:</label><input type="text"  name="id" value="${smallClass.id}" readonly/><br>
        <label>小分类类名称:</label><input type="text" id="smallName" name="smallName" value="${smallClass.smallName}" /><span id="sameName"></span><br>
        <label>小分类所属大分类名称:</label>
        <select name="smallBigId">
        </select><br>
        <label>描述信息:</label><input type="text" name="smallText" value="${smallClass.smallText}" /><br>
        <input disabled="disabled" type="submit" id="sub"  value="保存修改" class="layui-btn"/>
    </form>
</body>
<script type="text/javascript">
    $(function () {
        //验证输入的小分类名称是否重名
        $("input[name='smallName']").blur(function(){
            var url1 = "/doSmallClass/ajaxQueryBySmallName?smallName="+$("#smallName").val();
            var smallName=$("#smallName").val();
            if (smallName=='${smallClass.smallName}') {
                $("#sameName").html("当前小分类名称与原名称一样！");
            }else {
                $("#sameName").html("");
            }
            $.post(url1,function(status){
                if(status=="exists"){
                    alert("该小分类名称已经存在！");
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
                location.href="/doSmallClass/query";
            }else {
                alert("修改失败！")
            }
        }
    })
</script>
</html>


