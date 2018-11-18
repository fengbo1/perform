package perform.position.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import perform.norm.pojo.PKpinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class PosModifyKtiReturn extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 1L;
    private int posid;
	private int id;
	private String kpinorm;
	private List<PKpinorm> listkp;	
	
	
	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public String getKpinorm() {
		return kpinorm;
	}

	public void setKpinorm(String kpinorm) {
		this.kpinorm = kpinorm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PKpinorm> getListkp() {
		return listkp;
	}

	public void setListkp(List<PKpinorm> listkp) {
		this.listkp = listkp;
	}



	public String execute() throws Exception
	{
		Query query;
		String hql = "";
		PPositionDAO ppdao=new PPositionDAO();
		PPosition pp =new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	 
 	    pp=ppdao.findById(posid); 
 	    id=pp.getId();
 	    kpinorm=pp.getKpinorm();	
 		hql = "from PKpinorm as kp ";
		hql +=" order by kp.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkp = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
}
