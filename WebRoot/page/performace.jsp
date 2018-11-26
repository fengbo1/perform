<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//String username = (String)request.getSession().getAttribute("username");
	String IP=request.getRemoteAddr();
	session.setAttribute("IP",IP);

	
%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/cccx_old.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style_old.css" />
<link rel="stylesheet" type="text/css" href="css/style_menu_top.css"/>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>分中心绩效考核工具</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.2.6.js"></script>

<script language="javascript"> 


function startTime()
{

var today=new Date();
var h=today.getHours();
var m=today.getMinutes();
var s=today.getSeconds();
// add a zero in front of numbers<10
m=checkTime(m);
s=checkTime(s);
document.getElementById('txt').innerHTML=h+":"+m+":"+s;
t=setTimeout('startTime()',500);

}

function checkTime(i)
{
if (i<10) 
  {i="0" + i;}
  return i;
}
 
</script>
<script type="text/javascript">

function frame(o)
{
	var id = document.getElementById("id").value;
	var obj = " ";
	obj=o;
	if(obj=="userinfo")
	{
		document.getElementById("frame").src="<%=path%>/userlistall.action";
	}
	else if(obj=="hisquery_person")
	{
		document.getElementById("frame").src="<%=path%>/seasonquery_person.action?querynewnumber=${newnumber}&type=his";
	}
	else if(obj=="hisquery_center")
	{
		document.getElementById("frame").src="<%=path%>/hisquery_center.action?rater=${newnumber}";
	}
	else if(obj=="seasonquery_person")
	{
		document.getElementById("frame").src="<%=path%>/seasonquery_person.action?querynewnumber=${newnumber}&type=season";
	}
	else if(obj=="seasonquery_office")
	{
		document.getElementById("frame").src="<%=path%>/seasonquery_office.action?rater=${newnumber}";
	}
	else if(obj=="seasonquery_center")
	{
		document.getElementById("frame").src="<%=path%>/seasonquery_center.action?rater=${newnumber}";
	}
	else if(obj=="seasonquery_kpi")
	{
		document.getElementById("frame").src="<%=path%>/seasonquery_kpi.action?rater=${newnumber}";
	}
	else if(obj=="seasonrate")
	{
		document.getElementById("frame").src="<%=path%>/seasonrate_list_rate.action?rater=${newnumber}";
	}
	else if(obj=="rate_set")
	{
		document.getElementById("frame").src="<%=path%>/rate_set.action";
	}
	else if(obj=="pdp_person")
	{
		document.getElementById("frame").src="<%=path%>/pdp_person.action?rater=${newnumber}";
	}
	else if(obj=="pdp_office")
	{
		document.getElementById("frame").src="<%=path%>/pdp_office.action?rater=${newnumber}";
	}
	else if(obj=="pdp_center")
	{
		document.getElementById("frame").src="<%=path%>/pdp_center.action?rater=${newnumber}";
	}
	else if(obj=="pwdmodify")
	{
		document.getElementById("frame").src="<%=path%>/userinfo.action?id="+id;
	}
	else if(obj=="kpilist")
	{
		document.getElementById("frame").src="<%=path%>/kpilist_mang.action";
	}
	
	else if(obj=="ktilist")
	{
		document.getElementById("frame").src="<%=path%>/ktilist_mang.action";
	}
	
	else if(obj=="kbilist")
	{
		document.getElementById("frame").src="<%=path%>/kbilist_mang.action";
	}
	
	else if(obj=="kcilist")
	{
		document.getElementById("frame").src="<%=path%>/kcilist_mang.action";
	}
	else if(obj=="kpilistquery")
	{
		document.getElementById("frame").src="<%=path%>/kpilistquery.action";
	}
	
	else if(obj=="ktilistquery")
	{
		document.getElementById("frame").src="<%=path%>/ktilistquery.action?newnumber=${newnumber}";
	}
	
	else if(obj=="kbilistquery")
	{
		document.getElementById("frame").src="<%=path%>/kbilistquery.action";
	}
	
	else if(obj=="kcilistquery")
	{
		document.getElementById("frame").src="<%=path%>/kcilistquery.action";
	}
	else if(obj=="poszxlist")
	{
		document.getElementById("frame").src="<%=path%>/poszxlist.action?newnumber=${newnumber}";
	}
	else if(obj=="poschulist")
	{
		document.getElementById("frame").src="<%=path%>/poschulist.action?newnumber=${newnumber}";
	}
	else if(obj=="poslist")
	{
		document.getElementById("frame").src="<%=path%>/poslist_mang.action";
	}
	else if(obj=="dataimport")
	{
	    
	   document.getElementById("frame").src="<%=path%>/showkpiscoreimport_rate.action";
		//document.getElementById("frame").src="<%=path%>/page/import/dataimport_mang.jsp";
		//document.getElementById("daohang").innerHTML="用户信息->修改密码";
	}
	
}
</script>

<script type="text/javascript">
$(document).ready(function(){

	/* 滑动/展开 */
	$("ul.expmenu li > div.header").click(
	function()
	{
		var arrow = $(this).find("span.arrow");
	
		$(this).parent().find("ul.menu1").slideToggle("fast").parents(".a").siblings(".a").children("ul.menu1").hide(300);
	}
);
	$(".cc").click(
			function()
			{
				$(".cc").css("width","155px");
				$(this).css("width","168px");	
			}
			);
});
</script>
<style>
.menu1 ol { padding-left:15px; border:#E7E7E7 1px solid; border-top:none;background: #f7f2e5;}
.menu1 li i{background-color: #ae9c7e;padding: 1px 4px;color: #fff;text-shadow: 0px 0px 0px rgba(255, 255, 255, 0.8);font-family: 宋体;font-style:normal;}
.menu1 a{color: #3f3f3f;text-decoration: none;}
.menu1 .no {display:none;}
.menu1 ol a{width: 228px;display: block;line-height: 2em;margin-left: 20px;}
</style>

<style media="print" type="text/css"> 
.noprint{display:none;} 
</style> 

</head>
<body onload="startTime()"> 
	<div id="zhuti" style="position:absolute">
		<div id="top" class="noprint">
			<img src="images/logo.gif" width="276" height="50"
				style="margin-top:0px" />
		</div>
		<div class=x_beijing>
			<div class="x_anniu noprint">
				<div>
					<b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b
						class="b4 d1"></b>
					<div class="b d1 k">
						<div class=x_gr>
							<!-- 登陆用户信息 -->
							<br/>
							
							
							<p style="margin:0px；padding:0px;font-size:20px">${username}</p>
							<br/>
							<p style="font-size:16px">欢迎访问业务处理中心绩效评价系统</p>
							<br/>
							<p>
								<input align="right" name="logout" type="button" value="修改密码" onclick="frame('pwdmodify')" />&nbsp;
								<input align="right" name="logout" type="button" value="安全退出" onclick="location='<%=path%>/page/userinfo/logout.jsp'" />
								<input type="hidden" id="id" name="id" value="${id}"/>
							</p>
							<p><%=new SimpleDateFormat("yyyy年MM月dd日").format(new java.util.Date())%></p>
							<p id="txt"></p>
						</div>
					</div>
					<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b
						class="b1b"></b>
				</div>
					<div style="margin-top: 5px; background-color: #188AE7;">
						<ul class="expmenu">
							<li class="a">
								<div class="header">
									<span class="label">考核评分</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('seasonrate')">季度评分</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">查看评分</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('seasonquery_person')">个人绩效得分查询</li>
									<li class="cc"  onclick="frame('seasonquery_center')">中心员工绩效得分查询</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">考核结果</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('hisquery_person')">个人绩效得分查询</li>
								<!-- 
									<li class="cc"  onclick="frame('seasonquery_office')">处室员工绩效得分查询</li> -->	
									<li class="cc"  onclick="frame('hisquery_center')">中心员工绩效得分查询</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">考核管理</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('rate_set')">考核管理</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">绩效考核标准</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('pdp_person')">个人绩效与发展计划书</li>
								<!-- 	<li class="cc"  onclick="frame('pdp_office')">处室员工绩效与发展计划书</li>-->	
									<li class="cc"  onclick="frame('pdp_center')">中心员工绩效与发展计划书</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">指标库管理</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('kpilist')">关键业务指标管理</li>
									<li class="cc"  onclick="frame('ktilist')">关键任务指标管理</li>
									<li class="cc"  onclick="frame('kbilist')">关键品能目标管理</li>
									<li class="cc"  onclick="frame('kcilist')">加分项管理</li>
									<!-- 
									<li class="cc"  onclick="frame('kpilistquery')">关键业务指标</li>
									<li class="cc"  onclick="frame('ktilistquery')">关键任务指标</li>
									<li class="cc"  onclick="frame('kbilistquery')">关键品能目标</li>
									<li class="cc"  onclick="frame('kcilistquery')">加分项</li>
									 -->
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">岗位管理</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('poszxlist')">中心岗位信息查询</li>
									<li class="cc"  onclick="frame('poschulist')">处室岗位信息查询</li>
									<li class="cc"  onclick="frame('poslist')">岗位信息维护</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">用户管理</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('userinfo')">员工信息管理</li>
								</ul>
							</li>
							<li class="a">
								<div class="header">
									<span class="label">数据导入</span>
								</div>
								<ul class="menu1">
									<li class="cc"  onclick="frame('dataimport')">KPI得分导入</li>
								</ul>
							</li>
						</ul>
					</div>
			</div>
			<div class="x_xianshi" style="overflow:hidden">
				<div style="overflow:hidden">
					<b class="b1"></b><b class="b2 d1"></b><b class="b3 d1"></b><b class="b4 d1"></b>
					<div class="b d1 k3">
						<iframe id="frame" class="mainpage"	src="<%=path%>/welcome.action?newnumber=${newnumber}" marginwidth='0' marginheight='0' frameborder='0'></iframe>
					</div>
					<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
				</div>
			</div>
		</div>
	</div>
</body>
</html>