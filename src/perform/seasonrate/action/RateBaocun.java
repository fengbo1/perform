package perform.seasonrate.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import ccb.hibernate.HibernateSessionFactory;

public class RateBaocun {

	private int year;
	private int season;
	private String message;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String execute() throws Exception
	{
		String result = "success";
		String sql="";
		PFlagDAO pfdao = new PFlagDAO();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		PFlag pf = pfdao.findByYearSeason(year, season);
    		if(pf==null)
    		{
    			message = "请选择当前打分季度！";
    		}
    		else if(pf.getFlag()==1)
    		{
    			message = year+"年"+season+"季度正在打分中，请先结束打分并计算结果，再提交保存打分！";
    		}
    		else if(pf.getFlag()!=2)
    		{
    			message = year+"年"+season+"季度未开启打分，请选择其他操作！";
    		}
    		else
    		{
    			message = year+"年"+season+"季度保存成功！";
    			pf.setFlag(3);
    			pfdao.merge(pf);
    		}
    		
    	}catch (Exception e) {
			trans.rollback();//出错回滚
			e.printStackTrace();
		}finally{
			 trans.commit();
	         session.flush();
	         session.clear();
	         session.close();
		}
		return result;
	}
}
