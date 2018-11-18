package perform.norm.action;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.*;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class KTIListQuery {
	private static final Log log = LogFactory.getLog(PUser.class);
	private List<PKtinorm> list;	
	private int pageSize = 10;
	private int totalPages = -1;
	private int currentPage = -1;
	private int previousPage = 1;
	private int nextPage = 1;
	private int firstPage = 1;
	private int lastPage = 1;
	private long totalRows = -1;
	private String city;
	private String chu;
	private String tuan;
	private String newnumber;
	
	
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
	public List<PKtinorm> getList() {
		return list;
	}
	public void setList(List<PKtinorm> list) {
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
			hql = "from PKtinorm as kt where kt.id>0";
			
			if(position.charAt(0)=='1'&&position.charAt(1)=='2')
			{
				
				hql = hql+" and kt.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='3')
			{
				
				hql = hql+" and kt.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='4')
			{
				
				hql = hql+" and kt.chu not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='5')
			{
				
				hql = hql+" and kt.chu in ("+UserUtil.c_whyqb+")" ;
			}
			
			if(city!=null)
			{
				if(city.equals("1"))
				{
					hql=hql+" and kt.chu in ("+UserUtil.c_ywclzx+")";
				}
				else if(city.equals("2"))
				{
					hql=hql+" and kt.chu in ("+UserUtil.c_cdfzx+")";
				}
				else if(city.equals("3"))
				{
					hql=hql+" and kt.chu in ("+UserUtil.c_whyqb+")";
				}
			}
			if(chu!=null&&!chu.equals("")&&!chu.equals("wu"))
			{
				hql=hql+" and kt.chu='"+chu+"'";
			}
			if(tuan!=null&&!tuan.equals("")&&!tuan.equals("wu"))
			{
				hql=hql+" and kt.tuan='"+tuan+"'";
			}
			hql +=" order by kt.id";
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
