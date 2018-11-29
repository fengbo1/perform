package perform.seasonrate.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.norm.dao.PKbinormDAO;
import perform.norm.dao.PKcinormDAO;
import perform.norm.dao.PKpinormDAO;
import perform.norm.dao.PKtinormDAO;
import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKcinorm;
import perform.norm.pojo.PKpinorm;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKCIScoreDAO;
import perform.seasonrate.dao.PKPIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKCIScore;
import perform.seasonrate.pojo.PKPIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;

import ccb.hibernate.HibernateSessionFactory;

public class RateBegin {
	
	private int year;
	private int season;
	private String message;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String execute() throws Exception
	{
		String result = "success";
		String sql = "";
		PFlagDAO pfdao = new PFlagDAO();
		List<PFlag> list1;
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		list1 = pfdao.findByStatus("1");
    		if(!list1.isEmpty())
    		{
    			PFlag pf = list1.get(0);
    			message = "失败！当前"+pf.getYear()+"年"+pf.getSeason()+"季度正在评分，请先结束当前季度评分才能开启新的评分！";
    		}
    		else
    		{
    			if(year==0||season==0)
        		{
        			message = "失败！请选择正确的年和季度！";
        		}
        		else
        		{
        			PFlag pf = pfdao.findByYearSeason(year, season);
        			if(pf==null)//开始新一季度打分 
        			{
        				//添加初始化的方法
        				initAllRate(session,year,season);
        				sql = "update p_flag set isnew=0";//所有最新标志位置0；
        				session.createSQLQuery(sql).executeUpdate();
        				pf = new PFlag();
        				pf.setYear(year);
        				pf.setSeason(season);
        				pf.setIsnew(1);
        				pf.setFlag(1);
        				pf.setAlreadyrate("");//已评分人
        				pfdao.merge(pf);
        			}
        			else
        			{
        				if(pf.getFlag()==2)//公示中，修改得分，2->1
        				{
        					sql = "update p_flag set isnew=0";//所有最新标志位置0；
            				session.createSQLQuery(sql).executeUpdate();
        					pf.setFlag(1);
        					pf.setIsnew(1);
        					pf.setAlreadyrate("");//已评分人
        					pfdao.merge(pf);
        				}
        				if(pf.getFlag()==3)//打分完毕，删除原有记录，重新开始打分
        				{
        					//删除并新增
        					delAllRate(session,year,season);
        					initAllRate(session,year,season);
        					sql = "update p_flag set isnew=0";//所有最新标志位置0；
            				session.createSQLQuery(sql).executeUpdate();
            				pf = new PFlag();
            				pf.setYear(year);
            				pf.setSeason(season);
            				pf.setIsnew(1);
            				pf.setFlag(1);
            				pf.setAlreadyrate("");//已评分人
            				pfdao.merge(pf);
        				}
        			}
        			message = "成功！已开启"+pf.getYear()+"年"+pf.getSeason()+"季度打分！";
        		}
    		}
    		
    		
    		
    	}catch (Exception e) {
			trans.rollback();//出错回滚
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
	 * 删除所有打分数据
	 * @param year
	 * @param season
	 * @return
	 */
	public String delAllRate(Session session,int year,int season)
	{
		String sql = "";
		sql = "delete from p_score where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		sql = "delete from p_kpiscore where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		sql = "delete from p_ktiscore where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		sql = "delete from p_kbiscore where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		sql = "delete from p_kciscore where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		sql = "delete from p_flag where year='"+year+"' and season='"+season+"'";
		session.createSQLQuery(sql).executeUpdate();
		return "success";
	}
	
	/**
	 * 初始化所有打分数据
	 * @param session
	 * @param year
	 * @param season
	 * @return
	 */
	public String initAllRate(Session session,int year,int season)
	{
		PUserDAO pudao = new PUserDAO();
		PScoreDAO psdao = new PScoreDAO();
		PKpinormDAO pkpindao = new PKpinormDAO();
		PKtinormDAO pktindao = new PKtinormDAO();
		PKbinormDAO pkbindao = new PKbinormDAO();
		PKcinormDAO pkcindao = new PKcinormDAO();
		PKPIScoreDAO pkpisdao = new PKPIScoreDAO();
		PKTIScoreDAO pktisdao = new PKTIScoreDAO();
		PKBIScoreDAO pkbisdao = new PKBIScoreDAO();
		PKCIScoreDAO pkcisdao = new PKCIScoreDAO();
		PPositionDAO ppdao = new PPositionDAO();
		UserUtil uu = new UserUtil();
		List<PUser> list = pudao.findAllKaoheByPara("test");
		
		for(int i=0;i<list.size();i++)
		{
			PUser pu = list.get(i);
			PPosition pp = ppdao.findByID(pu.getPnum());
			List<PUser> listrater = pudao.findRaterByPostion(pu.getPosition());
			//初始化PScore
			PScore ps = new PScore();
			ps.setYear(year);
			ps.setSeason(season);
			ps.setNewnumber(pu.getNewnumber());
			System.out.println(pu.getName());
			ps.setName(pu.getName());
			ps.setPosition(pu.getPosition());
			ps.setPositionname(uu.pnumToName(session, pu.getPnum()));
			ps.setPositionchu(uu.positionToChu(pu.getPosition()));
			ps.setPositiontuan(uu.positionToTuan(pu.getPosition()));
			ps.setPositionzu(uu.positionToZu(pu.getPosition()));
			ps.setKpiscore(0.0);
			ps.setKtiscore(0.0);
			ps.setKbiscore(0.0);
			ps.setKciscore(0.0);
			ps.setKpiprop(pp.getKpiprop());
			ps.setKtiprop(pp.getKtiprop());
			ps.setKbiprop(pp.getKbiprop());
			ps.setKciprop(pp.getKciprop());
			ps.setKpirater("");
			ps.setKtirater(pp.getKtirater());
			ps.setKbirater(listrater.get(0).getNewnumber());
			ps.setKcirater(listrater.get(0).getNewnumber());
			ps.setScore(0.0);
			//初始化kpiscore
			String[] kpinorms = pp.getKpinorm().split("、");//kpi指标
//			String[] kpinormprops = pp.getKpinormprop().split("、");//kpi指标比重
			for(int j=0;j<kpinorms.length;j++)
			{
				int kpin = Integer.valueOf(kpinorms[j]);//指标编号
//				double kpip = Double.valueOf(kpinormprops[j]);//指标权重
				PKpinorm pkpinorm = pkpindao.findAllById(kpin); 
				PKPIScore pkpis = new PKPIScore();
				pkpis.setYear(year);
				pkpis.setSeason(season);
				pkpis.setNewnumber(pu.getNewnumber());
				pkpis.setName(pu.getName());
				pkpis.setKpiname(pkpinorm.getName());
				//pkpis.setKpipdpname(pkpinorm.getPdpname());
				pkpis.setKpinumber(kpin);
				pkpis.setTarget(pkpinorm.getTarget());
				if(ps.getKpiprop()==1)
				{
					pkpis.setScore(100.0);
				}
				else if(ps.getKpiprop()==-1)
				{
					pkpis.setScore(0.0);
				}
				else
				{
					pkpis.setScore(100.0);
				}
				pkpis.setRule(pkpinorm.getRule());
				pkpis.setSum(0.0);
				pkpis.setKpipdpname(pkpinorm.getRemark());
				pkpisdao.merge(pkpis);
			}
			//初始化ktiscore
			String[] ktinorms = pp.getKtinorm().split("、");//kti指标
			String[] ktinormprops = pp.getKtinormprop().split("、");//kti指标比重
			String[] ktirater = pp.getKtirater().split("\\|");
			for(int j=0;j<ktinorms.length;j++)
			{
				int ktin = Integer.valueOf(ktinorms[j]);//指标编号
				double ktip = Double.valueOf(ktinormprops[j]);//指标权重
				PKtinorm pktinorm = pktindao.findAllById(ktin); 
				PKTIScore pktis = new PKTIScore();
				pktis.setYear(year);
				pktis.setSeason(season);
				pktis.setNewnumber(pu.getNewnumber());
				pktis.setName(pu.getName());
				pktis.setKtiname(pktinorm.getName());
				pktis.setKtinumber(ktin);
				pktis.setTarget(pktinorm.getTarget());
				pktis.setScore(pktinorm.getScore()*ktip);
				pktis.setRule(pktinorm.getRule());
				pktis.setSum(0.0);
				pktis.setRater1(ktirater[j]);
				pktis.setResult1(0.0);
//				if(!listrater.isEmpty())
//				{
//					pktis.setRater1(listrater.get(0).getNewnumber());
//					pktis.setResult1(0.0);
//					if(listrater.size()>1)
//					{
//						pktis.setRater2(listrater.get(1).getNewnumber());
//						pktis.setResult2(0.0);
//						if(listrater.size()>2)
//						{
//							pktis.setRater3(listrater.get(2).getNewnumber());
//							pktis.setResult3(0.0);
//						}
//					}
//				}
				pktisdao.merge(pktis);
			}
			//初始化kbiscore
			String[] kbinorms = pp.getKbinorm().split("、");//kbi指标
			String[] kbinormprops = pp.getKbinormprop().split("、");//kbi指标比重
			System.out.println(pp.getKbinorm());
			System.out.println(pp.getKbinormprop());
			System.out.println(pu.getName());
			System.out.println(pu.getPnum());
			for(int j=0;j<kbinorms.length;j++)
			{
				int kbin = Integer.valueOf(kbinorms[j]);//指标编号
				double kbip = Double.valueOf(kbinormprops[j]);//指标权重
				PKbinorm pkbinorm = pkbindao.findAllById(kbin); 
				PKBIScore pkbis = new PKBIScore();
				pkbis.setYear(year);
				pkbis.setSeason(season);
				pkbis.setNewnumber(pu.getNewnumber());
				pkbis.setName(pu.getName());
				pkbis.setKbiname(pkbinorm.getName());
				pkbis.setKbinumber(kbin);
				pkbis.setTarget(pkbinorm.getTarget());
				pkbis.setScore(pkbinorm.getScore()*kbip);
				pkbis.setRule(pkbinorm.getRule());
				pkbis.setSum(0.0);
				pkbis.setRater1(listrater.get(0).getNewnumber());
				pkbis.setResult1(0.0);
//				if(!listrater.isEmpty())
//				{
//					pkbis.setRater1(listrater.get(0).getNewnumber());
//					pkbis.setResult1(0.0);
//					if(listrater.size()>1)
//					{
//						pkbis.setRater2(listrater.get(1).getNewnumber());
//						pkbis.setResult2(0.0);
//						if(listrater.size()>2)
//						{
//							pkbis.setRater3(listrater.get(2).getNewnumber());
//							pkbis.setResult3(0.0);
//						}
//					}
//				}
				pkbisdao.merge(pkbis);
			}
			//初始化kciscore
			String[] kcinorms = pp.getKcinorm().split("、");//kci指标
			String[] kcinormprops = pp.getKcinormprop().split("、");//kci指标比重
			for(int j=0;j<kcinorms.length;j++)
			{
				int kcin = Integer.valueOf(kcinorms[j]);//指标编号
				double kcip = Double.valueOf(kcinormprops[j]);//指标权重
				PKcinorm pkcinorm = pkcindao.findAllById(kcin); 
				PKCIScore pkcis = new PKCIScore();
				pkcis.setYear(year);
				pkcis.setSeason(season);
				pkcis.setNewnumber(pu.getNewnumber());
				pkcis.setName(pu.getName());
				pkcis.setKciname(pkcinorm.getName());
				pkcis.setKcinumber(kcin);
				pkcis.setTarget(pkcinorm.getTarget());
				if(ps.getKciprop()==1)
				{
					pkcis.setScore(100.0);
				}
				else if(ps.getKpiprop()==-1)
				{
					pkcis.setScore(0.0);
				}
				else
				{
					pkcis.setScore(100.0);
				}
				pkcis.setRule(pkcinorm.getRule());
				pkcis.setProp(kcip);
				pkcis.setSum(0.0);
				pkcis.setRater1(listrater.get(0).getNewnumber());
				pkcis.setResult1(0.0);
//				if(!listrater.isEmpty())
//				{
//					pkcis.setRater1(listrater.get(0).getNewnumber());
//					pkcis.setResult1(0.0);
//					if(listrater.size()>1)
//					{
//						pkcis.setRater2(listrater.get(1).getNewnumber());
//						pkcis.setResult2(0.0);
//						if(listrater.size()>2)
//						{
//							pkcis.setRater3(listrater.get(2).getNewnumber());
//							pkcis.setResult3(0.0);
//						}
//					}
//				}
				pkcisdao.merge(pkcis);
			}
			psdao.merge(ps);
		}
		return "success";
	}
}
