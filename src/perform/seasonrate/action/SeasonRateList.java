package perform.seasonrate.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Collections;
import ccb.hibernate.HibernateSessionFactory;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.bean.SeasonScoreBean;
import perform.seasonrate.bean.SeasonScoreBean2;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;

public class SeasonRateList {

	private int year;
	private int season;
	private int sorttype;
	private int zhuan;
	private String chu;
	private String tuan;
	private String zu;
	private String name;
	private String rater;
	private List<PScore> list;
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
	public String getZu() {
		return zu;
	}
	public void setZu(String zu) {
		this.zu = zu;
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
	public List<Integer> getListyear() {
		return listyear;
	}
	public void setListyear(List<Integer> listyear) {
		this.listyear = listyear;
	}
	public List<PScore> getList() {
		return list;
	}
	public void setList(List<PScore> list) {
		this.list = list;
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
		UserUtil uu = new UserUtil();
		DateTimeUtil dtu = new DateTimeUtil();
		PFlagDAO pfdao = new PFlagDAO();
		PUserDAO pudao = new PUserDAO();
		listyear = dtu.getLast10Years();
		list2 = new ArrayList<SeasonScoreBean2>();
		if(tuan!=null&&zhuan==1)
		{
			tuan= new String(tuan.getBytes("ISO8859-1"),"UTF-8");
		}
		if(zu!=null&&zhuan==1)
		{
			zu= new String(zu.getBytes("ISO8859-1"),"UTF-8");
		}
		if(name!=null&&zhuan==1)
		{
			name = new String(name.getBytes("ISO8859-1"),"UTF-8");
		}
		if(tuan==null)
		{
			tuan="wu";
		}
		if(zu==null)
		{
			zu="wu";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			PUser purater = pudao.findByNewNumber(rater);
			if(purater!=null)
			{
				chu = uu.positionToChu(purater.getPosition());
			}
			if(year==0||season==0)//未选年和季度
			{
				PFlag pf = pfdao.findByIsNew(1);
				year = pf.getYear();
				season = pf.getSeason();
			}
			sql = "select * from p_score as p where p.year='"+year+"' and p.season='"+season+"' and p.positionchu='"+chu+"'";
			if(tuan!=null&&!tuan.equals("wu"))
			{
				sql +=" and p.positiontuan='"+tuan+"'";
			}
			if(zu!=null&&!zu.equals("wu"))
			{
				sql +=" and p.positionzu='"+zu+"'";
			}
			if(name!=null&&!name.equals(""))
			{
				sql +=" and p.name='"+name+"'";
			}
			list = session.createSQLQuery(sql).addEntity(PScore.class).list();
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
				sql = "select sum(if(rater1='"+rater+"',result1,0)+if(rater2='"+rater+"',result2,0)+if(rater3='"+rater+"',result3,0)) from p_ktiscore where newnumber='"+ps.getNewnumber()+"' and year='"+year+"' and season='"+season+"'";
				ssb2.setKti(session.createSQLQuery(sql).uniqueResult().toString());
				sql = "select sum(if(rater1='"+rater+"',result1,0)+if(rater2='"+rater+"',result2,0)+if(rater3='"+rater+"',result3,0)) from p_kbiscore where newnumber='"+ps.getNewnumber()+"' and year='"+year+"' and season='"+season+"'";
				ssb2.setKbi(session.createSQLQuery(sql).uniqueResult().toString());
				sql = "select sum(if(rater1='"+rater+"',result1,0)+if(rater2='"+rater+"',result2,0)+if(rater3='"+rater+"',result3,0)) from p_kciscore where newnumber='"+ps.getNewnumber()+"' and year='"+year+"' and season='"+season+"'";
				ssb2.setKci(session.createSQLQuery(sql).uniqueResult().toString());
				score = Double.valueOf(ssb2.getKpi())*ps.getKpiprop()+Double.valueOf(ssb2.getKti())*ps.getKtiprop()+Double.valueOf(ssb2.getKbi())*ps.getKbiprop()+Double.valueOf(ssb2.getKci())*ps.getKciprop();
				ssb2.setScore(String.valueOf(score));
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