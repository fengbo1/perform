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
var city=document.getElementById("paracity").value;
var chu=document.getElementById("parachu").value;
var tuan=document.getElementById("paratuan").value;
$('#city').attr('value',city);
 getchumodify(); 
 gettuanmodify(chu);
$('#chu').attr('value',chu);
$('#tuan').attr('value',tuan);
 brbrbr();
 });
 //导出
 function expressout()
	{
		window.location = "<%=path%>/exportktinorm.action";
	}
//删除用户
 function del(id)
{
	var aa= window.confirm("请确认是否删除该指标");
	if (aa) {
		window.location = "<%=path%>/ktidel_mang.action?id="+id;
	}
}

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

function getchu(){
    
    var chus="";
    var	city=document.getElementById('city').value;
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			chus=xmlhttp.responseText;
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			chuname(chus);
		}				
	} 
	xmlhttp.open("GET","getchuajax.action?city="+city+"&nowtime="+time,true);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

 function gettuan(){
    
    var tuans="";
    var	chu=document.getElementById('chu').value;
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
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			tuanname(tuans);
		}				
	} 
	xmlhttp.open("GET","gettuanajax.action?chu="+chu+"&nowtime="+time,true);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
}

function chuname(chus){

var arry= new Array();
arry=chus.split("|"); //字符分割 
var obj=document.getElementById('chu'); 
obj.options.length=0;
obj.options.add(new Option("-请选择处室名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(chuidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
}
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

function tuanname(tuans){

var arry= new Array();
arry=tuans.split("|"); //字符分割 
var obj=document.getElementById('tuan'); 
obj.options.length=0;
obj.options.add(new Option("-请选择团队名称-","wu"));
for (var i=1;i<arry.length;i++){	

	obj.options.add(new Option(tuanidtoname(arry[i]),arry[i])); //这个兼容IE与firefox 
}
} 


function getchumodify(){
    
    var chus="";
    var	city=document.getElementById('city').value;
    var xmlhttp;
    var time=new Date().getTime();
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			chus=xmlhttp.responseText;
			//alert(yesorno);
			
				//document.getElementById("chutuan").innerHTML=arr[0];
			chuname(chus);
		}				
	} 
	xmlhttp.open("GET","getchuajax.action?city="+city+"&nowtime="+time,false);
	//xmlhttp.open("GET","login.action",true);
	xmlhttp.send();
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
   <form action="<%=path%>/ktilistquery.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" >
						<tr>
							<td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="12" align="center" bordercolor="#FFFFFF"><b>关键业绩指标KTI指标库</b>
							</td>
						</tr>
						<tr>
							<td colspan="12" height="10px">
								<div align="center">
						    机构
    				      <select style="width:160px"  id="city" name="city" onchange="getchu()">
										<option value="wu">请选择机构名称</option>
										<c:if test="${citys!='3'}">
										<option value="1">业务处理中心</option>
										<option value="2">成都分中心</option>
										</c:if>
										<c:if test="${(citys=='3')||(zhis=='0')||(authoR=='R')}">
										<option value="3">武汉生产园区管理办公室</option>
										</c:if>
						  </select>
    				
    			
									
									
									处室
									<select style="width:140px"  id="chu" name="chu" onchange="gettuan()">
						                 <option value="wu">-请选择处室名称-</option>			                        
					                </select>
									
									团队
									<select id="tuan" name="tuan" style="width: 160px">
					                    <option value="wu">-请选择团队名称-</option>				
				                    </select>									
							
									<input type="submit" value="查询"/>
									<input type="button" value="全量导出" onclick="expressout()"/>
									<input type="hidden" id="paracity"  name="paracity" value="${city}"/>
									<input type="hidden" id="parachu"  name="parachu" value="${chu}"/>
									<input type="hidden" id="paratuan" name="paratuan" value="${tuan}"/>
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="40px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>指标名称</p>
								</div></td>
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室板块</p>
								</div></td>
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>团队板块</p>
								</div></td>
							<td  width="400px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>目标值</p>
								</div></td>
							<td  width="50px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>分值</p>
								</div></td>
							<td  width="250px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>考核规则</p>
								</div></td>
						  <c:if test="${authoR=='R'}">
							<td  width="80px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>操作</p>
								</div></td>
							</c:if>								
						</tr>
							<c:forEach items="${list}" var="kt" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								
								<td  width="40px" height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
									
								<td width="80px" height="25" align="center" valign="middle" nowrap><div
										align="center">${kt.name}</div></td>		
								<td width="80px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontochu(kt.chu)}</div></td>
								<td width="80px" height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:chutuanTotuan(kt.chu,kt.tuan)}</div></td>
								<td width="400px" height="25" align="center" valign="middle" nowrap><div
										align="left">${kt.target}</div></td>
								<td width="50px" height="25" align="center" valign="middle" nowrap><div
										align="center">${kt.score}</div></td>
								<td width="250px" height="25" align="center" valign="middle" nowrap><div
										align="left">${kt.rule}</div></td>
					
							 <c:if test="${authoR=='R'}">
								<td width="80px" height="25" align="center" valign="middle" nowrap><div
										align="center">
									
										<input type="button" value="删除" onclick="del('${kt.id}')"/>
										
										<!-- <a href="<%=path%>/user.action?type=to_update&id=<s:property value="id"/>">修改</a> -->
											</div>
								</td>
							 </c:if>
							</tr>
							</c:forEach>
							<tr class="表格表头背景">
						
							<td colspan="7">
						 		
							<div align="center">
								<a	href="<%=path%>/ktilistquery.action?city=${city}&chu=${chu}&tuan=${tuan}&currentPage=${previousPage}&newnumber=${newnumber}"
							style="padding-right: 30px;color: #104E8B">上一页</a> 
									${currentPage} of ${totalPages}
								<a	href="<%=path%>/ktilistquery.action?city=${city}&chu=${chu}&tuan=${tuan}&currentPage=${nextPage}&newnumber=${newnumber}"
							style="padding-right: 30px;color: #104E8B">下一页</a> 	
									共有 ${totalRows} 条记录
								</div></td>
						   <c:if test="${authoR=='R'}">
							<td>
							 
							 <input  type="button" value="添加新指标" onclick="location='<%=path%>/page/norm/KTIadd_mang.jsp'"/>	
							 <input type="hidden" name="newnumber" value="${newnumber}"/>	
							</td>
							</c:if>
						</tr>
		</table>
		</form>
  </body>
</html>
