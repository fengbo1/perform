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
tr.locktop{
background-color:#FFFFFF;
position:relative;
top:expression((this.offsetParent.scrollTop>this.parentElement.parentElement.offsetTop?this.offsetParent.scrollTop-this.parentElement.parentElement.offsetTop-1:0)-1);
}
.c_ghbs3{
background-color:#C6E2FF;
font-family:黑体;
font-size:14px;
position:relative;
font-weight:lighter;
top:expression((this.offsetParent.scrollTop>this.parentElement.parentElement.offsetTop?this.offsetParent.scrollTop-this.parentElement.parentElement.offsetTop-1:0)-1);
}
-->
</style>
<script type="text/javascript">
 $(document).ready(function(){ 

$("tr.btbj:odd").css({"background-color":"#F0F0F0","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
$("tr.btbj:even").css({"background-color":"#bfd3fc","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
});
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/pdp_office.action" method="post" name="fm1">
<table id="pol_table_list" cellspacing="0" align="center" style="width: 1150px;height:500px">
			<tr class="locktop">
			<th>
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="8" align="center" bordercolor="#FFFFFF"><b>处室员工绩效与发展计划书</b>
							</td>
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
									<p>岗位</p>
								</div></td>
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>绩效目标任务书</p>
								</div></td>	
						</tr>
						</table>
						</th>
			</tr>
			<tr>
			<td style="padding-left:1px">
			<div id="scroll" align="center" style=" overflow-x: hidden;height:430px">
				<table>
						<c:forEach items="${list}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td width="40px"  height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td width="120px" height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.newnumber}</div></td>
								<td width="80px" height="25" align="center" valign="middle" nowrap><div
										align="center">${entity.name}</div></td>
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontochu(entity.position)}</div></td>
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:pnumtoname(entity.pnum)}</div></td>
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="center">
								<a	href="<%=path%>/pdp_person.action?rater=${entity.newnumber}">显示详情</a>		
								</div></td>
							</tr>
						</c:forEach>	
						</table>
			</div>
			</td>
			</tr>	
		</table>
		</form>
  </body>
</html>
