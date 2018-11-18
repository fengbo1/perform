package perform.position.action;
import java.util.List;
import ccb.hibernate.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKcinorm;
public class PosKciAddSearch {
  private String level;
  private String posid;
  private String id;
  private List<PKcinorm> listkc;	
  
  
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

public List<PKcinorm> getListkc() {
	return listkc;
}

public void setListkc(List<PKcinorm> listkc) {
	this.listkc = listkc;
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
		id = posid;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			hql = "from PKcinorm as kc where 1=1";
			if(!level.equals("wu"))
			{
				hql=hql+" and kc.level='"+level+"'";
			}
			hql +=" order by kc.id";
			System.out.println(hql);
			query = session.createQuery(hql);
			listkc = query.list();
				
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
