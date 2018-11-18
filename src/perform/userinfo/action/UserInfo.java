package perform.userinfo.action;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

import ccb.hibernate.HibernateSessionFactory;
public class UserInfo {
	private PUser ui;
	private int id;
	


	public PUser getUi() {
		return ui;
	}

	public void setUi(PUser ui) {
		this.ui = ui;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String execute() throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    PUserDAO uidao=new PUserDAO();
 	    ui = uidao.findById(id);
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
