package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.pojo.PKbinorm;

import ccb.hibernate.HibernateSessionFactory;

public class PosKbiAddSearch {
	 private String level;
	 private String posid;
	 private String id;
	 private List<PKbinorm> listkb;	
	  
   
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

	public List<PKbinorm> getListkb() {
		return listkb;
	}

	public void setListkb(List<PKbinorm> listkb) {
		this.listkb = listkb;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

		public String execute() throws Exception
		{
			Query query;
			String hql = "";
			id=posid;
			Session session = HibernateSessionFactory.getSession();
			Transaction trans = session.beginTransaction();
			try {
				hql = "from PKbinorm as kb where 1=1";
				if(!level.equals("wu"))
				{
					hql=hql+" and kb.level='"+level+"'";
				}
				hql +=" order by kb.id";
				System.out.println(hql);
				query = session.createQuery(hql);
				listkb = query.list();
					
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
