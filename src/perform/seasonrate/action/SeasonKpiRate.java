package perform.seasonrate.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.seasonrate.dao.PKPIScoreDAO;
import perform.seasonrate.pojo.PKPIScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

import ccb.hibernate.HibernateSessionFactory;

public class SeasonKpiRate {

	private String rater;//打分人
	private String ratepeople;//被打分人
	private String ratepeoplename;
	private String ratepeopleposition;
	private PKPIScore ps;
	private PUser pu;
	private int year;
	private int season;
	private double score;
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
	public String getRatepeopleposition() {
		return ratepeopleposition;
	}
	public void setRatepeopleposition(String ratepeopleposition) {
		this.ratepeopleposition = ratepeopleposition;
	}
	public PUser getPu() {
		return pu;
	}
	public void setPu(PUser pu) {
		this.pu = pu;
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
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public PKPIScore getPs() {
		return ps;
	}
	public void setPs(PKPIScore ps) {
		this.ps = ps;
	}
	public String execute() throws Exception
	{
		PUserDAO pudao = new PUserDAO();
		PKPIScoreDAO psdao = new PKPIScoreDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		pu = pudao.findByNewNumber(ratepeople);
    		ps = psdao.findByNewNumbernew(ratepeople,year,season);
    		if(pu!=null)
    		{
    			ratepeoplename = pu.getName();
    			ratepeopleposition = pu.getPosition();
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
		return "success";
	}
}
