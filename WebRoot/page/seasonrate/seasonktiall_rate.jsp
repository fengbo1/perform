<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
var type=document.getElementById("type").value;
if(type=='rate')
{
	alert('评分成功');
}
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
 function tijiao() {
     var score = document.getElementsByName("score");
     var num = 0;
     for(var i=0;i<score.length;i++)
     {
          if(score[i].value!="")
          {
          	num++;
          }
     }
    
     if(num!=${fn:length(list)})
     {
        
        alert("输入的分数不能为空！");
		
		return false;
     }
	 var message = "确认提交评分？";
	 if (window.confirm(message)) {
			with(document.forms[0]) {
				action='subseasonkti_rate.action';
				method="post";
				submit();
			}
		} 
 }

 function check(obj,max){
	  var score = obj.value;
	  var arry= new Array();
	  arry=score.split(".");
	  if(arry.length>2)
	  {
		alert("输入的分数不符合规则！");
		obj.focus();
		return false;
		}
	  else if(Number(score)>Number(max))
	  {
		alert("输入的分数超过限制！");
		obj.focus();
		return false;
	   }
	/*  else if(score.length==0)
	  {
		alert("输入的分数不能为空！");
		obj.focus();
		return false;
		}*/
	  }
 function rtn()
 {
	  action='seasonrate_list_rate.action';
		method="post";
		submit();
	} 
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  <form action="<%=path%>/subseasonkti_rate.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" style="width:1100px"
						cellspacing="2" border: 0px;">
						<tr>
							<td
								style="color: #1778C2; padding-top: 0px; padding-bottom: 5px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="7" align="center" bordercolor="#FFFFFF"><b>关键任务指标KTI评分</b>
							</td>
						</tr>
						<tr  >
							<td colspan="7" height="10px">
								<div align="center">
							           处室	:${ps.positionchu}	
								
								<input type="hidden" name="rater" value="${rater}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								评分年份:${year}
								<input type="hidden" name="year" value="${year}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								评分季度:${season}
								<input type="hidden" name="season" value="${season}"/>
								<input type="hidden" name="type" value="${type}"/>
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="5%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							<td  width="15%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>指标/任务名称</p>
								</div></td>
							<td  width="30%" align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>目标值/任务目标</p>
								</div></td>
							<td  width="25%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>	
							<td  width="7%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>	
							<td  width="8%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>评分</p>
								</div></td>
							<td  width="10%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>备注</p>
								</div></td>
						</tr>
						<c:forEach items="${listpu}" var="entity" varStatus="status">
							<tr  colspan="7"  class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" ><div
										align="center">${entity.name}</div></td>
							</tr>
							<c:forEach items="${listkti}" var="kti" varStatus="status">
							  <c:forEach items="${kti}" var="ktiscore" varStatus="status">
							 
							 <tr class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" ><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${ktiscore.ktiname}</div></td>
								<td height="25" align="left" valign="middle" ><div
										align="left">${ktiscore.target}</div></td>
								<td height="25" align="left" valign="middle" ><div
										align="left">${ktiscore.rule}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${ktiscore.score}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">
								<c:if test="${newnumber==ktiscore.rater1}">
								   <c:if test="${ktiscore.result1==0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value=""/>
								  </c:if>
								   <c:if test="${ktiscore.result1!=0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${ktiscore.result1}"/>
								  </c:if>	
								</c:if>		
								<c:if test="${newnumber==ktiscore.rater2}">
									<c:if test="${ktiscore.result2==0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value=""/>
								  </c:if>
								   <c:if test="${ktiscore.result2!=0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${ktiscore.result2}"/>
								  </c:if>	
								</c:if>
								<c:if test="${newnumber==ktiscore.rater3}">
								 <c:if test="${ktiscore.result3==0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value=""/>
								  </c:if>
								   <c:if test="${ktiscore.result3!=0}">
									<input name="score" type="text" onblur="check(this,'${ktiscore.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${ktiscore.result3}"/>
								  </c:if>	
								</c:if>
								</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">
								<c:if test="${newnumber==ktiscore.rater1}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${ktiscore.remark1}"/>
								</c:if>		
								<c:if test="${newnumber==ktiscore.rater2}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${ktiscore.remark2}"/>
								</c:if>
								<c:if test="${newnumber==ktiscore.rater3}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${ktiscore.remark3}"/>
								</c:if>			
										</div></td>
							</tr>
							</c:forEach>
							</c:forEach>
						</c:forEach>	
							
						<tr class="表格表头背景">
						</tr>
    				<tr class="表格表头背景">
    					<td colspan="7" height="25" align="center" valign="middle" ><div
										align="center">
								<c:if test="${type=='rate'}">
									<input type="button" onclick="rtn();" value="返 回" />
								</c:if>		
								<c:if test="${type!='rate'}">
									<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
								</c:if>	
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								<input type="button" onclick="tijiao()"  value="提  交" />
							</div></td>
    				</tr>
		</table>
		</form>
  </body>
</html>
