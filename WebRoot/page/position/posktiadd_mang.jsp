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
<script type="text/javascript" src="<%=path%>/js/userajax.js"></script>
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
var chu=document.getElementById("parachu").value;
$('#chu').attr('value',chu);
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
 	 
function search()
{
	var city = "2";
	var chu = document.getElementById("chu").value;
	var tuan = document.getElementById("tuan").value;
	var posid = document.getElementById("posid").value;
   window.location = "<%=path%>/posktiaddsearch.action?city="+city+"&chu="+chu+"&tuan="+tuan+"&posid="+posid;
	
}  


function tijiao()
{
	var normlist = document.getElementsByName("ktinorm");
	var raterlist = document.getElementsByName("rater");
	var raters = "";
	//var normprop = document.getElementsByName("kbinormprop");
	var i=0;
	for(i=0;i<normlist.length;i++)
	{
	  
	    if(normlist[i].checked)
	    {
             //alert(normlist[i].value);
            // alert(typeof((Number(normlist[i].value)+100000).toString()));
             //alert((Number(normlist[i].value)+100000).toString());
             var prop=document.getElementById((Number(normlist[i].value)+100000).toString()).value;
             var rater=document.getElementById((Number(normlist[i].value)+10000000).toString()).value;
             if(prop=="")
             {
                 alert("选中的指标权重未填！");
                 return;
             }    
 			if(rater=="")
             {
                 alert("指标未选择评分人！");
                 return;
             }
             raters = raters+rater;
             raters = raters+"|";    
	    }
	     
	}
	 with(document.forms[0])
	 {
		action='posaddkti_mang.action?raters='+raters;
	    method="post";
	    submit();
					
	  }
		
}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  ${daohang}
  <form action="posaddkti_mang.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" cellspacing="2" >				
					<tr>
							 <td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="9" align="center" bordercolor="#FFFFFF"><b>关键任务指标选择</b>
							</td>
					</tr>
					<tr>
							<td colspan="9" height="10px">
								<div align="center">
									处室
									<select style="width:140px"  id="chu" name="chu" onchange="gettuan()">
						                 <option value="wu">-请选择处室名称-</option>
						                 <option value="1">综合与生产管理处</option>
						                 <option value="2">合规与监督二处</option>
						                 <option value="3">通用业务二处</option>
						                 <option value="6">专业处理二处</option>
						                 <option value="5">研发支持二处</option>			                        
					                </select>

									 <input type="button" value="查询" onclick="search()"/>
									<input type="hidden" id="parachu"  name="parachu" value="${chu}"/>
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							
							<td  width="50px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
						
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>指标名称</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室板块</p>
								</div></td>	
							<td  width="350px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>目标值</p>
								</div></td>	
							<td  width="50px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>
							<td  width="200px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>操作</p>
								</div></td>	
							<td  width="80px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>权重</p>
								</div></td>
							<td  width="140px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>评分人</p>
								</div></td>													
						</tr>
					<tr>
					<c:forEach items="${listkt}" var="kt" varStatus="status">
					<tr class="btbj" id="hang" style="height:25px">
								
								<td  width="50px" height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								
								<td width="80px" height="25" align="center" valign="middle" ><div
										align="center">${kt.name}</div></td>
								<td width="80px" height="25" align="center" valign="middle" ><div
										align="center">${fb:positiontochu(kt.chu)}</div></td>
								<td width="350px" height="25" align="center" valign="middle" ><div
										align="left">${kt.target}</div></td>
								<td width="50px" height="25" align="center" valign="middle" ><div
										align="center">${kt.score}</div></td>
								<td width="200px" height="25" align="center" valign="middle" ><div
										align="left">${kt.rule}</div></td>
								<td width="80px" height="25" align="center" valign="middle" ><div
										align="center"><input id="${kt.id}" type="checkbox" name="ktinorm"  value="${kt.id}" /></div></td>	
								<td width="80px" height="25" align="center" valign="middle" ><div
										align="center"><input id="${kt.id+100000}"  style="width:50px" type="text" name="ktinormprop" onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')"/></div></td>			
				     			<td width="140px" height="25" align="center" valign="middle" ><div
										align="center">
											 <select style="width:80px"  id="${kt.id+10000000}" name="rater">
											 	<option value="">无</option>
											 	<c:forEach items="${listu}" var="u" varStatus="status">
											 		<option value="${u.newnumber}">${u.name}</option>
											 	</c:forEach>
						 					 </select>
										</div></td>	
				     </tr>
					</c:forEach>
			            </tr>
			  <tr>
    			
    			<td colspan="9" class="as">
    			   <div align="center">
    			   <input style="width: 100px"  type="button" value="下一步" onclick="tijiao()"/>
    			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<input style="width: 100px" type="button" onclick="javascript:history.go(-1);" value="返回" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   			<input type="hidden" name="posid" value="${id}"/>
		   			</div>	
    			</td>
    			
    		</tr>
			  
		</table>
		<br><br><br><br><br>
		</form>
  </body>
</html>
