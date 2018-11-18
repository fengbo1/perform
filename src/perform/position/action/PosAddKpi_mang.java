package perform.position.action;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKpinorm;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositionDAO;
import perform.position.dao.PPositiontempDAO;
import perform.position.pojo.PPosition;
import perform.position.pojo.PPositiontemp;
import ccb.hibernate.HibernateSessionFactory;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import com.opensymphony.xwork2.ActionSupport;
import ccb.hibernate.HibernateSessionFactory;
public class PosAddKpi_mang {
	private String category;
	private int id;
	private String posname;
	private String postuan;
	private String poschu;
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

	public String getPosname() {
		return posname;
	}

	public void setPosname(String posname) {
		this.posname = posname;
	}

	public String getPostuan() {
		return postuan;
	}

	public void setPostuan(String postuan) {
		this.postuan = postuan;
	}

	public String getPoschu() {
		return poschu;
	}

	public void setPoschu(String poschu) {
		this.poschu = poschu;
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
		if(category==null||category=="")
		{
			message="未选择KPI指标！";
			return "failed";
		}
		city="wu";
		chu="wu";
		tuan="wu";
		PPositiontempDAO ppdao=new PPositiontempDAO();
		PPositiontemp pp =new PPositiontemp();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    pp=ppdao.findByNameandChuandTuan(posname, poschu, postuan);
 	    pp.setKpinorm(category); 
 	    pp.setKpinormprop("1.00");
 	    ppdao.merge(pp);
 	    id=pp.getId();
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
