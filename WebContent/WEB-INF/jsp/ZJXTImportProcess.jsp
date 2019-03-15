<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>

<title>自建系统账号权限申请</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
 --%><script src="http://oss.sheetjs.com/js-xlsx/xlsx.full.min.js"></script>
</head>
<body>

 <button type="button" class="btn green" id="excell" onclick="method5('dataTable')">下载模板</button>
 <input type="file" onchange="importExcel(this)" />
 <button type="button" class="btn green" id="excell1" onclick="createProcess()">生成工单</button>
<h1></h1>

        <table id="dataTable" style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
		cellSpacing=1 cellPadding=2 rules=all border=0>
            <tr id="asd" style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
                <td style="WIDTH: 9%">工单标题</td>
                <td style="WIDTH: 9%">申请人姓名 </td>
                <td style="WIDTH: 9%">申请人OA </td>
                <td style="WIDTH: 9%">系统名称 </td>
                <td style="WIDTH: 9%">申请人部门 </td>
                <td style="WIDTH: 9%">申请人手机 </td>
                <td style="WIDTH: 9%">CRM账号 </td>
                <td style="WIDTH: 9%">岗位说明 </td>
                <td style="WIDTH: 9%">申请说明</td>
                <td style="WIDTH: 9%">处理人姓名</td>
                <td style="WIDTH: 9%">处理人OA</td>
            </tr>
            <%-- <tr id="wsx"></tr>
            <c:forEach items="${fdata }" var="customer">
            <tr style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
                <td>${customer.标题 }</td>
                <td>${customer.部门 }</td>
                <td>${customer.联系电话 } </td>
                <td>${customer.客户级别 }</td>
                <td>${customer.客户级别_1} </td>
            </tr> 
            </c:forEach> --%>
        </table>
<!-- <div id="demo" style="height: 0px;display: none" border=1></div> -->
<span id="demo"></span>
<script>
    /*
    FileReader共有4种读取方法：
    1.readAsArrayBuffer(file)：将文件读取为ArrayBuffer。
    2.readAsBinaryString(file)：将文件读取为二进制字符串
    3.readAsDataURL(file)：将文件读取为Data URL
    4.readAsText(file, [encoding])：将文件读取为文本，encoding缺省值为'UTF-8'
                 */
    var wb;//读取完成的数据
    var rABS = false; //是否将文件读取为二进制字符串
	var sData=new Array();
    function createProcess(){
    	document.getElementById('excell1').style.backgroundColor ='red'
    		if(window.confirm('你确定生成工单吗？')){
                //alert("确定");
			            $.ajax({
			    		type:"post",
			    		dataType: "json",
			    		url:"<%=request.getContextPath()%>/items/ZJXTCreateProcess.do",
			    		//contentType:"application/json;charset=utf-8",
			    		contentType:"application/x-www-form-urlencoded",
			    		data:"data="+JSON.stringify(sData)+"&processName=accountAuthority", 
			    		async: false,
			    		success:function(data){
			    			//data=JSON.prase(data)
			    			code=data.code;
			    			message=data.message;
			    			if(code=="-1"){
			    				alert(message);
			    			}else{
			    				alert(message);
			    			}
			    		}
			    	}); 
                return true;
             }else{
                //alert("取消");
                return false;
            }
    
    }
    function importExcel(obj) {//导入
        if(!obj.files) {
            return;
        }
        const IMPORTFILE_MAXSIZE = 1*1024;//这里可以自定义控制导入文件大小
        var suffix = obj.files[0].name.split(".")[1]
        if(suffix != 'xls' && suffix !='xlsx'){
            alert('导入的文件格式不正确!')
            return
        }
        if(obj.files[0].size/1024 > IMPORTFILE_MAXSIZE){
            alert('导入的表格文件不能大于1M')
            return
        }
        var f = obj.files[0];
        var reader = new FileReader();
        reader.onload = function(e) {
            var data = e.target.result;
            if(rABS) {
                wb = XLSX.read(btoa(fixdata(data)), {//手动转化
                    type: 'base64'
                });
            } else {
                wb = XLSX.read(data, {
                    type: 'binary'
                });
            }
            //wb.SheetNames[0]是获取Sheets中第一个Sheet的名字
            //wb.Sheets[Sheet名]获取第一个Sheet的数据
            //document.getElementById("demo").innerHTML= JSON.stringify( XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]) );
        	var fdata=XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]])
            var html="";
        	
           /*  for(var i=0;i<fdata.length;i++){
            	html+="<h5>"+JSON.stringify(fdata[i])+"</h5>"
            	alert(fdata[i].部门)
            }   */ 
           /*  for(var i=0;i<fdata.length;i++){
            	html+="<h5>"+fdata[i].标题+"&nbsp;&nbsp;&nbsp;&nbsp;"+fdata[i].部门+"&nbsp;&nbsp;&nbsp;&nbsp;"+fdata[i].联系电话 +"</h5>"
            	
            }  */  
            
        	for(var i=0;i<fdata.length;i++){
        		var Obj=new Object();  
        		Obj.APPLYTITLE=fdata[i].工单标题;
        		Obj.APPLICATIONNAME=fdata[i].申请人姓名;
        		Obj.APPLICATIONOA=fdata[i].申请人OA;
        		Obj.SYSTEMNAME=fdata[i].系统名称 ;
        		Obj.APPLICATIONDEPARTMENT=fdata[i].申请人部门;
        		Obj.PHONE=fdata[i].申请人手机 ;
        		Obj.CRMACCOUNT=fdata[i].CRM账号 ;
        		Obj.DESCRIPTION=fdata[i].岗位说明 ;
        		Obj.APPLICATIONINSTRUCTION=fdata[i].申请说明 ;
        		Obj.USERID=fdata[i].处理人OA ;
        		Obj.USERNAME=fdata[i].处理人姓名 ;
        		sData.push(Obj);
             	html+="<tr style='FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none'><td style='WIDTH: 9%'>"+fdata[i].工单标题 +"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].申请人姓名 +"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].申请人OA  +"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].系统名称 +"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].申请人部门+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].申请人手机+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].CRM账号+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].岗位说明+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].申请说明+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].处理人姓名+"</td>"+
                "<td style='WIDTH: 9%'>"+fdata[i].处理人OA+"</td>"+
            "</tr>"
             }  
            html="<table id='dataTable' style='BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc;margin-top:1px' cellSpacing=1 cellPadding=2 rules=all border=0>"+html+"</table>"
            //alert(html)
            alert(JSON.stringify(sData));
 			document.getElementById("demo").innerHTML=html
        };
        if(rABS) {
            reader.readAsArrayBuffer(f);
        } else {
            reader.readAsBinaryString(f);
        }
    }

    function fixdata(data) { //文件流转BinaryString
        var o = "",
            l = 0,
            w = 10240;
        for(; l < data.byteLength / w; ++l) o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w, l * w + w)));
        o += String.fromCharCode.apply(null, new Uint8Array(data.slice(l * w)));
        return o;
    }

</script>



  <script>
        //打印表格
        var idTmr;
 
        function getExplorer() {
            var explorer = window.navigator.userAgent;
            //ie  
            if(explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox  
            else if(explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome  
            else if(explorer.indexOf("Chrome") >= 0) {
                return 'Chrome';
            }
            //Opera  
            else if(explorer.indexOf("Opera") >= 0) {
                return 'Opera';
            }
            //Safari  
            else if(explorer.indexOf("Safari") >= 0) {
                return 'Safari';
            }
        }
 
        function method5(tableid) {
            if(getExplorer() == 'ie') {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");
                var oWB = oXL.Workbooks.Add();
                var xlsheet = oWB.Worksheets(1);
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                sel.select();
                sel.execCommand("Copy");
                xlsheet.Paste();
                oXL.Visible = true;
 
                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls",
                        "Excel Spreadsheets (*.xls), *.xls");
                } catch(e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);
                    oWB.Close(savechanges = false);
                    oXL.Quit();
                    oXL = null;
                    idTmr = window.setInterval("Cleanup();", 1);
                }
 
            } else {
                tableToExcel(tableid)
            }
        }
 
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
            var uri = 'data:application/vnd.ms-excel;base64,',
                template = '<html><head><meta charset="UTF-8"></head><body><table  border="1">{table}</table></body></html>',
                base64 = function(
                    s) {
                    return window.btoa(unescape(encodeURIComponent(s)))
                },
                format = function(s, c) {
                    return s.replace(/{(\w+)}/g, function(m, p) {
                        return c[p];
                    })
                }
            return function(table, name) {
                if(!table.nodeType)
                    table = document.getElementById(table)
                var ctx = {
                    worksheet: name || 'Worksheet',
                    table: table.innerHTML
                }
                window.location.href = uri + base64(format(template, ctx))
            }
        })()
    </script>
</body>
</html>