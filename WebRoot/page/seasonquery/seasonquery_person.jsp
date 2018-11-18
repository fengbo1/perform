<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <title>My JSP 'authorityfailed.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js" charset= "gbk"></script>
	
  <style type="text/css">
  .as {
	text-align: center;
    }
   .bs {
	text-align: left;
    }
b{
	color:red;
}
  </style>
  
  <script type="text/javascript">
 $(document).ready(function(){ 
 $("tr").css({"background-color":"#F0F8FF","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
//$("tr:even").css({"background-color":"#bfd3fc","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
var x=document.getElementsByName("para");
$('#year').attr('value',x[0].value);
$('#season').attr('value',x[1].value);
});
 </script>
  </head>
  
  <body>
    <form action="seasonquery_person.action" method="post">
    	  <p align="center" style="padding:0px;margin:0px; font-size: 24px;"><strong>员工季度绩效得分</strong></p></br>
       		<p align="center" style="padding:0px;margin:0px; font-size: 16px;">
				年度						
								<select id="year" name="year" style="width: 100px" >
									<c:forEach items="${listyear}" var="year" varStatus="status">
										<option value="${year}">${year}</option>
									</c:forEach>
								</select>
									&nbsp;&nbsp;&nbsp;季度						
								<select id="season" name="season" style="width: 100px" >
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>&nbsp;&nbsp;&nbsp;
								<input type="hidden" name="querynewnumber" value="${querynewnumber}"/>
								<input type="hidden" name="para" value="${year}"/>
								<input type="hidden" name="para" value="${season}"/>
									
				<input type="submit" value="查询"/>
			</p>
			</br>
        <table width="910" height="398" border="1" align="center" cellpadding="1" cellspacing="0">
    		
    		<tr>
    			<td class="as" width="100px" >
    				姓名
    			</td>
    			<td class="as" width="200px">
    				${ps.name}&nbsp;
    			</td>
    			<td class="as" width="100px">
    				新一代编号
    			</td>
    			<td class="as" width="200px">
    				${ps.newnumber}&nbsp;
    			</td>
    			<td class="as" width="100px">
    				岗位
    			</td>
    			<td class="as" width="200px">
    				${ps.positionname}&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td class="as">
    				处室
    			</td>
    			<td class="as">
    				${ps.positionchu}&nbsp;
    			</td>
    			<td class="as">
    				团队
    			</td>
    			<td class="as">
    				${ps.positiontuan}&nbsp;
    			</td>
    			<td class="as">
    				班组
    			</td>
    			<td class="as">
    				${ps.positionzu}&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td class="as" colspan="2">
    				关键业绩指标（KPI）
    			</td>
    			<td class="as" >
    				得分
    			</td>
    			<td class="as" >
    				<fmt:formatNumber type="number" value="${ps.kpiscore*ps.kpiprop}" pattern="0.000" maxFractionDigits="3"/>&nbsp;
    			</td>
    			<td class="as" >
    				排名
    			</td>
    			<td class="as" >
    				${kpirank}/${kpiallrank}
    			</td>
   	
    		</tr>
    		<tr>
    			<td class="as" colspan="2">
    				关键任务指标（KTI）
    			</td>
    			<td class="as" >
    				得分
    			</td>
    			<td class="as" >
    				<fmt:formatNumber type="number" value="${ps.ktiscore*ps.ktiprop}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td class="as" >
    				排名
    			</td>
    			<td class="as" >
    				${ktirank}/${ktiallrank}
    			</td>
    		</tr>
    		<tr>
    			<td class="as" colspan="2">
    				关键行为指标（KBI）
    			</td>
    			<td class="as" >
    				得分
    			</td>
    			<td class="as" >
    				<fmt:formatNumber type="number" value="${ps.kbiscore*ps.kbiprop}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td class="as" >
    				排名
    			</td>
    			<td class="as" >
    				${kbirank}/${kbiallrank}
    			</td>
    		</tr>
    		<tr>
    			<td class="as" colspan="2">
    				关键胜任力指标（KCI）
    			</td>
    			<td class="as" >
    				得分
    			</td>
    			<td class="as" >
    				<fmt:formatNumber type="number" value="${ps.kciscore*ps.kciprop}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td class="as" >
    				排名
    			</td>
    			<td class="as" >
    				${kcirank}/${kciallrank}
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="as" colspan="3">
    				季度绩效评价总得分：<fmt:formatNumber type="number" value="${ps.score}" pattern="0.000" maxFractionDigits="3"/>
    			</td>
    			<td class="as" colspan="3">
    				总得分排名：${scorerank}/${scoreallrank}
    			</td>
    		</tr>
    		
    		<tr>
    			<td class="as" colspan="6">
    				<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
