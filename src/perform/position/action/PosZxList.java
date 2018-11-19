package perform.position.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.pojo.PPosition;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class PosZxList {
	private static final Log log = LogFactory.getLog(PPosition.class);
	private String newnumber;
	private String chu;
	private String tuan;
	private String city;
	private List<PPosition> list;	
	private int pageSize = 20;
	private int totalPages = -1;
	private int currentPage = -1;
	private int previousPage = 1;
	private int nextPage = 1;
	private int firstPage = 1;
	private int lastPage = 1;
	private long totalRows = -1;
	
	public String getNewnumber() {
		return newnumber;
	}
	public void setNewnumber(String newnumber) {
		this.newnumber = newnumber;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<PPosition> getList() {
		return list;
	}
	public void setList(List<PPosition> list) {
		this.list = list;
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
	public String execute() throws Exception
	{
		Query query;
		String hql = "";
		PUserDAO pudao = new PUserDAO();
		if(chu==null)
		{
			chu="wu";
		}
		if(city==null)
		{
			city="wu";
		}
		if(tuan==null)
		{
			tuan="wu";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		PUser purater = pudao.findByNewNumber(newnumber);
		String position = purater.getPosition();
		try {
			hql = "from PPosition as pos where 1=1";
			
			if(position.charAt(0)=='1'&&position.charAt(1)=='2')
			{
				
				hql = hql+" and pos.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='3')
			{
				
				hql = hql+" and pos.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='4')
			{
				
				hql = hql+" and pos.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='5')
			{
				
				hql = hql+" and pos.chu in ("+UserUtil.c_whyqb+")" ;
			}
			if(chu!=null&&chu!=""&&!chu.equals("wu"))
			{
				hql=hql+" and pos.chu='"+chu+"'";
			}
			if(tuan!=null&&tuan!=""&&!tuan.equals("wu"))
			{
				hql=hql+" and pos.tuan='"+tuan+"'";
			}
			hql +=" order by pos.id";
			System.out.println(hql);
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
	
		return "success";
	}

	protected void initPageProperties() {

		if (totalRows == -1) {
			log.error("没有初始化totalRows参数！");
		}

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