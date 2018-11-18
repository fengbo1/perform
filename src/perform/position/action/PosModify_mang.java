package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;

import ccb.hibernate.HibernateSessionFactory;

public class PosModify_mang {
	int id;
	private PPosition pp;	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public PPosition getPp() {
		return pp;
	}

	public void setPp(PPosition pp) {
		this.pp = pp;
	}

	public String execute() throws Exception
	{
		PPositionDAO ppdao = new PPositionDAO();
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		pp=ppdao.findByID(id);
	    trans.commit();
	    session.flush();
		session.clear();
		session.close();
		return "success";
}
}
	
