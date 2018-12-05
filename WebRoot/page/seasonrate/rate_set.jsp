<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>acceptance_data</title>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js" charset= "gbk"></script>
		<script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
		<link href="<%=path%>/css/add.css" type="text/css"
			rel="stylesheet" />
			
		<script type="text/javascript">
			function beginrate()//开始评分
			{
				var message = "确认开始评分？";
				if (window.confirm(message)) {
					with(document.forms[0]) {
						action='rate_begin.action';
						method="post";
						submit();
					}
				}
			}

			function expressun()//导出未评分人
			{
				var message = "确认导出未评分人名单？";
				if (window.confirm(message)) {
					with(document.forms[0]) {
						action='rate_express.action';
						method="post";
						submit();
					}
				}
			}

			function jisuan()//结束并计算
			{
				var message = "确认结束当前评分，并计算总分？";
				if (window.confirm(message)) {
					with(document.forms[0]) {
						action='rate_jisuan.action';
						method="post";
						submit();
					}
				}
			}

			function baocun()//提交并保存
			{
				var message = "确认提交当前评分，并保存到历史数据？";
				if (window.confirm(message)) {
					with(document.forms[0]) {
						action='rate_baocun.action';
						method="post";
						submit();
					}
				}
			}
		
		</script>	
			
		<style type="text/css">
			b{
				color:red;
			}
		
		</style>	
	</head>
	<body>
		<form action="<%=path%>/useradd.action" method="post">
			<div class="layout">
				<div class="title">
					评分设置
				</div>

				<div id="content">
				<div class="four_columns">
							 当前状态：<b>${status}</b>
					<div class="clear"></div>
				</div>
					<div class="four_columns">
						<div class="four_columns_text">
							年度:
						</div>
						<div class="four_columns_input">
						 	<select id="year" name="year" style="width: 100px" >
									<c:forEach items="${listyear}" var="year" varStatus="status">
										<option value="${year}">${year}</option>
									</c:forEach>
							</select>
						</div>
						<div class="four_columns_text">
							季度:
						</div>
						<div class="four_columns_input">
						 	<select id="season" name="season" style="width: 100px" >
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
								</select>
						</div>
					<div class="clear"></div>
				</div>
				<div class="four_columns">
						<div class="four_columns_text">
							<input type="button" onclick="beginrate()" value="开始评分"/>
						</div>
						<!-- 
						<div class="four_columns_text">
						 	<input type="button" onclick="expressun()" value="导出未评分人名单"/>
						</div>
						 -->
						<div class="four_columns_text">
							<input type="button" onclick="jisuan()" value="结束并计算"/>
						</div>
						<div class="four_columns_text">
							<input type="button" onclick="baocun()" value="提交并保存"/>
						</div>
					<div class="clear"></div>
				</div>
				
				<br/><br/><br/>
				<div class="clear"></div>
				<div class="four_columns">
						提示1：评分过程中不能操作【用户管理】、【岗位管理】、【指标管理】<br/>
						提示2：结束评分之后，保存评分之前不能操作【用户管理】、【岗位管理】、【指标管理】、【评分】、【导入数据】，可以修改当季评分内容<br/>
						提示3：提交评分之后不能操作【打分】、【导入数据】，不能修改当季评分内容<br/>
						提示4：绩效管理员可以初始化任一季度评分
					<div class="clear"></div>
				</div>
				
			</div>
		</form>
	</body>

</html>


