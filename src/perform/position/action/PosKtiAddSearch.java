package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.pojo.PKtinorm;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;
import ccb.hibernate.HibernateSessionFactory;

public class PosKtiAddSearch {
	  private String chu;
	  private String tuan;
	  private String city;
	  private String posid;
	  private String id;
	  private List<PKtinorm> listkt;
	  private List<PUser> listu;	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<PKtinorm> getListkt() {
		return listkt;
	}
	public void setListkt(List<PKtinorm> listkt) {
		this.listkt = listkt;
	}
	public List<PUser> getListu() {
		return listu;
	}
	public void setListu(List<PUser> listu) {
		this.listu = listu;
	}
	public String execute() throws Exception
		{
			PUserDAO pudao = new PUserDAO();
			Query query;
			String hql = "";
			id = posid;
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
			try {
				listu = pudao.findRaterByChu(chu);	
				hql = "from PKtinorm as kt where 1=1";
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
				if(chu!=null&&chu!=""&&!chu.equals("wu"))
				{
					hql=hql+" and kt.chu='"+chu+"'";
				}
				if(tuan!=null&&tuan!=""&&!tuan.equals("wu"))
				{
					hql=hql+" and kt.tuan='"+tuan+"'";
				}
				hql +=" order by kt.id";
				System.out.println(hql);
				query = session.createQuery(hql);
				listkt = query.list();
				
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
