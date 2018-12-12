package perform.hisquery.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import ccb.hibernate.HibernateSessionFactory;

public class HisQueryKti {
	private String rater;//打分人
	private String ratepeople;//被打分人
	private String ratepeoplename;//被打分人姓名
	private int year;
	private int season;
	private List<PKTIScore> list;
	private PScore ps;
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public String getRatepeople() {
		return ratepeople;
	}
	public void setRatepeople(String ratepeople) {
		this.ratepeople = ratepeople;
	}
	public String getRatepeoplename() {
		return ratepeoplename;
	}
	public void setRatepeoplename(String ratepeoplename) {
		this.ratepeoplename = ratepeoplename;
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
	public List<PKTIScore> getList() {
		return list;
	}
	public void setList(List<PKTIScore> list) {
		this.list = list;
	}
	public PScore getPs() {
		return ps;
	}
	public void setPs(PScore ps) {
		this.ps = ps;
	}
	public String execute() throws Exception
	{
		PScoreDAO psdao = new PScoreDAO();
		PKTIScoreDAO pdao = new PKTIScoreDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		list = pdao.findByYearSeasonNewnumberRater(year, season, ratepeople,rater);
    		ps = psdao.findByNewnumberYearSeason(ratepeople, year, season);
    	}catch (Exception e) {
			trans.rollback();//出错回滚
			e.printStackTrace();
		}finally{
			 trans.commit();
	         session.flush();
	         session.clear();
	         session.close();
		}
		return "success";
	}
}
