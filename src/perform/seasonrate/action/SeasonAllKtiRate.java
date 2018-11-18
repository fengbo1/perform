package perform.seasonrate.action;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import ccb.hibernate.HibernateSessionFactory;
public class SeasonAllKtiRate {
	private String rater;//打分人
	private int year;
	private int season;
	private List<PKTIScore> list;
	private List<PUser> listpu;
    private List<List<PKTIScore>> listkti ;
    private PUser pu;
    	
	public List<PUser> getListpu() {
		return listpu;
	}
	public void setListpu(List<PUser> listpu) {
		this.listpu = listpu;
	}
	public PUser getPu() {
		return pu;
	}
	public void setPu(PUser pu) {
		this.pu = pu;
	}
	public List<List<PKTIScore>> getListkti() {
		return listkti;
	}
	public void setListkti(List<List<PKTIScore>> listkti) {
		this.listkti = listkti;
	}
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
	public List<PKTIScore> getList() {
		return list;
	}
	public void setList(List<PKTIScore> list) {
		this.list = list;
	}

	public String execute() throws Exception
	{
		
		PKTIScoreDAO pdao = new PKTIScoreDAO();
		listkti = new ArrayList<List<PKTIScore>>();
		PUserDAO pudao = new PUserDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		pu = pudao.findByNewNumber(rater);
    		listpu = pudao.findByChu(pu.getPosition().substring(2, 3));
    		for(int i=0;i<listpu.size();i++)
    		{
    			list = pdao.findByYearSeasonNewnumber(year, season, listpu.get(i).getNewnumber());
    			listkti.add(list);
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
