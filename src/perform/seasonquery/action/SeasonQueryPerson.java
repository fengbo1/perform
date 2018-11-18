package perform.seasonquery.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;
import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;

public class SeasonQueryPerson {

	private String querynewnumber;
	private PScore ps;
	private int kpirank;
	private int ktirank;
	private int kbirank;
	private int kcirank;
	private int kpiallrank;
	private int ktiallrank;
	private int kbiallrank;
	private int kciallrank;
	private int scorerank;
	private int scoreallrank;
	private List<Integer> listyear;
	private int year;
	private int season;
	
	public int getScoreallrank() {
		return scoreallrank;
	}
	public void setScoreallrank(int scoreallrank) {
		this.scoreallrank = scoreallrank;
	}
	public int getKpiallrank() {
		return kpiallrank;
	}
	public void setKpiallrank(int kpiallrank) {
		this.kpiallrank = kpiallrank;
	}
	public int getKtiallrank() {
		return ktiallrank;
	}
	public void setKtiallrank(int ktiallrank) {
		this.ktiallrank = ktiallrank;
	}
	public int getKbiallrank() {
		return kbiallrank;
	}
	public void setKbiallrank(int kbiallrank) {
		this.kbiallrank = kbiallrank;
	}
	public int getKciallrank() {
		return kciallrank;
	}
	public void setKciallrank(int kciallrank) {
		this.kciallrank = kciallrank;
	}
	public String getQuerynewnumber() {
		return querynewnumber;
	}
	public void setQuerynewnumber(String querynewnumber) {
		this.querynewnumber = querynewnumber;
	}
	public PScore getPs() {
		return ps;
	}
	public void setPs(PScore ps) {
		this.ps = ps;
	}
	public int getKpirank() {
		return kpirank;
	}
	public void setKpirank(int kpirank) {
		this.kpirank = kpirank;
	}
	public int getKtirank() {
		return ktirank;
	}
	public void setKtirank(int ktirank) {
		this.ktirank = ktirank;
	}
	public int getKbirank() {
		return kbirank;
	}
	public void setKbirank(int kbirank) {
		this.kbirank = kbirank;
	}
	public int getKcirank() {
		return kcirank;
	}
	public void setKcirank(int kcirank) {
		this.kcirank = kcirank;
	}
	public int getScorerank() {
		return scorerank;
	}
	public void setScorerank(int scorerank) {
		this.scorerank = scorerank;
	}
	public List<Integer> getListyear() {
		return listyear;
	}
	public void setListyear(List<Integer> listyear) {
		this.listyear = listyear;
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
	
	public String execute() throws Exception
	{
		String result = "success";
		String sql = "";
		String pname="";//岗位名称
		String chu = "";
		UserUtil uu = new UserUtil();
		PUserDAO pudao = new PUserDAO();
		PScoreDAO psdao = new PScoreDAO();
		PFlagDAO pfdao = new PFlagDAO();
		DateTimeUtil dtu = new DateTimeUtil();
		listyear = dtu.getLast10Years();
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			if(year==0||season==0)//未选年和季度
			{
				PFlag pf = pfdao.findByIsNew(1);
				year = pf.getYear();
				season = pf.getSeason();
			}
			ps = psdao.findByNewnumberYearSeason(querynewnumber, year, season);
			chu = ps.getPositionchu();
			pname = ps.getPositionname();
			
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.kpiscore>='"+ps.getKpiscore()+"' and a.positionname='"+pname+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kpirank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.positionname='"+pname+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kpiallrank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.ktiscore>='"+ps.getKtiscore()+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				ktirank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				ktiallrank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.kbiscore>='"+ps.getKbiscore()+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kbirank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"'  and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kbiallrank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.kciscore>='"+ps.getKciscore()+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kcirank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"'  and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				kciallrank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.score>='"+ps.getScore()+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				scorerank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
			}
			sql = "select count(*) from p_score as a where a.year='"+year+"' and a.season='"+season+"' and a.positionchu='"+chu+"'";
			if(session.createSQLQuery(sql).uniqueResult()!=null)
			{
				scoreallrank = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
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
