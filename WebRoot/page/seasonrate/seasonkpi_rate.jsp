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
  .as {
	text-align: left;
      }
b{
	color:red;
}
  </style>
  <script type="text/javascript">
  $(document).ready(function(){ 
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
  </head>
  
  <body>
    <form action="seasonquery_person.action" method="post">
    	  <p align="center" style="padding:0px;margin:0px; font-size: 24px;"><strong>关键任务指标得分</strong></p>
       		<p align="center" style="padding:0px;margin:0px; font-size: 16px;">
				年度	:${year}					
								
									&nbsp;&nbsp;&nbsp;季度:${season}
								</select>&nbsp;&nbsp;&nbsp;
			</p>
			
        <table width="910" height="398" border="1" align="center" cellpadding="1" cellspacing="0">
    		<tr height="70px">
    			<td colspan="6" class="as">
    				基本信息：
    			</td>
    		</tr>
    		<tr>
    			<td class="as" width="100px">
    				姓名
    			</td>
    			<td class="as" width="200px">
    				${pu.name}&nbsp;
    			</td>
    			<td class="as" width="100px">
    				新一代编号
    			</td>
    			<td class="as" width="200px">
    				${pu.newnumber}&nbsp;
    			</td>
    			<td class="as" width="100px">
    				岗位
    			</td>
    			<td class="as" width="200px">
    				${fb:pnumtoname(pu.pnum)}&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td class="as">
    				处室
    			</td>
    			<td class="as">
    				${fb:positiontochu(pu.position)}&nbsp;
    			</td>
    			<td class="as">
    				团队
    			</td>
    			<td class="as">
    				${fb:positiontotuan(pu.position)}&nbsp;
    			</td>
    			<td class="as">
    				班组
    			</td>
    			<td class="as">
    				${fb:positiontozu(pu.position)}&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td class="as">
    				指标名称
    			</td>
    			<td class="as">
    				${ps.kpiname}&nbsp;
    			</td>
    			<td class="as">
    				指标内容
    			</td>
    			<td class="as">
    				${ps.kpipdpname}&nbsp;
    			</td>
    			<td class="as">
    				目标值
    			</td>
    			<td class="as">
    				${ps.target}&nbsp;
    			</td>
    		</tr>
    		<tr>
    			<td class="as">
    				分值
    			</td>
    			<td class="as">
    				${ps.score}&nbsp;
    			</td>
    			<td class="as">
    				考核规则
    			</td>
    			<td class="as">
    				${ps.rule}&nbsp;
    			</td>
    			<td class="as" >
    				关键业绩指标（KPI）得分：
    			</td>
    			<td class="as" >
    				${score}&nbsp;
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
