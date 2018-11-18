package perform.util;

import java.math.BigDecimal;

public class Util {
	//public static final String downloadpath = "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/performance/derive/" ;//服务器
	//public static final String downloadpath ="C:/Program Files/apache-tomcat-7.0.59-windows-x86/apache-tomcat-7.0.59/webapps/performance/derive/";
	//public static final String downloadpath ="D:/Program Files/Apache Software Foundation/apache-tomcat-7.0.59/webapps/performance/derive/";
	public static final String downloadpath ="C:/Program Files (x86)/Apache Software Foundation/Tomcat 7.0/webapps/performance/derive/";
	public static final String autho = "00000000000000000000000000";
	public static final String AUTHO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 *double保留小数点后两位 
	 *
	 */
	public static String DoubleTo2(Double num)
	{
		String result="-";
		double temp;
		if(num==null||num.equals("null")||num.SIZE<1)
		{
			result = "-";
		}
		else
		{
			BigDecimal b = new BigDecimal(num+0.00000001);  
			temp = b.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
			if(temp<0.000001&&temp>-0.000001)
			{
				result = "-";
			}
			else
			{
				result = String.valueOf(temp);
			}
		}
		return result;
	}
}
