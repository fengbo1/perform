package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class PosModifyKpi_mang {
	private String category;
	private int id;
	private int posid;
	private String ktinormsave;
	private String ktinormpropsave;
	private String message;
	private String city;
	private String chu;
	private String tuan;
	private List<PKtinorm> listkt;	
		

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

	public List<PKtinorm> getListkt() {
		return listkt;
	}

	public void setListkt(List<PKtinorm> listkt) {
		this.listkt = listkt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String execute() throws Exception
	{
		Query query;
		String hql = "";
		city="wu";
		chu="wu";
		tuan="wu";
		if(category==null||category=="")
		{
			return "failed";
		}
		PPositionDAO ppdao=new PPositionDAO();
		PPosition pp =new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    pp=ppdao.findById(posid);
 	    pp.setKpinorm(category); 
 	    pp.setKpinormprop("1.00");
 	    ppdao.merge(pp);
 	    id=pp.getId();
 	    ktinormsave=pp.getKtinorm();
 	    ktinormpropsave=pp.getKtinormprop();
 	    message="添加成功";
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
