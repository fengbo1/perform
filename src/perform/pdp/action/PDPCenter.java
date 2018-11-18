package perform.pdp.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class PDPCenter {
	private String viewer;
	private String city;
	private String chu;
	private String tuan;
	private String zu;
	private String name;
	private List<PUser> list;
	private String rater;
	
	
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public String getViewer() {
		return viewer;
	}
	public void setViewer(String viewer) {
		this.viewer = viewer;
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
	public List<PUser> getList() {
		return list;
	}
	public void setList(List<PUser> list) {
		this.list = list;
	}
	public String execute() throws Exception
	{
		PUserDAO pudao  = new PUserDAO();
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
		PUser purater = pudao.findByNewNumber(rater);
		String position = purater.getPosition();
		
		
		try {
			String hql = "from PUser as pu where 1=1";
			
			if(position.charAt(0)=='1'&&position.charAt(1)=='2')
			{
				
				hql = hql+" and substring(pu.position,3,1) not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='3')
			{
				
				hql = hql+" and substring(pu.position,3,1) not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='4')
			{
				
				hql = hql+" and substring(pu.position,3,1) not in ("+UserUtil.c_whyqb+")" ;
			}
			if(position.charAt(0)=='1'&&position.charAt(1)=='5')
			{
				
				hql = hql+" and substring(pu.position,3,1) in ("+UserUtil.c_whyqb+")" ;
			}
			if(city!=null&&!city.equals("wu"))
			{
				hql += " and pu.position like '_____"+city+"_'";
			}
			if(chu!=null&&!chu.equals("wu"))
			{
				hql += " and pu.position like '__"+chu+"____'";
			}
			if(tuan!=null&&!tuan.equals("wu"))
			{
				hql += " and pu.position like '___"+tuan+"___'";
			}
			if(zu!=null&&!zu.equals("wu"))
			{
				hql += " and pu.position like '____"+zu+"__'";
			}
			if(name!=null&&!name.equals(""))
			{
				hql += " and pu.name='"+name+"'";
			}
			hql += " order by pu.position";
			System.out.println(hql);
			list = session.createQuery(hql).list();
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
