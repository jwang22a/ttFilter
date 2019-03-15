<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
 --%>
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
 <script type="text/javascript">

//请求json响应json
function addUser(){

	var userName=$("input[name='userName']").val() 
	var userId=$("input[name='userId']").val()
	var phone=$("input[name='phone']").val()
	var processNo=$("input[name='processNo']").val()
	var processName=$("input[name='processName']").val()
	var partTimeRole=$("input[name='partTimeRole']").val()
	
	var passWord=$("input[name='passWD']").val()
	var code;
	var message;
	$.ajax({
		type:"post",
		dataType: "json",
		url:"<%=request.getContextPath()%>/items/addUser.do",
		contentType:"application/x-www-form-urlencoded",
		data:"userName="+userName+"&userId="+userId+"&phone="+phone+"&processNo="+processNo+"&processName="+processName+"&partTimeRole="+partTimeRole+"&passWord="+passWord, 
		async: false,
		success:function(data){
			//data=JSON.prase(data)
			code=data.code;
			message=data.message;
			if(code=="-1"){
				alert(message);
			}else{
				alert(message)
				//window.location.href='http://localhost:8081/Process/index.html';
				//window.event.returnValue=false; 
				
			}
		}
	}); 
}

</script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		
		method=post>
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>用户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="userName">
								</td>
								<td>用户OA ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="userId">
								</td>
							</TR>
							
							<TR>
								
								<td>联系电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="phone">
								</td>
								<td>流程编号：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="processNo">
								</td>
							</TR>
							
							<TR>
								
								
								<td>流程名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="processName">
								</td>
								<td>身份串 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=300 name="partTimeRole">
								</td>
							</TR>
							<TR>
								<td>密码：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="passWD">
								</td>
								
							</TR>
							
							
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit onclick='addUser()'
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
