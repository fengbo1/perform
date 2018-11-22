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
$('#chu').attr('value',chu);
 });
 //导出
 function expressout()
	{
		window.location = "<%=path%>/exportpos.action?chu=all";
	}
//删除用户
function del(id)
{
	var aa= window.confirm("请确认是否删除该岗位");
	if (aa) {
		window.location = "<%=path%>/posdel_mang.action?id="+id;
	}
}
function modify(id)
{
	//window.location = "<%=path%>/userlist.action";
	window.location = "<%=path%>/posmodify_mang.action?id="+id;
}
function show(id)
{
	//window.location = "<%=path%>/userlist.action";
	window.location = "<%=path%>/posshow.action?pnum="+id;
}

 </script>
 <link href="<%=path%>/css/table_back.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
  ${daohang}
   <form action="<%=path%>/poslist_mang.action" method="post" name="fm1">
					<table height="80" align="center" cellpadding="0"
						cellspacing="2" >
						<tr>
							<td
								style="color: #1778C2; padding-top: 15px; padding-bottom: 15px; border: 0px; font-size: 26px; font-family: '黑体';"
								colspan="12" align="center" bordercolor="#FFFFFF"><b>岗位信息表</b>
							</td>
						</tr>
						<tr>
							<td colspan="12" height="10px">
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
							
									<input type="submit" value="查询"/>
									<input type="button" value="全量导出" onclick="expressout()"/>
									<input type="hidden" id="parachu"  name="parachu" value="${chu}"/>
						</div></td>	
						</tr>
						<tr height="50px" class="表格表头背景1" id="hang">
							<td  width="40px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>序号</p>
								</div></td>
							
							<td  width="250px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>岗位名称</p>
								</div></td>
							<td  width="250px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>处室</p>
								</div></td>
							<td  width="300px" align="center" valign="middle" nowrap
								bordercolor=none><div align="center">
									<p>操作</p>
								</div></td>
															
						</tr>
							<c:forEach items="${list}" var="pos" varStatus="status">
							<tr class="btbj" id="hang" style="height:20px">
								
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${status.index+1+(currentPage-1)*pageSize}</div></td>
								
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${pos.name}</div></td>		
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">${fb:positiontochu(pos.chu)}</div></td>
								<td height="25" align="center" valign="middle" nowrap><div
										align="center">
										<input type="button" value="查看详情" onclick="show('${pos.id}')"/>
										<input type="button" value="删除" onclick="del('${pos.id}')"/>
										<!-- 
										<input type="button" value="修改" onclick="modify('${pos.id}')"/>
										 -->
										<!-- <a href="<%=path%>/user.action?type=to_update&id=<s:property value="id"/>">修改</a> -->
											</div>
								</td>
							
							</tr>
							</c:forEach>
							<tr class="表格表头背景">
						
							<td colspan="3">
						 		
							<div align="center">
								<a	href="<%=path%>/poslist_mang.action?chu=${chu}&currentPage=${previousPage}"
							style="padding-right: 30px;color: #104E8B">上一页</a> 
									${currentPage} of ${totalPages}
								<a	href="<%=path%>/poslist_mang.action?chu=${chu}&currentPage=${nextPage}"
							style="padding-right: 30px;color: #104E8B">下一页</a> 	
									共有 ${totalRows} 条记录
								</div></td>
							<td align="center" >
							 <input type="button" value="添加新岗位" onclick="location='<%=path%>/page/position/posadd_mang.jsp'"/>	
							</td>
						</tr>
		</table>
		</form>
  </body>
</html>
