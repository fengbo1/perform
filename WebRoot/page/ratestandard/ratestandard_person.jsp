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
    			<td class="cb" width="14%">
    				指标/任务名称
    			</td>
    			<td class="cb" width="25%">
    				目标值/任务目标
    			</td>
    			<td class="cb" width="10%">
    				分值
    			</td>
    			<td class="cb" width="32%">
    				考核规则
    			</td>
    			<td class="cb" width="14%">
    				备注
    			</td>
    		</tr>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				一、关键业务指标（${kpisum}）
    			</td>
    		</tr>	
    		<c:forEach items="${listkpi}" var="kpi" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				&nbsp;${kpi.name}
    			</td>
    			<td>
    				&nbsp;${kpi.target}
    			</td>
    			<td>
    				&nbsp;${kpi.score}
    			</td>
    			<td>
    				&nbsp;${kpi.rule}
    			</td>
    			<td>
    				&nbsp;${kpi.remark}
    			</td>
    		</tr>
    		</c:forEach>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				二、关键任务目标（${ktisum}）
    			</td>
    		</tr>	
    		<c:forEach items="${listkti}" var="kti" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				&nbsp;${kti.name}
    			</td>
    			<td>
    				&nbsp;${kti.target}
    			</td>
    			<td>
    				&nbsp;<fmt:formatNumber type="number" value="${kti.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				&nbsp;${kti.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<tr height="20px">
    			<td class="b" colspan="6">
    				三、品能目标（${kbisum}）
    			</td>
    		</tr>	
    		<c:forEach items="${listkbi}" var="kbi" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				&nbsp;${kbi.name}
    			</td>
    			<td>
    				&nbsp;${kbi.target}
    			</td>
    			<td>
    				&nbsp;<fmt:formatNumber type="number" value="${kbi.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				&nbsp;${kbi.rule}
    			</td>
    			<td>
    				&nbsp;
    			</td>
    		</tr>
    		</c:forEach>
    		<c:if test="${pp.kciprop!='0'}">
    		<tr height="20px">
    			<td class="b" colspan="6">
    				四、加分项（${kcisum}）
    			</td>
    		</tr>	
    		<c:forEach items="${listkci}" var="kci" varStatus="status">
    		<tr>
    			<td class="cb">
    				${status.index+1}
    			</td>
    			<td>
    				&nbsp;${kci.name}
    			</td>
    			<td>
    				&nbsp;${kci.target}
    			</td>
    			<td>
    				&nbsp;<fmt:formatNumber type="number" value="${kci.score}" pattern="0.00" maxFractionDigits="2"/>
    			</td>
    			<td>
    				&nbsp;${kci.rule}
    			</td>
    			<td>
    				&nbsp;${kci.remark}
    			</td>
    		</tr>
    		</c:forEach>
    		</c:if>
    		<tr>
    			<td class="as"  colspan="2"  align="center">
    				最终考核得分
    			</td>
    			<td class="as" colspan="4" align="center">
    				最终考核得分=关键任务目标得分*${pp.ktiprop}+品能目标得分*${pp.kbiprop}<c:if test="${pp.kciprop!='0'}">+加分项得分</c:if>
    			</td>
    		</tr>
    		<tr>
    			<td class="as" colspan="6" >
    				<input type="button" onclick="javascript:history.go(-1);" value="返 回" />
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
