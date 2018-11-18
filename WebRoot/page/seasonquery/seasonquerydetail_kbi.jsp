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
brbrbr();
 });

 function brbrbr()
	{
		//var reg1=new RegExp("；","g"); 
		 var reg2=new RegExp("。","g");  
		 $('td').each(function()//遍历文章；
	             {
	                var html = $(this).html();
	                var newHtml1 = html.replace(reg2, '。<br/>');//回车符
	                $(this).html(newHtml1);//更新文章；
	     });
	 }
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/subseasonkbi_rate.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" style="width:1000px"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="11" align="center" bordercolor="#FFFFFF"><b>关键行为指标（KBI）得分明细</b>
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
							<td  width="35%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>目标值/任务目标</p>
								</div></td>
							<td  width="15%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>	
							<td  width="5%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>	
							<c:if test="${(rater1!='')&&(rater1==username||zhis=='0'||zhis=='1'||authoR=='R')}">
								<td  width="5%"  align="center" valign="middle" 
									bordercolor=none><div align="center">
									<p>${rater1}评分</p>
								</div></td>
							</c:if>
							<c:if test="${(rater2!='')&&(rater2==username||zhis=='0'||zhis=='1'||authoR=='R')}">
								<td  width="5%"  align="center" valign="middle" 
									bordercolor=none><div align="center">
									<p>${rater2}评分</p>
								</div></td>
							</c:if>
							<c:if test="${(rater3!='')&&(rater3==username||zhis=='0'||zhis=='1'||authoR=='R')}">
								<td  width="5%"  align="center" valign="middle" 
									bordercolor=none><div align="center">
									<p>${rater3}评分</p>
								</div></td>
							</c:if>	
							<td  width="5%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>期末得分</p>
								</div></td>
							<td  width="10%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>备注</p>
								</div></td>
						</tr>
						<c:forEach items="${list}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" ><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.kbiname}</div></td>
								<td height="25" align="left" valign="middle" ><div
										align="left">${entity.target}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.rule}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.score}</div></td>
									<c:if test="${(entity.rater1!=''&&entity.rater1!=null)&&(entity.rater1==newnumber||zhis=='0'||zhis=='1'||authoR=='R')}">
										<td height="25" align="center" valign="middle" ><div
										align="center">${entity.result1}</div></td>
									</c:if>
									<c:if test="${(entity.rater2!=''&&entity.rater2!=null)&&(entity.rater2==newnumber||zhis=='0'||zhis=='1'||authoR=='R')}">
										<td height="25" align="center" valign="middle" ><div
										align="center">${entity.result2}</div></td>
									</c:if>
									<c:if test="${(entity.rater3!=''&&entity.rater3!=null)&&(entity.rater3==newnumber||zhis=='0'||zhis=='1'||authoR=='R')}">
										<td height="25" align="center" valign="middle" ><div
										align="center">${entity.result3}</div></td>
									</c:if>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.sum}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.remark1}</div></td>
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
    				<tr class="表格表头背景">
    					<td colspan="11" height="25" align="center" valign="middle" nowrap><div
										align="center">
								<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
							</div></td>
    				</tr>
		</table>
		</form>
  </body>
</html>
