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
	<link href="<%=path%>/css/add.css" type="text/css"
			rel="stylesheet" />
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
//$('#year').attr('value',x[0].value);
//$('#season').attr('value',x[1].value);
$('#chu').attr('value',x[0].value);
});

	 function express()
		{
			var year = document.getElementById("year").value;
			var season = document.getElementById("season").value;
			var chu = document.getElementById("chu").value;
			window.location = "<%=path%>/exportpscore.action?type=all&year="+year+"&season="+season+"&chu="+chu;
		}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/seasonquery_center.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" border="0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="13" align="center" bordercolor="#FFFFFF"><b>员工季度绩效查询</b>
							</td>
						</tr>
						<tr  >
							<td colspan="13" height="10px">
								<div align="center">
								<!-- 
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
								 -->
								 年度: ${year}
								  季度: ${season}
								<c:if test="${zhis=='0'||authoW=='W'}">
								处室
								<select id="chu" name="chu" style="width: 150px" onchange="gettuan()">
										<option value="wu">-请选择处室名称-</option>
						                 <option value="1">综合与生产管理处</option>
						                 <option value="2">合规与监督二处</option>
						                 <option value="3">通用业务二处</option>
						                 <option value="6">专业处理二处</option>
						                 <option value="5">研发支持二处</option>	
								</select>
								</c:if>	
								<c:if test="${zhis!='0'&&authoW!='W'}">
									<input type="hidden" name="chu" value="${chu}"/>
								</c:if>				
								姓名
								<input style="width:60px" type="text" id="name" name="name" value="${name}"/>			
								<input type="submit" value="查询"/>
							<!-- 
								<input type="button" onclick="express()" value="导出"/>	 -->	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
								<input type="hidden" name="year" value="${year}"/>
								<input type="hidden" name="season" value="${season}"/>
								<input type="hidden" name="para" value="${chu}"/>
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
									<!-- <p><a href="<%=path%>/seasonquery_center.action?sorttype=1&rater=${newnumber}&paixu=${paixus}&chu=${chu}&name=${name}&zhuan=1" style="color:white;">关键业务指标</a></p> -->
									<p>关键业务指标</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>关键任务目标</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>品能目标</p>
								</div></td>
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>加分项</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>季度总分</p>
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
								 <a	href="<%=path%>/seasonkpidetail_query.action?year=${entity.year}&season=${entity.season}&rater=${newnumber}&ratepeople=${entity.newnumber}"><fmt:formatNumber type="number" value="${entity.kpiscore}" pattern="0.00" maxFractionDigits="2"/></a> 
									<!--	<fmt:formatNumber type="number" value="${entity.kpiscore}" pattern="0.000" maxFractionDigits="3"/>-->	
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
								<a	href="<%=path%>/seasonquery_person.action?type=season&year=${entity.year}&season=${entity.season}&querynewnumber=${entity.newnumber}">查看</a>
										</div></td>										
							</tr>
						</c:forEach>	
							<tr class="表格表头背景">
						</tr>
						<tr class="表格表头背景">
							<td colspan="13">
								<div align="center">
								<a	href="<%=path%>/seasonquery_center.action?currentPage=${previousPage}&rater=${newnumber}&paixu=${paixus}&chu=${chu}&name=${name}&zhuan=1"
							style="padding-right: 30px;color: #104E8B">上一页</a> 
									${currentPage} of ${totalPages}
								<a	href="<%=path%>/seasonquery_center.action?currentPage=${nextPage}&rater=${newnumber}&paixu=${paixus}&chu=${chu}&name=${name}&zhuan=1"
							style="padding-right: 30px;color: #104E8B">下一页</a> 	
									共有 ${totalRows} 条记录
								</div></td>
						</tr>
		</table>
		 <br/> <br/> <br/> <br/>
		<table height="80" align="center" cellpadding="0" cellspacing="2" border="0px;">
			<tr>
					<td
						style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
							colspan="13" align="center" bordercolor="#FFFFFF"><b>考核统计</b>
						</td>
			</tr>
			<tr class="btbj" id="hang" style="height:20px">
				<td width="100px" height="40" align="center" valign="middle" nowrap>
				已提交考核：
				</td>
				<td width="1000px"  height="40" align="center" valign="middle" nowrap>
					${alreadyrate}
				</td>
			</tr>
			<tr class="btbj" id="hang" style="height:20px">
				<td width="100px"  height="40" align="center" valign="middle" nowrap>
				未提交考核：
				</td>
				<td width="1000px"  height="40" align="center" valign="middle" nowrap>
				${notrate}
				</td>
			</tr>
		</table>
		</form>
		
  </body>
</html>
