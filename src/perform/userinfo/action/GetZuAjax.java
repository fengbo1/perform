package perform.userinfo.action;

public class GetZuAjax {
	private String chu;
	private String tuan;
	private String zuname="";
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

	
	public String getTuan() {
		return tuan;
	}

	public void setTuan(String tuan) {
		this.tuan = tuan;
	}

	public String getZuname() {
		return zuname;
	}

	public void setZuname(String zuname) {
		this.zuname = zuname;
	}

	public String execute() throws Exception
	{
		if(chu.length()>1)
		{
			chu = new String(chu.getBytes("ISO8859-1"),"UTF-8");
		}
		if(tuan.length()>1)
		{
			tuan = new String(tuan.getBytes("ISO8859-1"),"UTF-8");
		}
	    if(chu.equals("D")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("通用业务一处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    else if(chu.equals("E")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("专业处理一处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    else if(chu.equals("F")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("合规与监督一处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    else if(chu.equals("G")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("远程支持处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    else if(chu.equals("3")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("通用处理二处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    else if(chu.equals("5")&&tuan.equals("1"))
	    {
	    	zuname="1"+"|"+"2";
	    }
	    else if(chu.equals("5")&&tuan.equals("2"))
	    {
	    	zuname="3"+"|"+"4";
	    }
	    else if(chu.equals("研发支持二处")&&tuan.equals("智慧柜员机&龙易行团队"))
	    {
	    	zuname="智慧柜员机"+"|"+"龙易行";
	    }
	    else if(chu.equals("研发支持二处")&&tuan.equals("柜面&智慧银行团队"))
	    {
	    	zuname="柜面"+"|"+"智慧银行";
	    }
	    else if(chu.equals("6")&&!tuan.equals("0"))
	    {
	    	zuname="1"+"|"+"2"+"|"+"3";
	    }
	    else if(chu.equals("专业处理二处")&&!tuan.equals("处室管理"))
	    {
	    	zuname="班组1"+"|"+"班组2"+"|"+"班组3";
	    }
	    
	    else if(chu.equals("U")&&tuan.equals("2"))
	    {
	    	zuname="A"+"|"+"B";
	    }
	    else if(chu.equals("物业服务处")&&tuan.equals("物业服务团队"))
	    {
	    	zuname="综合维修组"+"|"+"常规物业组";
	    }
	    else if(chu.equals("V"))
	    {
	    	if(tuan.equals("1"))
	    	{
	    		zuname="C"+"|"+"D";
	    	}
	    	else if(tuan.equals("2"))
	    	{
	    		zuname="E"+"|"+"F"+"|"+"G"+"|"+"H";
	    	}
	    }
	    else if(chu.equals("安保及基础设施运维处"))
	    {
	    	if(tuan.equals("安保团队"))
	    	{
	    		zuname="安全管理"+"|"+"消安防管理";
	    	}
	    	else if(tuan.equals("基础设施运维团队"))
	    	{
	    		zuname="能源动力"+"|"+"空调暖通"+"|"+"弱电监控"+"|"+"运行管理";
	    	}
	    	
	    }
		return "success";
	}
}
