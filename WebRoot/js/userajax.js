function chuidtoname(id)
{
    var result="";
    
    if(id=="1")
    {
       result="综合与生产管理处";
    }
    else if(id=="2")
    {
    	result="合规与监督二处";
    }
    else if(id=="3")
    {
    	result="通用业务二处";
    }
    else if(id=="6")
    {
    	result="专业处理二处";
    }
    else if(id=="5")
    {
    	result="研发支持二处";
    }
    else if(id=="A")
    {
    	result="综合管理处";
    }
    else if(id=="B")
    {
    	result="生产管理处";
    }
    else if(id=="C")
    {
    	result="研发支持一处";
    }
    else if(id=="D")
    {
    	result="通用业务一处";
    }
    else if(id=="E")
    {
    	result="专业处理一处";
    }
    else if(id=="F")
    {
    	result="合规与监督一处";
    }
    else if(id=="G")
    {
    	result="远程支持处";
    }
    else if(id=="U")
    {
    	result="物业服务处";
    }
    else if(id=="V")
    {
    	result="安保及基础设施运维处";
    }
    return result;
}

function tuanidtoname(chu,id)
{
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
    	result="集中授权 团队";
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
    	result="反洗钱团队";
    }
    else if(id=="3")
    {
    	result="柜面团队";
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
       result="智慧柜员机&龙易行团队";
    }
    else if(id=="2")
    {
       result="柜面&智慧银行团队";
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

function zuidtoname(chu,id)
{
    var result="";
    
    if(chu=="5")
    {
    	if(id=="1")
    	{
    		result="智慧柜员机";
    	}
    	else if(id=="2")
    	{
    		result="龙易行";
    	}
    	else if(id=="3")
    	{
    		result="柜面";
    	}
    	else if(id=="4")
    	{
    		result="智慧银行";
    	}
    }
    else if(chu=="2")
    {
    	if(id=="1")
    	{
    		result="稽核";
    	}
    	else if(id=="2")
    	{
    		result="反洗钱";
    	}
    	else if(id=="3")
    	{
    		result="柜面";
    	}
    }
    else{
    	if(id=="1")
    	{
    		result="1组";
    	}
    	else if(id=="2")
    	{
    		result="2组";
    	}
    	else if(id=="3")
    	{
    		result="3组";
    	}
    	else if(id=="4")
    	{
    		result="4组";
    	}
    	else if(id=="5")
    	{
    		result="5组";
    	}
    	else if(id=="6")
    	{
    		result="6组";
    	}
    	else if(id=="7")
    	{
    		result="7组";
    	}
    }
    return result;
}
