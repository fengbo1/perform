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
	xmlhttp.open("GET","getchuajax.action?city="+city+"&nowtime="+time,false);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

 

function gettuan(){
    
    var chu=document.getElementById("chu").value;
    var tuans="";
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
	
			tuanname(chu,tuans);
		}				
	} 
	xmlhttp.open("GET","gettuanajax.action?chu="+chu+"&nowtime="+time,false);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

 function getzu(){
    
    var chu=document.getElementById("chu").value;
    var tuan=document.getElementById("tuan").value;
    var zus="";
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			zus=xmlhttp.responseText;
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			zuname(chu,zus);
		}				
	} 
	xmlhttp.open("GET","getzuajax.action?chu="+chu+"&tuan="+tuan+"&nowtime="+time,false);
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

function tuanname(chu,tuans){

var arry= new Array();
arry=tuans.split("|"); //字符分割 
var obj=document.getElementById('tuan'); 
obj.options.length=0;
obj.options.add(new Option("-请选择团队名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(tuanidtoname(chu,arry[i]),arry[i])); //这个兼容IE与firefox 
}
} 
function zuname(chu,zus){

var arry= new Array();
arry=zus.split("|"); //字符分割 
var obj=document.getElementById('zu'); 
obj.options.length=0;
obj.options.add(new Option("-请选择班组名称-","wu"));
for (var i=1;i<arry.length;i++){	
    
	obj.options.add(new Option(zuidtoname(chu,arry[i]),arry[i])); //这个兼容IE与firefox 
}
}  
  </script>
	</head>
	<body>
		<form action="<%=path%>/useradd_mang.action" method="post">
			
				<div style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';" align="center" bordercolor="#FFFFFF">
					<b>员工信息增加</b>
				</div>
               <table width="500"  height="300" border="1" align="center" cellpadding="1" cellspacing="0">
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">新一代编号</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:280px"  type="text" id="newnumber" name="newnumber" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')"/> 
    				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">姓名</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:280px"  type="text" id="name" name="name" /> 
    				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">密码</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:280px"  type="password" id="password" name="password" /> 
    				密码长度应该在6-20之间
    				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">职务</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select style="width:280px"  id="zhiwu" name="zhiwu" >
    				                   <option value="wu">-请选择职务-</option>
										<option value="0">主任</option>
										<option value="1">处室负责人</option>
										<option value="2">团队主管</option>
										<option value="4">班组长</option>
										<option value="3">普通员工</option>
										
						</select>
    				
    			</td>
    			
    		</tr>
    		
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">是否有权评分人</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select style="width:280px"  id="canscore" name="canscore" >
										<option value="0">否</option>
										<option value="1">是</option>
																				
						</select>
    				
    			</td>
    			
    		</tr>
    		  <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">处室</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select style="width:280px"  id="chu" name="chu" onchange="gettuan()">
						 <option value="wu">-请选择处室名称-</option>
						  <option value="1">综合与生产管理处</option>
						                 <option value="2">合规与监督二处</option>
						                 <option value="3">通用业务二处</option>
						                 <option value="6">专业处理二处</option>
						                 <option value="5">研发支持二处</option>				                        
					</select>
    				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">团队</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    			<select id="tuan" name="tuan" style="width: 280px" onchange="getzu()">
					  <option value="wu">-请选择团队名称-</option>				
				</select>
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">班组</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select id="zu" name="zu" style="width: 280px">
							<option value="wu">-请选择班组名称-</option>					
					</select>
    				
    			</td>
    			
    		</tr>
    		 <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">岗位</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select id="pos" name="pos" style="width: 280px">
						<option value="0">-请选择岗位名称-</option>
			            <c:forEach items="${listpos}" var="pos" varStatus="status">
        						<option value="${pos.id}">${pos.name}</option>
        				</c:forEach>
						</select>
    				
    			</td>
    			
    		</tr>
    		  <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">系统角色</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<select id="role" name="role" style="width: 280px">
										<option value="wu">-请选择系统角色-</option>
										<option value="W">绩效管理员</option><!-- W -->
										<option value="X">数据管理员</option><!-- X -->
										<option value="E">普通用户</option>
						</select>
    				
    			</td>
    			
    		</tr>
    		  <tr>
    			<td width="200" class="as" >
    				<span style="font-size:12pt;color:black">操作</span><span></span>
    			</td>
    			<td width="300" class="as">
    				<input style="width: 80px" type="button" onclick="javascript:history.go(-1);" value="返回" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   <input style="width: 80px" type="submit" class="but" value="提交" />
    				
    			</td>
    			
    		</tr>
    		 <tr><td colspan="2"><div style="color:red"><s:fielderror></s:fielderror></div></td></tr>
    	</table>
				
					
			
		
		</form>
	</body>

</html>


