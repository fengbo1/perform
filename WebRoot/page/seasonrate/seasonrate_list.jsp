<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
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
gettuan();
$('#tuan').attr('value',x[2].value);
getzu();
$('#zu').attr('value',x[3].value);
});


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
		 

 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/seasonrate_list_rate.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="12" align="center" bordercolor="#FFFFFF"><b>员工季度绩效评分</b>
							</td>
						</tr>
						<tr>
							<td colspan="12" height="10px">
								<div align="center">
									年度：${year}&nbsp;&nbsp;&nbsp;			
									季度：${season}&nbsp;&nbsp;&nbsp;					
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
									
									<input type="hidden" id="chu" name="chu" value="${chu}"/>	
									<input type="hidden" name="para" value="${year}"/>
									<input type="hidden" name="para" value="${season}"/>
									<input type="hidden" name="para" value="${tuan}"/>
									<input type="hidden" name="para" value="${zu}"/>
									<input id="rater" type="hidden" name="rater" value="${rater}"/>
									
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
							<td  width="120px"  align="center" valign="middle" nowrap
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
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键业绩指标</p><a href="<%=path%>/seasonrate_list_rate.action?sorttype=1&rater=${newnumber}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1"  style="color:purple;">KPI</a>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键任务指标</p><a href="<%=path%>/seasonrate_list_rate.action?sorttype=2&rater=${newnumber}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KTI</a>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键行为指标</p><a href="<%=path%>/seasonrate_list_rate.action?sorttype=3&rater=${newnumber}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KBI</a>
								</div></td>
							<td  width="120px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键胜任力指标</p><a href="<%=path%>/seasonrate_list_rate.action?sorttype=4&rater=${newnumber}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">KCI</a>
								</div></td>
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>季度总分</p><a href="<%=path%>/seasonrate_list_rate.action?sorttype=5&rater=${newnumber}&tuan=${tuan}&zu=${zu}&name=${name}&zhuan=1" style="color:purple;">SUM</a>
								</div></td>							
						</tr>
						<c:forEach items="${list2}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.newnumber}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.name}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.chu}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.tuan}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.zu}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.position}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<!-- <a	href="<%=path%>/seasonkpi_rate.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}">${entity.kpi}</a> -->		
										   <fmt:formatNumber type="number" value=" ${entity.kpi}" pattern="0.000" maxFractionDigits="3"/>         
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonkti_rate.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kti}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonkbi_rate.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kbi}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/seasonkci_rate.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kci}" pattern="0.00" maxFractionDigits="2"/></a>
										</div></td>	
							   	<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								       <fmt:formatNumber type="number" value="${entity.score}" pattern="0.000" maxFractionDigits="3"/>
										</div></td>						
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
		</table>
		</form>
  </body>
</html>
