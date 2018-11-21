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
	var posid = document.getElementById("posid").value;
    window.location = "<%=path%>/poskbiaddsearch.action?posid="+posid;
	
} 


function tijiao()
{
	var normlist = document.getElementsByName("kbinorm");
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
             if(prop=="")
             {
                 alert("选中的指标权重未填！");
                 return;
             }    
 
	    }
	     
	}
	 with(document.forms[0])
	 {
		action='posaddkbi_mang.action';
	    method="post";
	    submit();
					
	  }
	
	
} 
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  ${daohang}
  <form action="posaddkbi_mang.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" cellspacing="2" >				
					<tr>
							 <td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="8" align="center" bordercolor="#FFFFFF"><b>品能目标选择</b>
							</td>
					</tr>
					<tr>
			        <td style="padding-left:1px">
						<table height="80" align="center" cellpadding="0" cellspacing="2" >
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="50px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>指标名称</p>
								</div></td>	
							<td  width="400px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>目标值</p>
								</div></td>	
							<td  width="50px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>
							<td  width="150px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>操作</p>
								</div></td>	
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>权重</p>
								</div></td>												
						</tr>
						</table>
						</td>
					</tr>
					<tr>
			        <td style="padding-left:18px">
			         <div id="scroll" align="center" style="overflow-y: scroll; overflow-x: hidden;height:400px">
					<table  align="center" style="border: 0px; " cellpadding="0" cellspacing="2" >
					<c:forEach items="${listkb}" var="kb" varStatus="status">
					<tr class="btbj" id="hang" style="height:25px">
								
								<td  width="50px" height="25" align="center" valign="middle" ><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								
								<td width="100px" height="25" align="center" valign="middle" ><div
										align="center">${kb.name}</div></td>
								<td width="400px" height="25" align="center" valign="middle" ><div
										align="left">${kb.target}</div></td>
								<td width="50px" height="25" align="center" valign="middle" ><div
										align="center">${kb.score}</div></td>
								<td width="150px" height="25" align="center" valign="middle" ><div
										align="left">${kb.rule}</div></td>
								<td width="100px" height="25" align="center" valign="middle" ><div
										align="center"><input id="${kb.id}" type="checkbox"  name="kbinorm" value="${kb.id}" /></div></td>	
								<td width="100px" height="25" align="center" valign="middle" ><div
										align="center"><input id="${kb.id+100000}" style="width:50px" type="text" name="kbinormprop"  onkeyup="this.value=value.replace(/[^\d.]/g,'')"  onafterpaste="this.value=value.replace(/[^\d.]/g,'')"/></div></td>			
				     </tr>
					</c:forEach>
								</table>
			                     </div>  
			                     </td>	
			            </tr>
			  <tr>
    			
    			<td colspan="8" class="as">
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
