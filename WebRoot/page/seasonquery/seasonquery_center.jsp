<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/userajax.js"></script>
	
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
$('#year').attr('value',x[0].value);
$('#season').attr('value',x[1].value);
$('#city').attr('value',x[2].value);
getchu();
$('#chu').attr('value',x[3].value);
gettuan();
$('#tuan').attr('value',x[4].value);
getzu();
$('#zu').attr('value',x[5].value);
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

		 	obj.options.add(new Option(arry[i],arry[i])); //这个兼容IE与firefox 
		 }
		 }

	 function tuanname(tuans){

		 var arry= new Array();
		 arry=tuans.split("|"); //字符分割 
		 var obj=document.getElementById('tuan'); 
		 obj.options.length=0;
		 obj.options.add(new Option("-请选择团队-","wu"));
		 for (var i=1;i<arry.length;i++){	

		 	obj.options.add(new Option(arry[i],arry[i])); //这个兼容IE与firefox 
		 }
		 }

	 function zuname(zus){

		 var arry= new Array();
		 arry=zus.split("|"); //字符分割 
		 var obj=document.getElementById('zu'); 
		 obj.options.length=0;
		 obj.options.add(new Option("-请选择班组-","wu"));
		 for (var i=1;i<arry.length;i++){	
		     
		 	obj.options.add(new Option(arry[i],arry[i])); //这个兼容IE与firefox 
		 }
		 }
	 

	 function express()
		{
			var year = document.getElementById("year").value;
			var season = document.getElementById("season").value;
			var chu = document.getElementById("chu").value;
			var tuan = document.getElementById("tuan").value;
			var zu = document.getElementById("zu").value;
			window.location = "<%=path%>/exportpscore.action?type=all&year="+year+"&season="+season+"&chu="+chu+"&tuan="+tuan+"&zu="+zu;
		}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/seasonquery_center.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="13" align="center" bordercolor="#FFFFFF"><b>员工季度绩效查询</b>
							</td>
						</tr>
						<tr  >
							<td colspan="13" height="10px">
								<div align="center">
								年度	:	
								<select id="year" name="year" style="width: 60px" >
									<c:forEach items="${listyear}" var="year" varStatus="status">
										<option value="${year}">${year}</option>
									</c:forEach>
								</select>			
								 季度
								<select id="season" name="season" style="width: 60px" >
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
								机构
								<select id="city" name="city" style="width: 120px" onchange="getchu()">
										<option value="wu">请选择机构</option>
										<option value="业务处理中心">业务处理中心</option>
										<option value="成都分中心">成都分中心</option>
										<option value="武汉园区办">武汉生产园区管理办公室</option>
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
								<input style="width:60px" type="text" id="name" name="name" value="${name}"/>			
								<input type="submit" value="查询"/>
								<input type="button" onclick="express()" value="导出"/>	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
								<input type="hidden" name="para" value="${year}"/>
								<input type="hidden" name="para" value="${season}"/>
								<input type="hidden" name="para" value="${city}"/>
								<input type="hidden" name="para" value="${chu}"/>
								<input type="hidden" name="para" value="${tuan}"/>
								<input type="hidden" name="para" value="${zu}"/>
								<input type="hidden" name="rater" value="${newnumber}"/>	
								<input type="hidden" name="paixu" value="${paixu}"/>	
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="40px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>新一代编号</p>
								</div></td>
							<td  width="50px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>姓名</p>
								</div></td>
							<td  width="120px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室</p>
								</div></td>	
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>团队</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>班组</p>
								</div></td>
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>岗位</p>
								</div></td>
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键业绩指标</p><a href="<%=path%>/seasonquery_center.action?sorttype=1&rater=${newnumber}&paixu=${paixus}&chu=${chu}&city=${city}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KPI</a>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键任务指标</p><a href="<%=path%>/seasonquery_center.action?sorttype=2&rater=${newnumber}&paixu=${paixus}&chu=${chu}&city=${city}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KTI</a>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键行为指标</p><a href="<%=path%>/seasonquery_center.action?sorttype=3&rater=${newnumber}&paixu=${paixus}&chu=${chu}&city=${city}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KBI</a>
								</div></td>
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键胜任力指标</p><a href="<%=path%>/seasonquery_center.action?sorttype=4&rater=${newnumber}&paixu=${paixus}&chu=${chu}&city=${city}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KCI</a>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>季度总分</p><a href="<%=path%>/seasonquery_center.action?sorttype=5&rater=${newnumber}&paixu=${paixus}&chu=${chu}&city=${city}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">SUM</a>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>得分详情</p>
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
										align="center">${entity.positionchu}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.positiontuan}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.positionzu}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.positionname}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<!--  <a	href="<%=path%>/seasonkpidetail_query.action?score=${entity.kpiscore}&year=${entity.year}&season=${entity.season}&rater=${newnumber}&ratepeople=${entity.newnumber}">${entity.kpiscore}</a>	 -->	
										<fmt:formatNumber type="number" value="${entity.kpiscore}" pattern="0.000" maxFractionDigits="3"/>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonktidetail_query.action?year=${entity.year}&season=${entity.season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.ktiscore}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonkbidetail_query.action?year=${entity.year}&season=${entity.season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kbiscore}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonkcidetail_query.action?year=${entity.year}&season=${entity.season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kciscore}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center"><fmt:formatNumber type="number" value="${entity.score}" pattern="0.000" maxFractionDigits="3"/></div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonquery_person.action?year=${entity.year}&season=${entity.season}&querynewnumber=${entity.newnumber}">查看</a>
										</div></td>										
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
		</table>
		</form>
  </body>
</html>
