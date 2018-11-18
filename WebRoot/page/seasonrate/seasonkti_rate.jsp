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
jisuan();
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
 function accadd(arg1,arg2)
 {
 	var r1,r2,m;
 	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
 	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
 	m=Math.pow(10,Math.max(r1,r2));
 	return(arg1*m+arg2*m)/m;
 	
 }
 function jisuan()
 {
 	 var score = document.getElementsByName("score");
     var num = 0;
     for(var i=0;i<score.length;i++)
     {
          if(score[i].value!="")
          {
          	num=accadd(Number(num),Number(score[i].value));
          }
     }
    document.getElementById("sumscore").innerHTML=num;
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
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
								 团队:${ps.positiontuan}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								姓名:${ps.name}
								<input type="hidden" name="ratepeople" value="${ratepeople}"/>
								<input type="hidden" name="rater" value="${rater}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								评分年份:${year}
								<input type="hidden" name="year" value="${year}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
								评分季度:${season}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
								 总分：
								 <span id="sumscore" style="color:red"></span>
								<!--  <input type="text" id="sumscore" style="width:50" value="0" readonly="readonly"/>-->
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
							<!-- <td  width="10%"  align="center" valign="middle" 
								bordercolor=none><div align="center">
									<p>备注</p>
								</div></td> -->
						</tr>
						<c:forEach items="${list}" var="entity" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								<td  height="25" align="center" valign="middle" ><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.ktiname}</div></td>
								<td height="25" align="left" valign="middle" ><div
										align="left">${entity.target}</div></td>
								<td height="25" align="left" valign="middle" ><div
										align="left">${entity.rule}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">${entity.score}</div></td>
								<td height="25" align="center" valign="middle" ><div
										align="center">
								<c:if test="${newnumber==entity.rater1}">
								   <c:if test="${entity.result1==0}">
									<input name="score" type="text"  onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="" />
								  </c:if>
								   <c:if test="${entity.result1!=0}">
									<input name="score" type="text" onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${entity.result1}"/>
								  </c:if>	
								</c:if>		
								<c:if test="${newnumber==entity.rater2}">
									<c:if test="${entity.result2==0}">
									<input name="score" type="text" onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value=""/>
								  </c:if>
								   <c:if test="${entity.result2!=0}">
									<input name="score" type="text" onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${entity.result2}"/>
								  </c:if>	
								</c:if>
								<c:if test="${newnumber==entity.rater3}">
								 <c:if test="${entity.result3==0}">
									<input name="score" type="text" onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value=""/>
								  </c:if>
								   <c:if test="${entity.result3!=0}">
									<input name="score" type="text" onpropertychange="jisuan();" onblur="check(this,'${entity.score}');" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')" maxlength="5"  style="width:70%" value="${entity.result3}"/>
								  </c:if>	
								</c:if>
								</div></td>
								<!--<td height="25" align="center" valign="middle" ><div
										align="center">
								<c:if test="${newnumber==entity.rater1}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${entity.remark1}"/>
								</c:if>		
								<c:if test="${newnumber==entity.rater2}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${entity.remark2}"/>
								</c:if>
								<c:if test="${newnumber==entity.rater3}">
									<input name="remark" type="text" maxlength="200" style="width:80%" value="${entity.remark3}"/>
								</c:if>			
										</div></td>-->
							</tr>
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
