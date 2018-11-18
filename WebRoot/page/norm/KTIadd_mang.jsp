<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>acceptance_data</title>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js" charset= "gbk"></script>
		<script type="text/javascript" src="<%=path%>/js/userajax.js"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
		<link href="<%=path%>/css/add.css" type="text/css"
			rel="stylesheet" />
<style type="text/css">
  .as {
	text-align: center;
}
 span{
	color:red;
  }
  </style>
  
  <script type="text/javascript">
  

   
   function getchu(){
    
    var chus="";
    var	city=document.getElementById('city').value;
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			chus=xmlhttp.responseText;
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			chuname(chus);
		}				
	} 
	xmlhttp.open("GET","getchuajax.action?city="+city+"&nowtime="+time,true);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

 function gettuan(){
    
    var tuans="";
    var	chu=document.getElementById('chu').value;
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			tuans=xmlhttp.responseText;
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			tuanname(tuans);
		}				
	} 
	xmlhttp.open("GET","gettuanajax.action?chu="+chu+"&nowtime="+time,true);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}


function chuname(chus){

var arry= new Array();
arry=chus.split("|"); //字符分割 
var obj=document.getElementById('chu'); 
obj.options.length=0;
obj.options.add(new Option("-请选择处室名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(chuidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
}
} 

function tuanname(tuans){

var arry= new Array();
arry=tuans.split("|"); //字符分割 
var obj=document.getElementById('tuan'); 
obj.options.length=0;
obj.options.add(new Option("-请选择团队名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(tuanidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
}
} 

  </script>
	</head>
	<body>
		<form action="<%=path%>/ktiadd_mang.action" method="post">
			
				<div style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';" align="center" bordercolor="#FFFFFF">
					<b>KTI指标增加</b>
				</div>
               <table  border="1" align="center" cellpadding="1" cellspacing="0">
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">指标名称</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:500px"  type="text" id="name" name="name" />     				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">机构</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				 <select style="width:500px"  id="city" name="city" onchange="getchu()">
										<option value="wu">请选择机构名称</option>
										<option value="1">业务处理中心</option>
										<option value="2">成都分中心</option>
										<option value="3">武汉生产园区管理办公室</option>
				    </select>
    				
    			</td>
    			
    		</tr>
    		
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">处室板块</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select style="width:500px"  id="chu" name="chu" onchange="gettuan()">
						                 <option value="wu">-请选择处室名称-</option>			                        
				    </select>
    				
    			</td>
    			
    		</tr>
    		<tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">团队板块</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select id="tuan" name="tuan" style="width: 500px">
					                    <option value="wu">-请选择团队名称-</option>				
				    </select>
    				
    			</td>
    			
    		</tr>
    	
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">目标值</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<textarea id="target" name="target" rows="20" cols="30"></textarea>
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">分值</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:500px"  type="text" id="score" name="score" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')"/> 
    				
    			</td>
    			
    		</tr>
    		
    	   <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">考核规则</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<textarea id="rule" name="rule" rows="10" cols="30"></textarea>
    			</td>
    			
    		</tr>
    		
    		  <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">操作</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width: 80px" type="button" onclick="javascript:history.go(-1);" value="返回" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   <input style="width: 80px" type="submit" class="but" value="提交" />
    				
    			</td>
    			
    		</tr>
    		 <tr><td colspan="2">&nbsp;<div style="color:red"><s:fielderror></s:fielderror></div></td></tr>
    	</table>
						
		</form>
	</body>

</html>


