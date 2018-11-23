package perform.seasonrate.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;

import ccb.hibernate.HibernateSessionFactory;

public class SubAllRate {

	private String rater;
	private int year;
	private int season;
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
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
		PFlagDAO pfdao = new PFlagDAO();
		String result = "success";
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		PFlag pf = pfdao.findByYearSeason(year, season);
    		if(pf.getAlreadyrate()==null)
    		{
    			pf.setAlreadyrate(rater);
    		}
    		else
    		{
    			pf.setAlreadyrate(pf.getAlreadyrate()+"、"+rater);
    		}
    		pfdao.merge(pf);
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
}
