<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>流程批量生成系统v1.0</TITLE> 
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript">

 function logout(){
	 
	 	alert("aaa")
		// window.location.href='<%=request.getContextPath()%>/index.html';
	 	//window.event.returnValue=false; 
 }
</script>
</HEAD>
<FRAMESET name=main1 frameSpacing=0 rows=80,* frameBorder=0>
	<FRAME name=top src="<%=request.getContextPath()%>/top.html" frameBorder=0 noResize
		scrolling=no>
	<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*>
		<FRAME name=menu src="<%=request.getContextPath()%>/menu.html" frameBorder=0 noResize>
		<FRAME name=main src="<%=request.getContextPath()%>/welcome.html" frameBorder=0>
	</FRAMESET>
	<NOFRAMES>
		<p>This page requires frames, but your browser does not support
			them.</p>
	</NOFRAMES>
</FRAMESET>
</HTML>
