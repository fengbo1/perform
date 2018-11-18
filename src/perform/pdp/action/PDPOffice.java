package perform.pdp.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

public class PDPOffice {
	
	private String rater;
	private String tuan;
	private String chu;
	private String zu;
	private String name;
	private List<PUser> list;
	
	public String getChu() {
		return chu;
	}
	public void setChu(String chu) {
		this.chu = chu;
	}
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
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
			
			String position = purater.getPosition();
			if(position.length()>5)
			{
				chu=position.substring(2, 3);
			}
			String hql = "from PUser as pu where pu.position like '__"+chu+"____'";
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
