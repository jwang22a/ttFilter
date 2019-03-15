<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>

<title>本地需求开发导入</title>
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
 --%><script src="http://oss.sheetjs.com/js-xlsx/xlsx.full.min.js"></script>
</head>
<body>
<!-- <input type="file" onchange="importExcel(this)" />
 <button type="button" class="btn green" id="excell" onclick="method5('dataTable')">导出模板</button>
 <button type="button" class="btn green" id="excell" onclick="createProcess()">生成工单</button> -->
<h1>此流程正在紧急开发中...</h1>

       <!--  <table id="dataTable" style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
		cellSpacing=1 cellPadding=2 rules=all border=0>
            <tr id="asd" style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
                <td style="WIDTH: 20%">标题</td>
                <td style="WIDTH: 20%">部门 </td>
                <td style="WIDTH: 20%">联系电话 </td>
                <td style="WIDTH: 20%">客户级别 </td>
                <td style="WIDTH: 20%">客户级别 </td>
            </tr>
           
        </table> -->
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
    	alert("createProcess");
    	$.ajax({
    		type:"post",
    		dataType: "json",
    		url:"<%=request.getContextPath()%>/items/createProcess.do",
    		//contentType:"application/json;charset=utf-8",
    		contentType:"application/x-www-form-urlencoded",
    		data:"data="+JSON.stringify(sData)+"&processName=asdfgrfv", 
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
        		Obj.subject=fdata[i].标题;
        		Obj.department=fdata[i].部门;
        		Obj.phone=fdata[i].联系电话;
        		Obj.leave=fdata[i].客户级别 ;
        		sData.push(Obj);
             	html+="<tr style='FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none'><td style='WIDTH: 20%'>"+fdata[i].标题 +"</td>"+
                "<td style='WIDTH: 20%'>"+fdata[i].部门 +"</td>"+
                "<td style='WIDTH: 20%'>"+fdata[i].联系电话  +"</td>"+
                "<td style='WIDTH: 20%'>"+fdata[i].客户级别 +"</td>"+
                "<td style='WIDTH: 20%'>"+fdata[i].客户级别_1+"</td>"+
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