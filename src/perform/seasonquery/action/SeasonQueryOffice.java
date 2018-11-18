package perform.seasonquery.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.bean.SeasonScoreBean;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class SeasonQueryOffice {
	private int year;
	private int season;
	private String chu;
	private String tuan;
	private String zu;
	private String name;
	private String rater;
	private int sorttype;
	private List<PScore> list;
	private List<Integer> listyear;
	private int zhuan;
		
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
		List<PScore> listps = new ArrayList<PScore>();
		
		listyear = dtu.getLast10Years();
		
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
			if(sorttype!=0&&sorttype==1)
			{
				sql +=" order by p.kpiscore desc";
			}
			if(sorttype!=0&&sorttype==2)
			{
				sql +=" order by p.ktiscore desc";
			}
			if(sorttype!=0&&sorttype==3)
			{
				sql +=" order by p.kbiscore desc";
			}
			if(sorttype!=0&&sorttype==4)
			{
				sql +=" order by p.kciscore desc";
			}
			if(sorttype!=0&&sorttype==5)
			{
				sql +=" order by p.score desc";
			}
			System.out.println(sql);
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
