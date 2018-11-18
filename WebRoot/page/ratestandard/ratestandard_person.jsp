<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fb" uri="http://template.fb.com/article/taglib"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
  .b {
	font-weight:bold;
	word-break:break-all;
	}
	.cb {
	text-align: center;
	font-weight:bold;
	word-break:break-all;
	}
	.c {
	text-align: center;
	word-break:break-all;
	}
	.o{
	word-break:break-all;
	}
b{
	color:red;
}
  </style>
  
  <script type="text/javascript">
 $(document).ready(function(){
 
$("tr").css({"background-color":"#F0F8FF","font-family": "黑体","font-size": "14px","font-weight":"lighter" }); 
	 brbrbr();
});
 </script>
 
 
 <script type="text/javascript">
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
    function MergeCell(tableId,kpi,kpis) {
        var tb = document.getElementById(tableId);  
        tb.rows[kpi].cells[0].rowSpan = kpis;
        for(var i=1;i<kpis;i++)
        {
        	tb.rows[kpi+i].removeChild(tb.rows[kpi+i].cells[0]);
        }
    }  
</script>  
 
  </head>
  
  <body>
    <form action="seasonquery_person.action" method="post">
    	  <p align="center" style="padding:0px;margin:0px; font-size: 24px;"><strong>员工绩效与发展计划书（PDP）</strong></p>
        <p align="center" >姓名：${pu.name}&nbsp;&nbsp;&nbsp;处室：${fb:positiontochu(pu.position)}&nbsp;&nbsp;&nbsp; 岗位：${fb:pnumtoname(pp.id)}</p>
        <table id="pdp" style="font-size:14px;width:1130px" border="1" align="center" cellpadding="1" cellspacing="0">
        
    		<tr height="20px">
    			<td class="cb" width="5%">
    				编号
    			</td>
    			<td class="cb" width="17%">
    				指标/任务名称
    			</td>
    			<td class="cb" width="25%">
    				目标值/任务目标
    			</td>
    			<td class="cb" width="10%">
    				分值
    			</td>
    			<td class="cb" width="35%">
    				考核规则
    			</td>
    			<td class="cb" width="8%">
    				备注
    			</td>
    		</tr>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				一、关键业绩指标KPI（<fmt:formatNumber type="number" value="${pp.kpiprop*100}" pattern="0.00" maxFractionDigits="2"/>分）
    			</td>
    		</tr>	
    		<c:forEach items="${listkpi}" var="kpi" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				${kpi.pdpname}
    			</td>
    			<td>
    				${kpi.target}
    			</td>
    			<td>
    				${kpi.score}
    			</td>
    			<td>
    				${kpi.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				二、关键任务指标KTI（<fmt:formatNumber type="number" value="${pp.ktiprop*100}" pattern="0.00" maxFractionDigits="2"/>分）
    			</td>
    		</tr>	
    		<c:forEach items="${listkti}" var="kti" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				${kti.name}
    			</td>
    			<td>
    				${kti.target}
    			</td>
    			<td>
    				<fmt:formatNumber type="number" value="${kti.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				${kti.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				三、关键行为指标KBI（<fmt:formatNumber type="number" value="${pp.kbiprop*100}" pattern="0.00" maxFractionDigits="2"/>分）
    			</td>
    		</tr>	
    		<c:forEach items="${listkbi}" var="kbi" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				${kbi.name}
    			</td>
    			<td>
    				${kbi.target}
    			</td>
    			<td>
    				<fmt:formatNumber type="number" value="${kbi.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				${kbi.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				四、关键胜任力指标KCI（<fmt:formatNumber type="number" value="${pp.kciprop*100}" pattern="0.00" maxFractionDigits="2"/>分）
    			</td>
    		</tr>	
    		<c:forEach items="${listkci}" var="kci" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				${kci.name}
    			</td>
    			<td>
    				${kci.target}
    			</td>
    			<td>
    				<fmt:formatNumber type="number" value="${kci.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				${kci.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<tr>
    			<td class="as" colspan="6" >
    				<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
