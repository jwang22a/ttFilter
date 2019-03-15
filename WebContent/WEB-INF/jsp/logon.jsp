<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript">
//请求json响应json
function aa(){
	window.location.href='http://localhost:8081/Process/items/list'
};

  <%-- function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	$.ajax({
		type:"POST",
		dataType: "json",
		//url:"<%=request.getContextPath()%>/test/Login1.do",
		url:"<%=request.getContextPath()%>/testParam/test1.do",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//data:{"name":"测试商品","price":99.9},
		data:"name="+name+"&passWord="+passWord, 
		async: false,
		success:function(data){
			alert(JSON.stringify(data));
			code=data.code;
			message=data.message;
			if(code=="-1"){
				alert(message);
			}else{
				alert(message+"!")
				window.location.href='<%=request.getContextPath()%>/test/index.do';
				window.event.returnValue=false;
				
			}
		}
	}); 
}   --%>


//1.
 <%-- function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	$.ajax({
		//type:"POST",
		type:"GET",
		dataType: "json",
		url:"<%=request.getContextPath()%>/testParam/test.do",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//data:{"name":"测试商品","price":99.9},
		data:"userName="+name+"&passWord="+passWord, 
		async: false,
		success:function(data){
			alert(JSON.stringify(data));
		}
	})
};  --%>
//2.
  <%--  function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	var Obj=new Object();
	Obj.userName=name;
	Obj.passWord=passWord;
	
	$.ajax({
		type:"POST",
		//type:"GET",
		dataType: "json",
		url:"<%=request.getContextPath()%>/testParam/test1.do",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//两种传参数的方式都可以
		//data:"userName="+name+"&passWord="+passWord, 
		data:Obj,
		async: false,
		success:function(data){
			 alert(data);
			 //var Obj=new Object();
			 //var Obj1=new Object();
			 //Obj=data;
			 //Obj1=Obj.data;
			 //alert(Obj.code);
			 //alert(Obj1.userName);
			 //alert(Obj1.passWord);
			//alert(JSON.stringify(data));
		}
		
	}); 
}  --%>
//3.
 <%-- function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	var Obj=new Object();
	Obj.userName=name;
	Obj.passWord=passWord;
	$.ajax({
		//type:"POST",
		type:"GET",
		dataType: "json",
		url:"<%=request.getContextPath()%>/testParam/test1.do",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//data:{"name":"测试商品","price":99.9},
		data:"user_name="+name+"&passWord="+passWord, 
		async: false,
		success:function(data){
			 alert(data);
			
		}
	}); 
} --%>


//4.当需要传递Json格式的数据时(contentType:"application/json;charset=utf-8"),后台用@RequestBody接收，否则接收不到
<%-- function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	var Obj=new Object();
	Obj.userName=name;
	Obj.passWord=passWord;
	//alert(JSON.stringify(Obj));
	$.ajax({
		type:"POST",
		//type:"GET",
		dataType: "json",
		url:"<%=request.getContextPath()%>/testParam/test1.do",
		contentType:"application/json;charset=utf-8",
		//contentType:"application/x-www-form-urlencoded",
		//此处必须传json字符串，对象不行
		data:JSON.stringify(Obj),
		//data:"user_name="+name+"&passWord="+passWord, 
		async: false,
		success:function(data){
			 alert(data);
		}
	}); 
}
 --%>

//5.url传递参数,get,post都适用
function toLogon(){
	var name=$("input[name='txtName']").val() 
	var passWord=$("input[name='txtPwd']").val()
	var code;
	var message;
	var Obj=new Object();
	Obj.userName=name;
	Obj.passWord=passWord;
	//alert(JSON.stringify(Obj));
	$.ajax({
		type:"POST",
		//type:"GET",
		dataType: "json",
		url:"<%=request.getContextPath()%>/testParam/test1.do?sessionId=gdadgs",
		//contentType:"application/json;charset=utf-8",
		contentType:"application/x-www-form-urlencoded",
		//此处必须传json字符串，对象不行
		//data:JSON.stringify(Obj),
		data:"user_name="+name+"&passWord="+passWord, 
		async: false,
		success:function(data){
			 alert(data);
		}
	}); 
	alert(11)
}


</script>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>
<title>Insert title here</title>
</head>
<body>
<%-- <FORM id=form1 action="<%=request.getContextPath()%>/items/list.do" name=form1 onsubmit="" method=post> --%>
<FORM id=form1  name=form1 onsubmit="" method=post>
<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
   <TR>
    <TD style="HEIGHT: 105px"><IMG src="${pageContext.request.contextPath }/images/welcome.gif" 
  border=0></TD></TR>
  <TR>
  <TR>
    <TD background=${pageContext.request.contextPath }/images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150><INPUT id=txtName 
                  style="WIDTH: 130px" name=txtName></TD>
                <TD style="HEIGHT: 28px" width=370><SPAN 
                  id=RequiredFieldValidator3 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id=txtPwd style="WIDTH: 130px" 
                  type=password name=txtPwd></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD></TR>
            
                <TD></TD>
                <TD><INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  onclick='toLogon()' 
                  type=image src="${pageContext.request.contextPath }/images/login_button.gif" name=btn> 
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="${pageContext.request.contextPath }/images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM>
</body>
</html>