package perform.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

public class DateTimeUtil {
	private String sumdates;

	Calendar a=Calendar.getInstance();
	
	int year = a.get(Calendar.YEAR); 
	int month = a.get(Calendar.MONTH)+1; 
	int date = a.get(Calendar.DATE); 
	int hour = a.get(Calendar.HOUR_OF_DAY); 
	int minute = a.get(Calendar.MINUTE); 
	int second = a.get(Calendar.SECOND); 
	public int getThisYear()
	{
		return year;
	}
	public int getMonth()
	{
		return month;
	}
	public String getThisSeason()
	{
		if(3<=month&&5>=month)
		{
			return "2";
		}
		else if(6<=month&&8>=month)
		{
			return "3";
		}
		else if(9<=month&&11>=month)
		{
			return "4";
		}
		else
		{
			return "1";
		}
	}
	public String getLastYear()
	{
		return String.valueOf(year-1);
	}
	public String getLastSeason()
	{
		int m = month+1;
		if(3<=m&&5>=m)
		{
			return "1";
		}
		else if(6<=m&&8>=m)
		{
			return "2";
		}
		else if(9<=m&&11>=m)
		{
			return "3";
		}
		else
		{
			return "4";
		}
	}
	public String getDateTime()
	{
		return year+"年"+month+"月"+date+"日"+hour+"时"+minute+"分"+second+"秒";
	}
	
	public String getSimpleDateTime()
	{
		return year+"-"+std(month)+"-"+std(date)+" "+std(hour)+":"+std(minute)+":"+std(second);
	}
	public String getTime()
	{
		return std(hour)+":"+std(minute)+":"+std(second);
	}
	public String std(int input)
	{
		if(input<10)
		{
			return "0"+input;
		}
		else
		{
			return String.valueOf(input);
		}
		
	}
	public String getDate()
	{
		return year+"年"+month+"月"+date+"日";
	}
	public int getIntegerDate()
	{
		return year*10000+month*100+date;
	}
	public String getStringDate()
	{
		return String.valueOf(getIntegerDate());
	}
	public String getThisMonth()
	{
		return String.valueOf(year*100+month);
	}
	/**
	 * 
	 * 获得最近10年list
	 * @return
	 */
	public List<Integer> getLast10Years()
	{
		List<Integer> list = new ArrayList<Integer>();
		int thisyear = getThisYear();
		for(int i=0;i<10;i++)
		{
			list.add(thisyear-i);
		}
		return list;
	}
	public String getNextMonth(String thismonth)
	{
		int year = Integer.parseInt(thismonth.substring(0,4));
		int month = Integer.parseInt(thismonth.substring(4,6));
		if(month==12)
		{
			month=1;
			year+=1;
		}
		else
		{
			month+=1;
		}
		return String.valueOf(year*100+month);
	}
	public String getLastMonth(String thismonth)
	{
		int year = Integer.parseInt(thismonth.substring(0,4));
		int month = Integer.parseInt(thismonth.substring(4,6));
		if(month==1)
		{
			month=12;
			year-=1;
		}
		else
		{
			month-=1;
		}
		return String.valueOf(year*100+month);
	}
	/**
	 * 20150103-->2015年01月03日
	 * @param simple
	 * @return
	 */
	public String simpleToStanderd(String simple)
	{
		return simple.substring(0,4)+"年"+simple.substring(4,6)+"月"+simple.substring(6,8)+"日";
	}
	/**
	 * 返回下一日日期
	 * 传入日期格式2015-11-11或20151111
	 * 返回20151112
	 * @param date
	 * @return
	 */
	public String getNextDay(String date)
	{
		date = date.replace("-", "");
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(4,6));
		int day = Integer.parseInt(date.substring(6,8));
		String nextdate = "";
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);//月份从0开始
		cal.add(Calendar.DATE, 1);
		Date newdate = cal.getTime();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		nextdate = format.format(newdate);
		return nextdate;
	}
	
	public String getBeforeDay(String date)
	{
		date = date.replace("-", "");
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(4,6));
		int day = Integer.parseInt(date.substring(6,8));
		String beforedate = "";
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);//月份从0开始
		cal.add(Calendar.DATE, -1);
		Date newdate = cal.getTime();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		beforedate = format.format(newdate);
		return beforedate;
	}
	
}
