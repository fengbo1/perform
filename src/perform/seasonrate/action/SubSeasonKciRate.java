package perform.seasonrate.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKCIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKCIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.seasonrate.pojo.PScore;
import ccb.hibernate.HibernateSessionFactory;

public class SubSeasonKciRate {

	private int year;
	private int season;
	private String ratepeople;
	private String rater;
	private double[] score;
	//private String[] remark;
	private List<PKCIScore> list;
	private PScore ps;
	private String type;
	private String chu;
	private String tuan;
	private String zu;
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
	public String getRatepeople() {
		return ratepeople;
	}
	public void setRatepeople(String ratepeople) {
		this.ratepeople = ratepeople;
	}
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public double[] getScore() {
		return score;
	}
	public void setScore(double[] score) {
		this.score = score;
	}
/*	public String[] getRemark() {
		return remark;
	}
	public void setRemark(String[] remark) {
		this.remark = remark;
	}*/
	public List<PKCIScore> getList() {
		return list;
	}
	public void setList(List<PKCIScore> list) {
		this.list = list;
	}
	public PScore getPs() {
		return ps;
	}
	public void setPs(PScore ps) {
		this.ps = ps;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String execute() throws Exception
	{
		type = "rate";
		PKCIScoreDAO pdao = new PKCIScoreDAO();
		PScoreDAO psdao = new PScoreDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		List<PKCIScore> listtemp = pdao.findByYearSeasonNewnumber(year, season, ratepeople);
    		for(int i=0;i<listtemp.size();i++)
    		{
    			PKCIScore pstemp = listtemp.get(i);
    			if(pstemp.getRater1()!=null&&pstemp.getRater1().equals(rater))
    			{
    				pstemp.setResult1(score[i]);
    				//pstemp.setRemark1(remark[i]);
    			}
    			else if(pstemp.getRater2()!=null&&pstemp.getRater2().equals(rater))
    			{
    				pstemp.setResult2(score[i]);
    				//pstemp.setRemark2(remark[i]);
    			}
    			else if(pstemp.getRater3()!=null&&pstemp.getRater3().equals(rater))
    			{
    				pstemp.setResult3(score[i]);
    				//pstemp.setRemark3(remark[i]);
    			}
    			pdao.merge(pstemp);
    			list = pdao.findByYearSeasonNewnumber(year, season, ratepeople);
        		ps = psdao.findByNewnumberYearSeason(ratepeople, year, season);
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
