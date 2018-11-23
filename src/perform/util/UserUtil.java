package perform.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

/**
 * 用户管理相关工具
 * @author htzx
 *
 */
public class UserUtil {

	public static int positionlength=5;
	public static String ywclzx = "'综合管理处','生产管理处','研发支持一处','通用业务一处','专业处理一处','合规与监督一处','远程支持处'";
	public static String ywclzxzhu = "'生产管理处','研发支持一处','专业处理一处','合规与监督一处'";
	public static String ywclzxzhang = "'通用业务一处','远程支持处'";
	public static String cdfzx = "'综合与生产管理处','合规与监督二处','通用业务二处','专业处理二处','研发支持二处'";
	public static String whyqb = "'物业服务处','安保及基础设施运维处'";
	public static String c_ywclzx = "'A','B','C','D','E','F','G'";
	public static String c_cdfzx = "'1','2','3','4','5'";
	public static String c_whyqb = "'W','X'";

	/**
	 * position转处室
	 * @param position
	 * @return
	 */
	public static String positionToChu(String position)
	{
		String result="";
		char chu = ' ';
		if(position.length()!=1&&position.length()!=positionlength)
		{
			result = " ";
		}
		else
		{
			if(position.length()==1)
			{
				chu = position.charAt(0);
			}
			else
			{
				chu = position.charAt(2);
			}
			if(chu=='1')
			{
				result = "综合与生产管理处";
			}
			else if(chu=='2')
			{
				result = "合规与监督二处";
			}
			else if(chu=='3')
			{
				result = "通用业务二处";
			}
			else if(chu=='6')
			{
				result = "专业处理二处";
			}
			else if(chu=='5')
			{
				result = "研发支持二处";
			}
			else if(chu=='A')
			{
				result = "综合管理处";
			}
			else if(chu=='B')
			{
				result = "生产管理处";
			}
			else if(chu=='C')
			{
				result = "研发支持一处";
			}
			else if(chu=='D')
			{
				result = "通用业务一处";
			}
			else if(chu=='E')
			{
				result = "专业处理一处";
			}
			else if(chu=='F')
			{
				result = "合规与监督一处";
			}
			else if(chu=='G')
			{
				result = "远程支持处";
			}
			else if(chu=='U')
			{
				result = "物业服务处";
			}
			else if(chu=='V')
			{
				result = "安保及基础设施运维处";
			}
			else
			{
				result = "无";
			}
		}
		return result;
	}
	
	public static String chuToNumber(String chu)
	{
		String result="";
		if(chu.equals("综合与生产管理处"))
		{
			result = "1";
		}
		else if(chu.equals("合规与监督二处"))
		{
			result = "2";
		}
		else if(chu.equals("通用业务二处"))
		{
			result = "3";
		}
		else if(chu.equals("专业处理二处"))
		{
			result = "6";
		}
		else if(chu.equals("研发支持二处"))
		{
			result = "5";
		}
		else if(chu.equals("综合管理处"))
		{
			result = "A";
		}
		else if(chu.equals("生产管理处"))
		{
			result = "B";
		}
		else if(chu.equals("研发支持一处"))
		{
			result = "C";
		}
		else if(chu.equals("通用业务一处"))
		{
			result = "D";
		}
		else if(chu.equals("专业处理一处"))
		{
			result = "E";
		}
		else if(chu.equals("合规与监督一处"))
		{
			result = "F";
		}
		else if(chu.equals("远程支持处"))
		{
			result = "G";
		}
		else if(chu.equals("物业服务处"))
		{
			result = "U";
		}
		else if(chu.equals("安保及基础设施运维处"))
		{
			result = "V";
		}
		
		return result;
	}
	
	/**
	 * position转职务
	 * @param position
	 * @return
	 */
	public static String positionToZhi(String position)
	{
		String result="";
		char zhi = ' ';
		if(position.length()!=1&&position.length()!=positionlength)
		{
			result = " ";
		}
		else
		{
			zhi = position.charAt(0);
			if(zhi=='0')
			{
				result = "主任";
			}
			else if(zhi=='1')
			{
				result = "处室负责人";
			}
			else if(zhi=='2')
			{
				result = "团队负责人";
			}else if(zhi=='4')
			{
				result = "班组长";
			}else if(zhi=='3')
			{
				result = "普通员工";
			}
		}
		return result;
	}
	/**
	 * position转主分中心
	 * @param position
	 * @return
	 */
	public static String positionToZX(String position)
	{
		String result="";
		char zx = ' ';
		if(position.length()!=1&&position.length()!=positionlength)
		{
			result = " ";
		}
		else
		{
			if(position.length()==1)
			{
				zx = position.charAt(0);
			}
			else
			{
				zx = position.charAt(1);
			}
			if(zx=='1')
			{
				result = "业务处理中心";
			}
			else if(zx=='2')
			{
				result = "成都分中心";
			}
			else if(zx=='3')
			{
				result = "武汉生产园区管理办公室";
			}
		}
		return result;
	}
	/**
	 * position转团队
	 * @param position
	 * @return
	 */
	public static String positionToTuan(String position)
	{
		String result="";
		String chutuan = "";
		
		if(position.length()!=2&&position.length()!=positionlength)
		{
			result = " ";
		}
		else
		{
			if(position.length()==positionlength)
			{
				chutuan = position.substring(2,4);
				
			}
			else
			{
				chutuan = position.substring(0,2);
			}
			
			if(chutuan.equals("A0"))//综合管理处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("A1"))
			{
				result = "综合管理团队";
			}
			else if(chutuan.equals("A2"))
			{
				result = "财务（采购）管理团队";
			}
			else if(chutuan.equals("A3"))
			{
				result = "人力资源（党群）团队";
			}
			else if(chutuan.equals("B0"))//生产管理处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("B1"))
			{
				result = "运营规划与外包管理团队";
			}
			else if(chutuan.equals("B2"))
			{
				result = "质量控制与合规管理团队";
			}
			else if(chutuan.equals("B3"))
			{
				result = "监控调度与参数运维团队";
			}
			else if(chutuan.equals("C0"))//研发支持一处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("C1"))
			{
				result = "需求分析团队";
			}
			else if(chutuan.equals("C2"))
			{
				result = "推广响应团队";
			}
			else if(chutuan.equals("D0"))//通用业务一处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("D1"))
			{
				result = "本币支付结算团队";
			}
			else if(chutuan.equals("D2"))
			{
				result = "代理签约团队";
			}
			else if(chutuan.equals("E0"))//专业处理一处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("E1"))
			{
				result = "柜面外汇团队";
			}
			else if(chutuan.equals("E2"))
			{
				result = "外汇汇款清算团队";
			}
			else if(chutuan.equals("F0"))//远程支持处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("F1"))
			{
				result = "稽核监督团队";
			}
			else if(chutuan.equals("F2"))
			{
				result = "集中授权 团队";
			}
			else if(chutuan.equals("G0"))//远程支持处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("G1"))
			{
				result = "智慧银行业务团队";
			}
			else if(chutuan.equals("G2"))
			{
				result = "集团子公司业务团队";
			}
			else if(chutuan.equals("10"))//综合与生产管理处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("11"))//综合与生产管理处
			{
				result = "综合与财务管理团队";
			}
			else if(chutuan.equals("12"))//综合与生产管理处
			{
				result = "人力资源团队";
			}
			else if(chutuan.equals("13"))//综合与生产管理处
			{
				result = "生产管理团队";
			}
			else if(chutuan.equals("20"))//合规与监督二处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("21"))//合规与监督二处
			{
				result = "集中稽核团队";
			}
			else if(chutuan.equals("30"))//通用业务二处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("31"))//通用业务二处
			{
				result = "业务处理团队";
			}
			else if(chutuan.equals("60"))//专业处理二处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("61"))//专业处理二处
			{
				result = "专业处理团队";
			}
			else if(chutuan.equals("50"))//研发支持二处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("51"))//
			{
				result = "研发支持团队1";
			}
			else if(chutuan.equals("52"))//
			{
				result = "研发支持团队2";
			}
			else if(chutuan.equals("U0"))//物业服务处
			{
				result = "处室管理";
			}
			else if(chutuan.equals("U1"))//
			{
				result = "综合保障团队";
			}
			else if(chutuan.equals("U2"))//
			{
				result = "物业服务团队";
			}
			else if(chutuan.equals("V0"))//
			{
				result = "处室管理";
			}
			else if(chutuan.equals("V1"))//
			{
				result = "安保团队";
			}
			else if(chutuan.equals("V2"))//
			{
				result = "基础设施运维团队";
			}
			else if(chutuan.equals("V3"))//
			{
				result = "商务及合规团队";
			}
			else if(chutuan.equals("V4"))//
			{
				result = "基建团队";
			}
			else
			{
				result = "无";
			}
		}
		
		return result;
	}
	
	public static String chutuanTotuan(String chu,String tuan)
	{
		String position = chu+tuan;
		return positionToTuan(position);
	}
	public static String positionToZu(String position)
	{
		String result="";
		char chu = ' ';
		char zu = ' ';
		if(position.length()!=1&&position.length()!=positionlength)
		{
			result = " ";
		}
		else
		{
			if(position.length()==positionlength)
			{
				chu = position.charAt(2);
				zu = position.charAt(4);
			}
			if(zu=='A')
			{
				result = "综合维修组";
			}
			else if(zu=='B')
			{
				result = "常规物业组";
			}
			else if(zu=='C')
			{
				result = "安全管理";
			}
			else if(zu=='D')
			{
				result = "消安防管理";
			}
			else if(zu=='E')
			{
				result = "能源动力";
			}
			else if(zu=='F')
			{
				result = "空调暖通";
			}
			else if(zu=='G')
			{
				result = "弱电监控";
			}
			else if(zu=='H')
			{
				result = "运行管理";
			}
			else if(zu>'0'&&zu<='9')
			{
				if(chu=='5')
				{
					if(zu=='1')
					{
						result = "智慧柜员机组";
					}
					else if(zu=='2')
					{
						result = "龙易行组";
					}
					else if(zu=='3')
					{
						result = "柜面组";
					}
					else if(zu=='4')
					{
						result = "智慧银行组";
					}
				}
				else
				{
					result = "班组"+zu;
				}
				
			}
			else
			{
				result ="无";
			}
		}
		
		return result;
	}
	
	/**
	 * 岗位编码转换成名称(单独用,自己声明session)
	 * @param pnum
	 * @return
	 */
	public static String pnumToName(Integer pnum)
	{
		String result="";
		PPositionDAO ppdao = new PPositionDAO();
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			PPosition pp = ppdao.findByID(pnum);
			if(pp==null)
			{
				return "";
			}
			else
			{
				return pp.getName();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			trans.commit();
			session.flush();
			session.clear();
			session.close();
		}
		return result;
	}
	/**
	 * 岗位编码转换成名称(需要传session)
	 * @param session
	 * @param pnum
	 * @return
	 */
	public static String pnumToName(Session session,Integer pnum)
	{
		PPositionDAO ppdao = new PPositionDAO();
		PPosition pp = ppdao.findByID(pnum);
		if(pp==null)
		{
			return "";
		}
		else
		{
			return pp.getName();
		}	
	}
	public static String newnumberToName(Session session,String newnumber)
	{
		PUserDAO pudao = new PUserDAO();
		PUser pu = pudao.findByNewNumber(newnumber);
		if(pu==null)
		{
			return "";
		}
		else
		{
			return pu.getName();
		}	
	}
	
	public static String authoToName(String autho)
	{
		
		if(autho.contains("R"))
		{
			return "绩效管理员";
		}
		else if(autho.contains("S"))		
		{
			return "数据管理员";
		}
		else
		{
			return "普通用户";
		}
	}
	
}
//////
