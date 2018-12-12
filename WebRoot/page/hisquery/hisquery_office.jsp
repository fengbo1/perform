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
});

function tijiao()
{
	 message = "提交之后将无法修改评分！请确认是否提交！";
	 if (window.confirm(message)) {
		with(document.forms[0]) {
			window.location = "<%=path%>/suballrate.action?year=${year}&season=${season}&rater=${newnumber}";
		}
	}
}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/hisquery_office.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border="0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="12" align="center" bordercolor="#FFFFFF"><b>员工季度绩效得分</b>
							</td>
						</tr>
						<tr>
							<td colspan="12" height="10px">
								<div align="center">
									年度：${year}&nbsp;&nbsp;&nbsp;			
									季度：${season}&nbsp;&nbsp;&nbsp;					
									姓名	
								<input style="width:100px" type="text" id="name" name="name" value="${name}"/>
									<input type="submit" value="查询"/>
									
									<input type="hidden" name="year" value="${year}"/>
									<input type="hidden" name="season" value="${season}"/>
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
									<p>关键业务指标</p>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键任务目标</p>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>品能目标</p>
								</div></td>
							<td  width="120px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>加分项</p>
								</div></td>
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>季度总分</p>
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
										   -      
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
										<!-- <fmt:formatNumber type="number" value="${entity.kti}" pattern="0.00" maxFractionDigits="2"/> -->
								<c:if test="${entity.ktirater==newnumber}"><a href="<%=path%>/seasonkti_query.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}">
								查看评分
								</a></c:if>
								<c:if test="${entity.ktirater!=newnumber}">-</c:if>		
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								<c:if test="${entity.kbirater==newnumber}"><a href="<%=path%>/seasonkbi_query.action?year=${year}&season=${season}&rater=${newnumber}&ratepeople=${entity.newnumber}">
								查看评分
								</a></c:if>
								<c:if test="${entity.kbirater!=newnumber}">-</c:if>		
										</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
										-
										</div></td>	
							   	<td height="25" align="center" valign="middle" nowrap><div
										align="center">
								       -
										</div></td>						
							</tr>
						</c:forEach>	
		</table>
		</form>
  </body>
</html>
