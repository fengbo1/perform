package perform.dataimport.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

import ccb.hibernate.HibernateSessionFactory;

public class ShowKpiScoreImport {
	
	private String yewuimp;
	private String jiafenimp;
	private String tiyanimp;
	public String getYewuimp() {
		return yewuimp;
	}
	public void setYewuimp(String yewuimp) {
		this.yewuimp = yewuimp;
	}
	public String getJiafenimp() {
		return jiafenimp;
	}
	public void setJiafenimp(String jiafenimp) {
		this.jiafenimp = jiafenimp;
	}
	public String getTiyanimp() {
		return tiyanimp;
	}
	public void setTiyanimp(String tiyanimp) {
		this.tiyanimp = tiyanimp;
	}
	public String execute() throws Exception
	{
		yewuimp = "";
		jiafenimp = "";
		tiyanimp = "";
		PUserDAO pudao = new PUserDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	//AssetInfoDAO kidao = new AssetInfoDAO();
    	try {
    		PUser puyw = pudao.findByNewNumber("88888887");
    		PUser pujf = pudao.findByNewNumber("88888888");
    		PUser puty = pudao.findByNewNumber("88888889");
    		if(puyw!=null)
    		{
    			yewuimp = puyw.getPassword();
    			jiafenimp = pujf.getPassword();
    			tiyanimp = puty.getPassword();
    		}
    	}
		catch (Exception e) {
			trans.rollback();//出错回滚
			e.printStackTrace();
		    }
			finally{
			 trans.commit();
	         session.flush();
	         session.clear();
	         session.close();
		  }
		return "success";
	}
}
