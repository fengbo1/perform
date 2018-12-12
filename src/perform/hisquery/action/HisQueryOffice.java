package perform.hisquery.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.action.DoubleKbiComparator;
import perform.seasonrate.action.DoubleKciComparator;
import perform.seasonrate.action.DoubleKpiComparator;
import perform.seasonrate.action.DoubleKtiComparator;
import perform.seasonrate.action.DoubleScoreComparator;
import perform.seasonrate.bean.SeasonScoreBean2;
import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class HisQueryOffice {
	private int year;
	private int season;
	private int sorttype;
	private int zhuan;
	private String name;
	private String rater;
	private String message;
	private List<SeasonScoreBean2> list2;
	private List<Integer> listyear;
	
	
	public int getZhuan() {
		return zhuan;
	}
	public void setZhuan(int zhuan) {
		this.zhuan = zhuan;
	}
	public int getSorttype() {
		return sorttype;
	}
	public void setSorttype(int sorttype) {
		this.sorttype = sorttype;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Integer> getListyear() {
		return listyear;
	}
	public void setListyear(List<Integer> listyear) {
		this.listyear = listyear;
	}
	public List<SeasonScoreBean2> getList2() {
		return list2;
	}
	public void setList2(List<SeasonScoreBean2> list2) {
		this.list2 = list2;
	}
	public String execute() throws Exception
	{
		String result = "success";
		String sql = "";
//		String chu = "";
		UserUtil uu = new UserUtil();
		DateTimeUtil dtu = new DateTimeUtil();
		PFlagDAO pfdao = new PFlagDAO();
		PUserDAO pudao = new PUserDAO();
		PKTIScoreDAO pktidao = new PKTIScoreDAO();
		PKBIScoreDAO pkbidao = new PKBIScoreDAO();
		listyear = dtu.getLast10Years();
		list2 = new ArrayList<SeasonScoreBean2>();
		if(name!=null&&zhuan==1)
		{
			name = new String(name.getBytes("ISO8859-1"),"UTF-8");
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			PUser purater = pudao.findByNewNumber(rater);
//			if(purater!=null)
//			{
//				chu = uu.positionToChu(purater.getPosition());
//			}
//			if(year==0||season==0)//未选年和季度
//			{
				PFlag pf = pfdao.findByIsNew(1);
				year = pf.getYear();
				season = pf.getSeason();
//			}
			sql = "select * from p_score as p where p.year='"+year+"' and p.season='"+season+"' and (p.kpirater like '%"+rater+"%' or p.ktirater like '%"+rater+"%' or p.kbirater like '%"+rater+"%' or p.kcirater like '%"+rater+"%')";
			if(name!=null&&!name.equals(""))
			{
				sql +=" and p.name='"+name+"'";
			}
			sql+=" order by locate(mid(position,1,1),'01243')";
			List<PScore> list = session.createSQLQuery(sql).addEntity(PScore.class).list();
			double score=0.0;
			for(int i=0;i<list.size();i++)
			{
				SeasonScoreBean2 ssb2 = new SeasonScoreBean2();
				PScore ps = list.get(i);
				ssb2.setNewnumber(ps.getNewnumber());
				ssb2.setName(ps.getName());
				ssb2.setChu(ps.getPositionchu());
				ssb2.setTuan(ps.getPositiontuan());
				ssb2.setZu(ps.getPositionzu());
				ssb2.setPosition(ps.getPositionname());
				ssb2.setKpi(String.valueOf(ps.getKpiscore()));
				List<PKTIScore> listkti = pktidao.findByYearSeasonRaterNotRate(year, season, rater,ps.getNewnumber());
				List<PKBIScore> listkbi = pkbidao.findByYearSeasonRaterNotRate(year, season, rater,ps.getNewnumber());
				ssb2.setKpirated("");
				if(listkti.isEmpty())
				{
					ssb2.setKtirated("yes");
				}
				else
				{
					ssb2.setKtirated("no");
				}
				if(listkbi.isEmpty())
				{
					ssb2.setKbirated("yes");
				}
				else
				{
					ssb2.setKbirated("no");
				}
				ssb2.setKcirated("");
				//是否本人打分标志
				if(ps.getKpirater()!=null&&ps.getKpirater().contains(rater))
				{
					ssb2.setKpirater(rater);
				}
				else
				{
					ssb2.setKpirater("");
				}
				if(ps.getKtirater()!=null&&ps.getKtirater().contains(rater))
				{
					ssb2.setKtirater(rater);
				}
				else
				{
					ssb2.setKtirater("");
				}
				if(ps.getKbirater()!=null&&ps.getKbirater().contains(rater))
				{
					ssb2.setKbirater(rater);
				}
				else
				{
					ssb2.setKbirater("");
				}
				if(ps.getKcirater()!=null&&ps.getKcirater().contains(rater))
				{
					ssb2.setKcirater(rater);
				}
				else
				{
					ssb2.setKcirater("");
				}
				list2.add(ssb2);
				
			}
			
			if(sorttype!=0&&sorttype==1)
			{
				DoubleKpiComparator dckpi = new DoubleKpiComparator();
				Collections.sort(list2, dckpi);
			}
			if(sorttype!=0&&sorttype==2)
			{
				DoubleKtiComparator dckti = new DoubleKtiComparator();
				Collections.sort(list2, dckti);
			}
			if(sorttype!=0&&sorttype==3)
			{
				DoubleKbiComparator dckbi = new DoubleKbiComparator();
				Collections.sort(list2, dckbi);
			}
			if(sorttype!=0&&sorttype==4)
			{
				DoubleKciComparator dckci = new DoubleKciComparator();
				Collections.sort(list2, dckci);
			}
			if(sorttype!=0&&sorttype==5)
			{
				DoubleScoreComparator dcscore = new DoubleScoreComparator();
				Collections.sort(list2, dcscore);
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
}
