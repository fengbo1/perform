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
	private String chu;
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
	public List<PUser> getList() {
		return list;
	}
	public void setList(List<PUser> list) {
		this.list = list;
	}
	public String execute() throws Exception
	{
		PUserDAO pudao  = new PUserDAO();
		if(chu==null)
		{
			chu="wu";
		}
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		PUser purater = pudao.findByNewNumber(rater);
		String position = purater.getPosition();
		
		
		try {
			String hql = "from PUser as pu where 1=1";
			if(chu!=null&&!chu.equals("wu"))
			{
				hql += " and pu.position like '__"+chu+"____'";
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
