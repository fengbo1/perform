package perform.userinfo.action;

public class GetTuanAjax {
	private String chu;
	private String tuanname="";
	private String nowtime;
	
	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}


	public String getChu() {
		return chu;
	}

	public void setChu(String chu) {
		this.chu = chu;
	}

	public String getTuanname() {
		return tuanname;
	}

	public void setTuanname(String tuanname) {
		this.tuanname = tuanname;
	}

	public String execute() throws Exception
	{
		if(chu.length()>1)
		{
			chu = new String(chu.getBytes("ISO8859-1"),"UTF-8");
		}
		if(chu.equals("A"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2"+"|"+"3";
	    }
		else if(chu.equals("综合管理处"))
		{
	    	tuanname="处室管理"+"|"+"综合管理团队"+"|"+"财务（采购）管理团队"+"|"+"人力资源（党群）团队";
		}
	    else if(chu.equals("B"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("生产管理处"))
		{
	    	tuanname="处室管理"+"|"+"运营规划与外包管理团队"+"|"+"质量控制与合规管理团队"+"|"+"监控调度与参数运维团队";
		}
	    else if(chu.equals("C"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("研发支持一处"))
	    {
	    	tuanname="处室管理"+"|"+"需求分析团队"+"|"+"推广响应团队";
	    }
	    else if(chu.equals("D"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("通用业务一处"))
	    {
	    	tuanname="处室管理"+"|"+"本币支付结算团队"+"|"+"代理签约团队";
	    }
	    else if(chu.equals("E"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("专业处理一处"))
	    {
	    	tuanname="处室管理"+"|"+"柜面外汇团队"+"|"+"外汇汇款清算团队";
	    }
	    else if(chu.equals("F"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("合规与监督一处"))
	    {
	    	tuanname="处室管理"+"|"+"稽核监督团队"+"|"+"集中授权团队";
	    }
	    else if(chu.equals("G"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("远程支持处"))
	    {
	    	tuanname="处室管理"+"|"+"智慧银行业务团队"+"|"+"集团子公司业务团队";
	    }
	    else if(chu.equals("1"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("综合与生产管理处"))
	    {
	    	tuanname="处室管理"+"|"+"综合与财务管理团队"+"|"+"人力资源团队"+"|"+"生产管理团队";
	    }
	    else if(chu.equals("2"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("合规与监督二处"))
	    {
	    	tuanname="处室管理"+"|"+"集中稽核团队"+"|"+"集中业务处理团队";
	    }
	    else if(chu.equals("3"))
	    {
	    	tuanname="0"+"|"+"1";
	    }
	    else if(chu.equals("通用业务二处"))
	    {
	    	tuanname="处室管理"+"|"+"业务处理团队";
	    }
	    else if(chu.equals("6"))
	    {
	    	tuanname="0"+"|"+"1";
	    }
	    else if(chu.equals("专业处理二处"))
	    {
	    	tuanname="处室管理"+"|"+"专业处理团队";
	    }
	    else if(chu.equals("5"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("研发支持二处"))
	    {
	    	tuanname="处室管理"+"|"+"智慧柜员机&龙易行团队"+"|"+"柜面&智慧银行团队";
	    }
	    else if(chu.equals("U"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2";
	    }
	    else if(chu.equals("物业服务处"))
	    {
	    	tuanname="处室管理"+"|"+"综合保障团队"+"|"+"物业服务团队";
	    }
	    else if(chu.equals("V"))
	    {
	    	tuanname="0"+"|"+"1"+"|"+"2"+"|"+"3"+"|"+"4";
	    }
	    else if(chu.equals("安保及基础设施运维处"))
	    {
	    	tuanname="处室管理"+"|"+"安保团队"+"|"+"基础设施运维团队"+"|"+"商务及合规团队"+"|"+"基建团队";
	    }
		return "success";
	}
}
