package perform.seasonrate.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKTIScore;
public class SeasonNewRateList {
	private String rater;
	private String message;
	private List<PKTIScore> listkti;
	private List<PKBIScore> listkbi;
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
	public List<PKTIScore> getListkti() {
		return listkti;
	}
	public void setListkti(List<PKTIScore> listkti) {
		this.listkti = listkti;
	}
	public List<PKBIScore> getListkbi() {
		return listkbi;
	}
	public void setListkbi(List<PKBIScore> listkbi) {
		this.listkbi = listkbi;
	}
	public String execute() throws Exception
	{
		PFlagDAO pfdao = new PFlagDAO();
		PKTIScoreDAO pktidao = new PKTIScoreDAO();
		PKBIScoreDAO pkbidao = new PKBIScoreDAO();
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			PFlag pf = pfdao.findByIsNew(1);
			if(pf!=null)
			{
				int year = pf.getYear();
				int season = pf.getSeason();
				if(pf.getAlreadyrate()!=null&&pf.getAlreadyrate().contains(rater))
				{
					message = "您已经提交了所有评分，无法再次评分。";
					return "failed";
				}
				listkti = pktidao.findByYearSeasonRater(year, season,rater);
				listkbi = pkbidao.findByYearSeasonRater(year, season,rater);
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
		return "success";
	}
}
