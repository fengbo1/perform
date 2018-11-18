package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKcinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class PosModifyKciReturn {

	private int id;
	private int posid;
	private List<PKbinorm> listkb;	
	private String kbinormsave;
	private String kbinormpropsave;
    private String level;

    
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	public String getKbinormsave() {
		return kbinormsave;
	}

	public void setKbinormsave(String kbinormsave) {
		this.kbinormsave = kbinormsave;
	}

	public String getKbinormpropsave() {
		return kbinormpropsave;
	}

	public void setKbinormpropsave(String kbinormpropsave) {
		this.kbinormpropsave = kbinormpropsave;
	}

	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<PKbinorm> getListkb() {
		return listkb;
	}

	public void setListkb(List<PKbinorm> listkb) {
		this.listkb = listkb;
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
 	    kbinormsave=pp.getKbinorm();
 	    kbinormpropsave=pp.getKbinormprop();
 	    hql = "from PKbinorm as kb ";
		hql +=" order by kb.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkb = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
