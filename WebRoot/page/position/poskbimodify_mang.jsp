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
var x=document.getElementsByName("para");
$('#level').attr('value',x[0].value);  

 var kbinormsave=document.getElementById("kbinormsave").value;
 var kbinormpropsave=document.getElementById("kbinormpropsave").value;
 var kbinorm=kbinormsave.split("、");
 var kbinormprop=kbinormpropsave.split("、");
 for(var i=0;i<kbinorm.length;i++)
 {
 	document.getElementById(kbinorm[i]).checked=true;
 	document.getElementById((Number(kbinorm[i])+100000).toString()).value=kbinormprop[i];  
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
 	  
function search()
{
	var level = document.getElementById("level").value;
	var posid = document.getElementById("posid").value;
    window.location = "<%=path%>/poskbiaddsearch.action?level="+level+"&posid="+posid;
	
}  

function fanhui()
{
	var city = document.getElementById("city").value;
	var tuan = document.getElementById("tuan").value;
	var chu = document.getElementById("chu").value;
	var posid = document.getElementById("posid").value;
    window.location = "<%=path%>/posmodifykbireturn.action?city="+city+"&posid="+posid+"&chu="+chu+"&tuan="+tuan;
	
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
		action='posmodifykbi_mang.action';
	    method="post";
	    submit();
					
	  }
	
	
}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  ${daohang}
  <form action="posmodifykbi_mang.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0" cellspacing="2" >				
					<tr>
							 <td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="8" align="center" bordercolor="#FFFFFF"><b>关键行为指标KBI选择</b>
							</td>
					</tr>
					<tr>
							<td colspan="8" height="10px">
								<div align="center">
									
									所属层级：
									<select id="level" name="level" style="width: 120px">
										<option value="wu">-请选择层级-</option>
										<option value="0">主任</option>
										<option value="1">副主任</option>
										<option value="2">处室主要负责人</option>
										<option value="3">处室负责人</option>
										<option value="4">团队主管</option>
										<option value="5">班组长</option>
										<option value="6">经办岗</option>
									</select>										
							
								    <input type="button" value="查询" onclick="search()"/>
									<input type="hidden" name="para" value="${level}"/>
									<input type="hidden" id="city" name="city" value="${city}"/>
									<input type="hidden" id="chu" name="chu" value="${chu}"/>
									<input type="hidden" id="tuan" name="tuan" value="${tuan}"/>
						</div></td>	
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
							<td  width="100px"  align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>所属层级</p>
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
								
								<td  width="50px" height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
							
								<td width="100px" height="25" align="center" valign="middle" nowrap><div
										align="center">${kb.name}</div></td>
								<td width="100px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontozhi(kb.level)}</div></td>
								<td width="400px" height="25" align="center" valign="middle" nowrap><div
										align="left">${kb.target}</div></td>
								<td width="50px" height="25" align="center" valign="middle" nowrap><div
										align="center">${kb.score}</div></td>
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="left">${kb.rule}</div></td>
								<td width="100px" height="25" align="center" valign="middle" nowrap><div
										align="center"><input id="${kb.id}" type="checkbox"  name="kbinorm" value="${kb.id}" /></div></td>	
								<td width="100px" height="25" align="center" valign="middle" nowrap><div
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
    				<input style="width: 100px" type="button" onclick="fanhui()" value="返回" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   			<input type="hidden" name="posid" value="${id}"/>
		   			<input id ="kbinormsave" type="hidden" name="kbinormsave" value="${kbinormsave}"/>
		   			<input id ="kbinormpropsave" type="hidden" name="kbinormpropsave" value="${kbinormpropsave}"/>
		   			</div>	
    			</td>
    			
    		</tr>
			  
		</table>
		<br><br><br><br><br>
		</form>
  </body>
</html>
