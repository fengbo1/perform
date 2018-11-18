<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js" charset= "gbk"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>	
<script language="javascript" type="text/javascript" src="<%=path%>/js/DatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link href="<%=path%>/css/add.css" type="text/css" rel="stylesheet" />
<title>数据导入</title>
</head>
<body>
	<div style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
			colspan="14" align="center" bordercolor="#FFFFFF"><b>KPI得分导入</b>
	</div>
	<div class="layout">
		<form name="filename" method="post" action="importkpiscore_rate.action"
			enctype="multipart/form-data">
			<div class="title" ><b>KPI得分导入,选择上传文件</b></div>
			<div id="content" >
				<div class="four_columns">
					
					<div class="four_columns_input">
						<input type="file" name="file" />
				
						 <input type="submit" name="submit"value="上传">
					</div>
						<br/>
					<div class="four_columns_input" >
						<a href="<%=path%>/templet/kpiscore.xls">下载【KPI得分表】模板</a>
					</div>
				</div>
			</div>
		</form>
		<table border="1px"width="100%">
				<tr>
					<td colspan="3" class="title">提示：上传文件命名规则</td>
				</tr>
				<tr>
					<td width="30%">&nbsp;</td>
					<td width="35%">文件名规则如下：</td>
					<td width="35%">示例如下：</td>
				</tr>
				<tr>
					<td>KPI得分表：</td>
					<td>年季度&nbsp;+&nbsp;kpiscore</td>
					<td>201701kpiscore.xls</td>
					
				</tr>
				
			</table>
	</div>
	<br/>
	
	
	<!--  
	<div class="layout">
			<form name="filename" method="post" action="exportkqjl.action"
			enctype="multipart/form-data">
			<div class="title">导出</div>
			<div id="content" >
				<div class="four_columns">
					<div class="four_columns_input">
						 <select name="year">
						 	<option value="${year1}">${year1}</option>
						 	<option value="${year2}">${year2}</option>
						 </select>年
						  <select name="month">
						 	<option value="01">1</option>
						 	<option value="02">2</option>
						 	<option value="03">3</option>
						 	<option value="04">4</option>
						 	<option value="05">5</option>
						 	<option value="06">6</option>
						 	<option value="07">7</option>
						 	<option value="08">8</option>
						 	<option value="09">9</option>
						 	<option value="10">10</option>
						 	<option value="11">11</option>
						 	<option value="12">12</option>
						 </select>月
					</div>
					<div class="four_columns_input">
						 <input type="radio" name="type" value="daily" checked/>明细
					</div>
					<div class="four_columns_input">
						<input type="radio" name="type" value="month" />月报
					</div>
					<div class="four_columns_input">
						 <input type="submit" name="submit"value="导出">
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</form>
	</div>
	-->
	
</body>
</html>