package perform.userinfo.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

public class GetChuAjax {
	
	private String city;
	private String chuname="";
	private String nowtime;
	
	public String getNowtime() {
		return nowtime;
	}

	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getChuname() {
		return chuname;
	}

	public void setChuname(String chuname) {
		this.chuname = chuname;
	}

	public String execute() throws Exception
	{
		if(city.length()>1)
		{
			city = new String(city.getBytes("ISO8859-1"),"UTF-8");
		}
	    if(city.equals("2"))
	    {
	    	chuname="1"+"|"+"2"+"|"+"3"+"|"+"6"+"|"+"5";
	    }
	    else if(city.equals("成都分中心"))
	    {
	    	chuname="综合与生产管理处"+"|"+"合规与监督二处"+"|"+"通用业务二处"+"|"+"专业处理二处"+"|"+"研发支持二处";
	    }
	    else if(city.equals("1"))
		{
			chuname="A"+"|"+"B"+"|"+"C"+"|"+"D"+"|"+"E"+"|"+"F"+"|"+"G";
		}
	    else if(city.equals("业务处理中心"))
		{
			chuname="综合管理处"+"|"+"生产管理处"+"|"+"研发支持一处"+"|"+"通用业务一处"+"|"+"专业处理一处"+"|"+"合规与监督一处"+"|"+"远程支持处";
		}
	    else if(city.equals("3"))
		{
			chuname="U"+"|"+"V";
		}
	    else if(city.equals("武汉园区办"))
		{
			chuname="物业服务处"+"|"+"安保及基础设施运维处";
		}
		return "success";
	}
}
