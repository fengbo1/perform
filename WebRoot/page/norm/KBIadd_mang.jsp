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
<style type="text/css">
  .as {
	text-align: center;
}
 span{
	color:red;
  }
  </style>
  

	</head>
	<body>
		<form action="<%=path%>/kbiadd_mang.action" method="post">
			
				<div style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';" align="center" bordercolor="#FFFFFF">
					<b>品能指标增加</b>
				</div>
               <table  border="1" align="center" cellpadding="1" cellspacing="0">
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">指标名称</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width:500px"  type="text" id="name" name="name" />     				
    			</td>
    			
    		</tr>
    	
    		 <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">目标值</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<textarea id="target" name="target" rows="20" cols="30"></textarea>
    			</td>
    			
    		</tr>
    	   <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">考核规则</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<textarea id="rule" name="rule" rows="10" cols="30"></textarea>
    			</td>
    			
    		</tr>
    		
    		  <tr>
    			<td width="100" class="as" >
    				<span style="font-size:16pt;color:black">操作</span><span>*</span>
    			</td>
    			<td width="300" class="as">
    				<input style="width: 80px" type="button" onclick="javascript:history.go(-1);" value="返回" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   <input style="width: 80px" type="submit" class="but" value="提交" />
    				
    			</td>
    			
    		</tr>
    		 <tr><td colspan="2">&nbsp;<div style="color:red"><s:fielderror></s:fielderror></div></td></tr>
    	</table>
						
		</form>
	</body>

</html>


