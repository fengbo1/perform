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

public class PosModifyKpiReturn extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private int posid;
	private PPosition pp;
	


	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public PPosition getPp() {
		return pp;
	}

	public void setPp(PPosition pp) {
		this.pp = pp;
	}

	public String execute() throws Exception
	{
		
		PPositionDAO ppdao=new PPositionDAO();
	
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction(); 
 	    pp=ppdao.findById(posid);	   
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
