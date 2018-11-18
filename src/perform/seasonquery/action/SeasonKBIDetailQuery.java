package perform.seasonquery.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class SeasonKBIDetailQuery {
	private String rater;//打分人
	private String ratepeople;//被打分人
	private String ratepeoplename;//被打分人姓名
	private int year;
	private int season;
	private int ifrate;//如果rater是ratepeople的打分人，该值为1
	private List<PKBIScore> list;
	private String rater1;
	private String rater2;
	private String rater3;
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
	public int getIfrate() {
		return ifrate;
	}
	public void setIfrate(int ifrate) {
		this.ifrate = ifrate;
	}
	public List<PKBIScore> getList() {
		return list;
	}
	public void setList(List<PKBIScore> list) {
		this.list = list;
	}
	public String getRater1() {
		return rater1;
	}
	public void setRater1(String rater1) {
		this.rater1 = rater1;
	}
	public String getRater2() {
		return rater2;
	}
	public void setRater2(String rater2) {
		this.rater2 = rater2;
	}
	public String getRater3() {
		return rater3;
	}
	public void setRater3(String rater3) {
		this.rater3 = rater3;
	}
	public String execute() throws Exception
	{
		ifrate = 0;
		PUserDAO pudao = new PUserDAO();
		PKBIScoreDAO pdao = new PKBIScoreDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		list = pdao.findByYearSeasonNewnumber(year, season, ratepeople);
    		PUser pu = pudao.findByNewNumber(ratepeople);
    		PUser pur = pudao.findByNewNumber(rater);
    		if(pu!=null)
    		{
    			ratepeoplename = pu.getName();
    			if(pur!=null)
    			{
    				String p = pu.getPosition();
    				String pr = pur.getPosition();
    				char pzhi = p.charAt(0);
    				char pchu = p.charAt(2);
    				char przhi = pr.charAt(0);
    				char prchu = pr.charAt(2);
    				if(prchu==pchu)//同一个处室
    				{
    					if((przhi=='2'||przhi=='3')&&pzhi>'3')//处室负责人和团队负责人及以下
    					{
    						ifrate = 1;
    					}
    				}
    			}
    			rater1 = UserUtil.newnumberToName(session, list.get(0).getRater1());
				rater2 = UserUtil.newnumberToName(session, list.get(0).getRater2());
				rater3 = UserUtil.newnumberToName(session, list.get(0).getRater3());
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
