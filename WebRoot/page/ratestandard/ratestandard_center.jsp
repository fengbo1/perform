<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js" charset= "gbk"></script>
<script type="text/javascript" src="<%=path%>/js/userajax.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
 <style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	background-color: #ffffff;
}
-->
</style>
<script type="text/javascript">
 $(document).ready(function(){ 

$("tr.btbj:odd").css({"background-color":"#F0F0F0","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
$("tr.btbj:even").css({"background-color":"#bfd3fc","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
var x=document.getElementsByName("para");
$('#city').attr('value',x[0].value);
getchu();
$('#chu').attr('value',x[1].value);
gettuan();
$('#tuan').attr('value',x[2].value);
getzu();
$('#zu').attr('value',x[3].value);
});

 function getchu(){
	    var chus="";
	    var	city=document.getElementById('city').value;
	    city=encodeURI(city);
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
				chuname(chus);
			}				
		} 
		xmlhttp.open("GET","getchuajax.action?city="+city+"&nowtime="+time,false);
		//xmlhttp.open("GET","login.action",true);
		xmlhttp.send();
	}
 function gettuan(){
	    var tuans="";
	    var	chu=document.getElementById('chu').value;
	    chu=encodeURI(chu);
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
				tuanname(tuans);
			}				
		} 
		xmlhttp.open("GET","gettuanajax.action?chu="+chu+"&nowtime="+time,false);
		xmlhttp.send();
	}

	 function getzu(){
	    var zus="";
	    var	chu=document.getElementById('chu').value;
	    var	tuan=document.getElementById('tuan').value;
	    chu=encodeURI(chu);
	    tuan=encodeURI(tuan);
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
				zuname(zus);
			}				
		} 
		xmlhttp.open("GET","getzuajax.action?chu="+chu+"&tuan="+tuan+"&nowtime="+time,false);
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
		 obj.options.add(new Option("-请选择团队-","wu"));
		 for (var i=1;i<arry.length;i++){	
		 	obj.options.add(new Option(tuanidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
		 }
		 }

	 function zuname(zus){

		 var arry= new Array();
		 arry=zus.split("|"); //字符分割 
		 var obj=document.getElementById('zu'); 
		 obj.options.length=0;
		 obj.options.add(new Option("-请选择班组-","wu"));
		 for (var i=1;i<arry.length;i++){	
		 	obj.options.add(new Option(zuidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
		 }
		 }
	 
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/pdp_center.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="13" align="center" bordercolor="#FFFFFF"><b>中心员工绩效与发展计划书</b>
							</td>
						</tr>
						<tr  >
							<td colspan="13" height="10px">
								机构
								<select id="city" name="city" style="width: 120px" onchange="getchu()">
										<option value="wu">-请选择机构-</option>
										<c:if test="${citys=='1'||citys=='2'||authoR=='R'}">
											<option value="1">业务处理中心</option>
											<option value="2">成都分中心</option>
										</c:if>
										<c:if test="${zhis=='0'||citys=='3'||authoR=='R'}">
											<option value="3">武汉生产园区管理办公室</option>
										</c:if>
										
								</select>		
								处室
								<select id="chu" name="chu" style="width: 150px" onchange="gettuan()">
										<option value="wu">-请选择处室-</option>
								</select>				
								团队
								<select id="tuan" name="tuan" style="width: 150px"  onchange="getzu()">
										<option value="wu">-全部-</option>
								</select>		
								班组
								<select id="zu" name="zu" style="width: 100px">
										<option value="wu">-全部-</option>
								</select>	
								姓名
								<input style="width:100px" type="text" id="name" name="name" value="${name}"/>			
								<input type="submit" value="查询"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
								<input type="hidden" name="para" value="${city}"/>
								<input type="hidden" name="para" value="${chu}"/>
								<input type="hidden" name="para" value="${tuan}"/>
								<input type="hidden" name="para" value="${zu}"/>
								<input type="hidden" name="rater" value="${newnumber}"/>	
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="40px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							<td  width="120px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>新一代编号</p>
								</div></td>
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>姓名</p>
								</div></td>
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室</p>
								</div></td>	
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>团队</p>
								</div></td>	
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>班组</p>
								</div></td>
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>岗位</p>
								</div></td>
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>绩效目标任务书</p>
								</div></td>	
						</tr>
						<c:forEach items="${list}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.newnumber}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.name}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontochu(entity.position)}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontotuan(entity.position)}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontozu(entity.position)}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:pnumtoname(entity.pnum)}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<c:if test="${fn:substring(entity.position,0,1)!='0'&&fn:substring(entity.position,0,1)!='1'&&fn:substring(entity.position,0,1)!='2'&&fn:substring(entity.position,0,1)!='3'}">
								<a	href="<%=path%>/pdp_person.action?rater=${entity.newnumber}">显示详情</a>		
										</div></td>
								</c:if>
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
		</table>
		</form>
  </body>
</html>
