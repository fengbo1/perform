package perform.userinfo.action;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.pojo.PPosition;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import ccb.hibernate.HibernateSessionFactory;
public class UserModify_mang {
	private PUser ui;
	private int id;
	private String city;
	private String zhi;
	private String zf;//职务正负
	private String chu;
	private String tuan;
	private String zu;
	private String pos;
	private String role;
	private String authoE;
	private String authoW;
	private String authoX;
	private int canscore;
	private List<PPosition> listpos;
	
	
	public int getCanscore() {
		return canscore;
	}

	public void setCanscore(int canscore) {
		this.canscore = canscore;
	}

	public String getAuthoE() {
		return authoE;
	}

	public void setAuthoE(String authoE) {
		this.authoE = authoE;
	}

	public String getAuthoW() {
		return authoW;
	}

	public void setAuthoW(String authoW) {
		this.authoW = authoW;
	}

	public String getAuthoX() {
		return authoX;
	}

	public void setAuthoX(String authoX) {
		this.authoX = authoX;
	}

	public List<PPosition> getListpos() {
		return listpos;
	}

	public void setListpos(List<PPosition> listpos) {
		this.listpos = listpos;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZhi() {
		return zhi;
	}

	public void setZhi(String zhi) {
		this.zhi = zhi;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
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

	public String getZu() {
		return zu;
	}

	public void setZu(String zu) {
		this.zu = zu;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

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
		Query query;
		String hql="";
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    PUserDAO uidao=new PUserDAO();
 	    ui = uidao.findAllById(id);
 	    zhi=ui.getPosition().substring(0,1);
 	    zf=ui.getPosition().substring(1,2);
 	    chu=ui.getPosition().substring(2,3);
 	    tuan=ui.getPosition().substring(3,4);
 	    zu=ui.getPosition().substring(4,5);
 	    city="2";
 	    
 	    canscore=ui.getCanscore()==null?0:ui.getCanscore();
 	    pos=ui.getPnum()==null?"":ui.getPnum().toString();
 	    role=ui.getAutho();
 	    authoE=role.substring(4,5);
 	    authoW=role.substring(22,23);
 	    authoX=role.substring(23,24);
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
