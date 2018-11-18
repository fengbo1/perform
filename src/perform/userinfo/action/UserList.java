package perform.userinfo.action;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.userinfo.pojo.PUser;
import ccb.hibernate.HibernateSessionFactory;

public class UserList {
	private static final Log log = LogFactory.getLog(PUser.class);
	private String newnumber;
	private String chu;
	private String tuan;
	private String city;
	private String zu;
	private String name;
	private String strtemp;
	private int zhuan;
	private List<PUser> list;	
	private int pageSize = 15;
	private int totalPages = -1;
	private int currentPage = -1;
	private int previousPage = 1;
	private int nextPage = 1;
	private int firstPage = 1;
	private int lastPage = 1;
	private long totalRows = -1;
	
	
	public String getStrtemp() {
		return strtemp;
	}
	public void setStrtemp(String strtemp) {
		this.strtemp = strtemp;
	}
	public int getZhuan() {
		return zhuan;
	}
	public void setZhuan(int zhuan) {
		this.zhuan = zhuan;
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
	public List<PUser> getList() {
		return list;
	}
	public void setList(List<PUser> list) {
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
		if(name!=null&&!name.equals("")&&zhuan==1)
		{
			strtemp = new String(name.getBytes("ISO8859-1"),"UTF-8");
		}
		else
		{
			strtemp = name;
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			hql = "from PUser as ui where ui.id>0 ";
//			if(city!=null&&!city.equals("")&&!city.equals("wu"))
//			{
//				hql=hql+" and ui.position like '_____"+city+"_'";
//			}
			if(chu!=null&&!chu.equals("")&&!chu.equals("wu"))
			{
				hql=hql+" and ui.position like '__"+chu+"__'";
			}
			if(tuan!=null&&!tuan.equals("")&&!tuan.equals("wu"))
			{
				hql=hql+" and ui.position like '___"+tuan+"_'";
			}
			if(zu!=null&&!zu.equals("")&&!zu.equals("wu"))
			{
				hql=hql+" and ui.position like '____"+zu+"'";
			}
			if(strtemp!=null&&!strtemp.equals(""))
			{
				hql=hql+" and ui.name='"+strtemp+"'";
			}
			hql +=" order by ui.id";
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
