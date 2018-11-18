package perform.seasonquery.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class SeasonQueryKPI {
	private int year;
	private int season;
	private String city;
	private String chu;
	private String tuan;
	private String zu;
	private String name;
	private String rater;
	private List<PScore> list;
	private List<Integer> listyear;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public List<PScore> getList() {
		return list;
	}
	public void setList(List<PScore> list) {
		this.list = list;
	}
	public List<Integer> getListyear() {
		return listyear;
	}
	public void setListyear(List<Integer> listyear) {
		this.listyear = listyear;
	}
	public String execute() throws Exception
	{
		String result = "success";
		String sql = "";
		UserUtil uu = new UserUtil();
		PFlagDAO pfdao = new PFlagDAO();
		PUserDAO pudao = new PUserDAO();
		DateTimeUtil dtu = new DateTimeUtil();
		listyear = dtu.getLast10Years();
		if(city==null)
		{
			city="wu";
		}
		if(chu==null)
		{
			chu="wu";
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
			if(year==0||season==0)//未选年和季度
			{
				PFlag pf = pfdao.findByIsNew(1);
				year = pf.getYear();
				season = pf.getSeason();
			}
			sql = "select * from p_score as p where p.year='"+year+"' and p.season='"+season+"'";
			if(chu!=null&&!chu.equals("wu"))
			{
				sql +=" and p.positionchu='"+chu+"'";
			}
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
