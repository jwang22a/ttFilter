<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jquery</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

//请求json响应json
function sendJson(){
	alert("11111111")
	$.ajax({
		type:"post",
		dataType: "json",
		//url:"${pageContext.request.contextPath }/items/sendJson.action",
		//url:"http://localhost:8081/Process/items/list",
		url:"<%=request.getContextPath()%>/items/wefweg.do",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//data:{"name":"测试商品","price":99.9},
		 data:"name=小红&age=12", 
		async: false,
		success:function(data){
			alert(JSON.stringify(data));
		}
	});
}

</script>
<script>
    function f1(){
    	alert($('#d1').html());
    	alert($('#d1').text());
    	
    }
    function f2(){
    	$('#d1').html('<p>Hello Hello</p>');
    	alert($('#d1').html());
    }
    function f3(){
    	$(':text').val("Litty");
    }
    function f4(){
    	alert($('#d1').attr('id'));
    	$('#d1').attr('style','color:red');
    }
</script>
</head>
<body>
发送json:<input type="button" value="sendJson" onclick="sendJson()"/>
<div id="d1"><span>Hello Jquery!</span></div><br/>
username<input name="username"/><br/>
<a href="<%=request.getContextPath()%>/items/list.do" onclick="f4();">Dom</a>	
</body>
</html>