package perform.position.action;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositiontempDAO;
import perform.position.pojo.PPositiontemp;
import ccb.hibernate.HibernateSessionFactory;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
public class PosAddKpi_mang {
	private String[] kbinorm;
	private int id;
	private String posname;
	private String poschu;
	private String message;
	private String chu;
	private List<PKtinorm> listkt;	
	private List<PUser> listu;	

	public String getChu() {
		return chu;
	}

	public void setChu(String chu) {
		this.chu = chu;
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

	public String[] getKbinorm() {
		return kbinorm;
	}

	public void setKbinorm(String[] kbinorm) {
		this.kbinorm = kbinorm;
	}

	public List<PUser> getListu() {
		return listu;
	}

	public void setListu(List<PUser> listu) {
		this.listu = listu;
	}

	public String execute() throws Exception
	{
		PUserDAO pudao = new PUserDAO();
		Query query;
		String hql = "";
		String kbinormcun = "";
		if(kbinorm!=null&&kbinorm.length!=0)
		{	
		  kbinormcun =kbinorm[0];
		 for(int i=1;i<kbinorm.length;i++)
	    {
			kbinormcun += "、";
			kbinormcun += kbinorm[i];
	    }
		}
		PPositiontempDAO ppdao=new PPositiontempDAO();
		PPositiontemp pp =new PPositiontemp();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    
 	    pp=ppdao.findByNameandChuandTuan(posname, poschu);
 	    pp.setKpinorm(kbinormcun); 
 	    pp.setKpinormprop("1");
 	    ppdao.merge(pp);
 	    id=pp.getId();
 	    chu = pp.getChu();
 	    message="添加成功";
 	    hql = "from PKtinorm as kt";
		hql +=" order by kt.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkt = query.list();
		listu = pudao.findRaterByChu(chu);
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
