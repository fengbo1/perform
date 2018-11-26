<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	  brbrbr();
	  });
  function brbrbr()
	{
		var reg1=new RegExp("；","g"); 
		 var reg2=new RegExp("。","g");  
		 $('td').each(function()//遍历文章；
	             {
	                var html = $(this).html();
	                var newHtml1 = html.replace(reg1, '；<br/>').replace(reg2, '。<br/>');//回车符
	                $(this).html(newHtml1);//更新文章；
	     });
	 }
  </script>
   <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
   <form action="<%=path%>/subseasonkpi_rate.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" style="width:1100px" 
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="11" align="center" bordercolor="#FFFFFF"><b>关键业务指标得分明细</b>
							</td>
						</tr>
						<tr>
							<td colspan="11" height="10px">
								<div align="center">
								年度:${year}&nbsp;&nbsp;&nbsp;&nbsp;季度:${season}&nbsp;&nbsp;&nbsp;&nbsp;姓名:${ratepeoplename}&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>		
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="5%" align="center" valign="middle"
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							<td  width="10%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>指标/任务名称</p>
								</div></td>
							<td  width="20%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>目标值/任务目标</p>
								</div></td>
							<td  width="25%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>	
							<td  width="5%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>
							<td  width="5%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>期末得分</p>
								</div></td>
							<td  width="15%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>备注</p>
								</div></td>
						</tr>
						<c:forEach items="${list}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td  align="center" valign="middle" ><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td align="center" valign="middle" ><div
										align="center">${entity.kpiname}</div></td>
								<td  align="left" valign="middle" >
										 ${entity.target}</td>
								<td  align="left" valign="middle" ><div
										align="left">${entity.rule}</div></td>
								<td  align="center" valign="middle" ><div
										align="center">${entity.score}</div></td>
								<td align="center" valign="middle" ><div
										align="center">${entity.sum}</div></td>
								<td align="center" valign="middle"><div
										align="center">${entity.kpipdpname}</div></td>
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
    				<tr class="表格表头背景">
    					<td colspan="11" height="25" align="center" valign="middle" ><div
										align="center">
								<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
							</div></td>
    				</tr>
		</table>
		</form>
  </body>
</html>
