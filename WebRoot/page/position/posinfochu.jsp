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

var chu=document.getElementById("parachu").value;
var tuan=document.getElementById("paratuan").value; 
 gettuanmodify(chu);
$('#tuan').attr('value',tuan);
 });
 //导出
 function expressout()
	{
	 var chu=document.getElementById("parachu").value;
		window.location = "<%=path%>/exportpos.action?chu="+chu;
	}
//删除用户

function show(id)
{
	//window.location = "<%=path%>/userlist.action";
	window.location = "<%=path%>/posshow.action?pnum="+id;
}

function tuannamemodify(tuans){

var arry= new Array();
arry=tuans.split("|"); //字符分割 
var obj=document.getElementById('tuan'); 
obj.options.length=0;
obj.options.add(new Option("-请选择团队名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(tuanidtonamemodify(arry[i]),arry[i])); //这个兼容IE与firefox 
}
} 

function gettuanmodify(chu){
    
    var tuans="";
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
	
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			tuans=xmlhttp.responseText;
	
			tuannamemodify(tuans);
		}				
	} 
	xmlhttp.open("GET","gettuanajax.action?chu="+chu+"&nowtime="+time,false);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

function tuanidtonamemodify(id)
{
 var chu=document.getElementById("parachu").value;
 var result="";
   if(chu=="A")
   { 
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="综合管理团队";
    }
    else if(id=="2")
    {
    	result="财务（采购）管理团队";
    }
    else if(id=="3")
    {
    	result="人力资源（党群）团队";
    }
    }
   else if(chu=="B")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="运营规划与外包管理团队";
    }
    else if(id=="2")
    {
    	result="质量控制与合规管理团队";
    }
    else if(id=="3")
    {
    	result="监控调度与参数运维团队";
    }
   }
   else if(chu=="C")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="需求分析团队";
    }
    else if(id=="2")
    {
    	result="推广响应团队";
    }
   
   }
   else if(chu=="D")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="本币支付结算团队";
    }
    else if(id=="2")
    {
    	result="代理签约团队";
    }
   
   }
    else if(chu=="E")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="柜面外汇团队";
    }
    else if(id=="2")
    {
    	result="外汇汇款清算团队";
    }
   
   }
    else if(chu=="F")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="稽核监督团队";
    }
    else if(id=="2")
    {
    	result="集中授权团队";
    }
   
   }
   
   else if(chu=="G")
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="智慧银行业务团队";
    }
    else if(id=="2")
    {
    	result="集团子公司业务团队";
    }
   
   }
   else if(chu=="1")//综合与生产管理处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="综合与财务管理团队";
    }
    else if(id=="2")
    {
    	result="人力资源团队";
    }
    else if(id=="3")
    {
    	result="生产管理团队";
    }
   }
   else if(chu=="2")//合规与监督二处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="集中稽核团队";
    }
    else if(id=="2")
    {
    	result="集中业务处理团队";
    }
   }
   else if(chu=="3")//通用业务二处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="业务处理团队";
    }
   }
   else if(chu=="4")//专业处理二处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="专业处理团队";
    }
   }
   else if(chu=="5")//研发支持二处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="研发支持团队";
    }
   }
   else if(chu=="U")//物业服务处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="综合保障团队";
    }
    else if(id=="2")
    {
       result="物业服务团队";
    }
   }
   else if(chu=="V")//安保及基础设施运维处
   {
    if(id=="0")
    {
    	result="处室管理";
    }
    else if(id=="1")
    {
       result="安保团队";
    }
    else if(id=="2")
    {
       result="基础设施运维团队";
    }
    else if(id=="3")
    {
       result="商务及合规团队";
    }
    else if(id=="4")
    {
       result="基建团队";
    }
   }
    return result;
}
 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  ${daohang}
   <form action="<%=path%>/poschulist.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" >
						<tr>
							<td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="12" align="center" bordercolor="#FFFFFF"><b>处室岗位信息表</b>
							</td>
						</tr>
						<tr>
							<td colspan="12" height="10px">
								<div align="center">
																		
									团队
									<select id="tuan" name="tuan" style="width: 160px">
										<option value="wu">-请选择团队名称-</option>
									</select>										
							
									<input type="submit" value="查询"/>
									<input type="button" value="全量导出" onclick="expressout()"/>
									<input type="hidden" id="parachu"  name="parachu" value="${chus}"/>
			                        <input type="hidden" id="newnumber"  name="newnumber" value="${newnumber}"/>
									<input type="hidden" id="paratuan" name="paratuan" value="${tuan}"/>
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="40px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							
							<td  width="150px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>岗位名称</p>
								</div></td>
							<td  width="150px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室</p>
								</div></td>
							<td  width="200px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>团队</p>
								</div></td>
						
							<td  width="100px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>操作</p>
								</div></td>
															
						</tr>
							<c:forEach items="${list}" var="pos" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								
								<td  width="40px" height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>	
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="center">${pos.name}</div></td>		
								<td width="150px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontochu(pos.chu)}</div></td>
								<td width="200px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:chutuanTotuan(pos.chu,pos.tuan)}</div></td>								
								<td width="100px" height="25" align="center" valign="middle" nowrap><div
										align="center">
										<input type="button" value="查看详情" onclick="show('${pos.id}')"/>
									
										<!-- <a href="<%=path%>/user.action?type=to_update&id=<s:property value="id"/>">修改</a> -->
											</div>
								</td>
							
							</tr>
							</c:forEach>
							<tr class="表格表头背景">
						
							<td colspan="6">
						 		
							<div align="center">
								<a	href="<%=path%>/poschulist.action?newnumber=${newnumber}&tuan=${tuan}&currentPage=${previousPage}"
							style="padding-right: 30px;color: #104E8B">上一页</a> 
									${currentPage} of ${totalPages}
								<a	href="<%=path%>/poschulist.action?newnumber=${newnumber}&tuan=${tuan}&currentPage=${nextPage}"
							style="padding-right: 30px;color: #104E8B">下一页</a> 	
									共有 ${totalRows} 条记录
								</div></td>
						</tr>
		</table>
		</form>
  </body>
</html>
