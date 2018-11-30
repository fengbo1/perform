package perform.seasonquery.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.DateTimeUtil;
import perform.util.UserUtil;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class SeasonQueryCenter {
	private int year;
	private int season;
	private String chu;
	private String name;
	private String rater;
	private int zhuan;
	private int sorttype;
	private List<PScore> list;
	private List<Integer> listyear;
	private int pageSize = Util.PAGESIZE;
	private int totalPages = -1;
	private int currentPage = -1;
	private int previousPage = 1;
	private int nextPage = 1;
	private int firstPage = 1;
	private int lastPage = 1;
	private long totalRows = -1;
	private String alreadyrate;//已评分人
	private String notrate;//未评分人
	
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
	public String getAlreadyrate() {
		return alreadyrate;
	}
	public void setAlreadyrate(String alreadyrate) {
		this.alreadyrate = alreadyrate;
	}
	public String getNotrate() {
		return notrate;
	}
	public void setNotrate(String notrate) {
		this.notrate = notrate;
	}
	public String execute() throws Exception
	{
		Query query;
		alreadyrate = "";
		notrate = "";
		String result = "success";
		String hql = "";
		UserUtil uu = new UserUtil();
		PFlagDAO pfdao = new PFlagDAO();
		PUserDAO pudao = new PUserDAO();
		DateTimeUtil dtu = new DateTimeUtil();
		listyear = dtu.getLast10Years();
//		if(chu!=null&&zhuan==1)
//		{
//			chu= new String(chu.getBytes("ISO8859-1"),"UTF-8");
//		}
		if(name!=null&&zhuan==1)
		{
			name = new String(name.getBytes("ISO8859-1"),"UTF-8");
		}
		if(chu==null)
		{
			chu="wu";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			
			PUser purater = pudao.findByNewNumber(rater);
			String position = purater.getPosition();
			if(year==0||season==0)//未选年和季度
			{
				PFlag pf = pfdao.findByIsNew(1);
				year = pf.getYear();
				season = pf.getSeason();
			}
			List<PUser> listrater = pudao.findRaterByChu(chu);
			PFlag pf = pfdao.findByYearSeason(year, season);
			String adyrate = pf.getAlreadyrate();
			for(int i=0;i<listrater.size();i++)
			{
				PUser pu = listrater.get(i);
				if(adyrate.contains(pu.getNewnumber()))
				{
					alreadyrate+=pu.getName();
					alreadyrate+="、";
				}
				else
				{
					notrate+=pu.getName();
					notrate+="、";
				}
			}
			hql = "from PScore as p where p.year='"+year+"' and p.season='"+season+"'";
			if(chu!=null&&!chu.equals("wu"))
			{
				hql +=" and p.position like '__"+chu+"__'";
			}
			if(name!=null&&!name.equals(""))
			{
				hql +=" and p.name='"+name+"'";
			}
			if(sorttype!=0&&sorttype==1)
			{
				hql +=" order by p.kpiscore desc";
			}
			if(sorttype!=0&&sorttype==2)
			{
				hql +=" order by p.ktiscore desc";
			}
			if(sorttype!=0&&sorttype==3)
			{
				hql +=" order by p.kbiscore desc";
			}
			if(sorttype!=0&&sorttype==4)
			{
				hql +=" order by p.kciscore desc";
			}
			if(sorttype!=0&&sorttype==5)
			{
				hql +=" order by p.score desc";
			}
			query = session.createQuery(hql);
			query.setFirstResult(pageSize * (currentPage - 1));
			query.setMaxResults(pageSize);
			totalRows = session.createQuery(hql).list().size();
			initPageProperties();
			list = query.list();
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
	
	protected void initPageProperties() {


		firstPage = 1;
		
		currentPage = currentPage <= 1 ? 1 : currentPage;

		totalPages = (totalRows % pageSize == 0) ? ((int) (totalRows / pageSize))
				: ((int) (totalRows / pageSize + 1));

		currentPage = currentPage >= totalPages ? totalPages : currentPage;

		previousPage = currentPage > 1 ? currentPage - 1 : 1;

		nextPage = currentPage < totalPages ? currentPage + 1 : totalPages;

		lastPage = totalPages;
	}
}
