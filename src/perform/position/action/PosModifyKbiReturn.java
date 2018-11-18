package perform.position.action;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;
public class PosModifyKbiReturn {
	private List<PKtinorm> listkt;	
	private String city;
	private String chu;
	private String tuan;
	private int id;
	private int posid;
	private String ktinormsave;
	private String ktinormpropsave;
	
	public List<PKtinorm> getListkt() {
		return listkt;
	}

	public void setListkt(List<PKtinorm> listkt) {
		this.listkt = listkt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public String getKtinormsave() {
		return ktinormsave;
	}

	public void setKtinormsave(String ktinormsave) {
		this.ktinormsave = ktinormsave;
	}

	public String getKtinormpropsave() {
		return ktinormpropsave;
	}

	public void setKtinormpropsave(String ktinormpropsave) {
		this.ktinormpropsave = ktinormpropsave;
	}

	public String execute() throws Exception
	{
		Query query;
		String hql = "";
	    if(city==null||city.equals(""))
	    {
	    	city="wu";
	    }
	    if(chu==null||chu.equals(""))
	    {
	    	chu="wu";
	    }
	    if(tuan==null||tuan.equals(""))
	    {
	    	tuan="wu";
	    }
		PPositionDAO ppdao=new PPositionDAO();
		PPosition pp =new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    pp=ppdao.findById(posid); 	 
 	    id=pp.getId();
 	    ktinormsave=pp.getKtinorm();
 	    ktinormpropsave=pp.getKtinormprop();
 	    hql = "from PKtinorm as kt";
		hql +=" order by kt.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkt = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
