package perform.userinfo.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class ShowUserAdd {
	private List<PPosition> listpos;
	
	public List<PPosition> getListpos() {
		return listpos;
	}

	public void setListpos(List<PPosition> listpos) {
		this.listpos = listpos;
	}

	public String execute() throws Exception
	{
		Query query;
		String hql="";
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    hql = "from PPosition as pos where pos.id>0 ";
 	    hql +=" order by pos.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listpos = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
