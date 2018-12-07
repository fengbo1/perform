package perform.seasonrate.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;

import ccb.hibernate.HibernateSessionFactory;

public class RateJisuan {

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
    		else if(pf.getFlag()!=1)
    		{
    			message = year+"年"+season+"季度未开启打分，请选择其他操作！";
    		}
    		else
    		{
    			message = year+"年"+season+"季度计算成功！";
    			//计算小分
        		sql = "update p_ktiscore set sum=CAST((if(result1 is NULL,0,result1)+if(result2 is NULL,0,result2)+if(result3 is NULL,0,result3))/(if(rater1 is NULL,0,1)+if(rater2 is NULL,0,1)+if(rater3 is NULL,0,1)) AS DECIMAL(18,2)) where year='"+year+"' and season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_kbiscore set sum=CAST((if(result1 is NULL,0,result1)+if(result2 is NULL,0,result2)+if(result3 is NULL,0,result3))/(if(rater1 is NULL,0,1)+if(rater2 is NULL,0,1)+if(rater3 is NULL,0,1)) AS DECIMAL(18,2)) where year='"+year+"' and season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_kciscore set sum=CAST((if(result1 is NULL,0,result1)+if(result2 is NULL,0,result2)+if(result3 is NULL,0,result3))/(if(rater1 is NULL,0,1)+if(rater2 is NULL,0,1)+if(rater3 is NULL,0,1)) AS DECIMAL(18,2)) where year='"+year+"' and season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		//将kpiscore得分更新到p_score中
        		sql = "update p_score a set a.kpiscore=(SELECT sum(b.sum) from p_kpiscore b where b.year='"+year+"' and b.season='"+season+"' and a.newnumber=b.newnumber group by b.newnumber) where a.year='"+year+"' and a.season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		//将ktiscore得分更新到p_score中
        		sql = "update p_score a set a.ktiscore=(SELECT sum(b.sum) from p_ktiscore b where b.year='"+year+"' and b.season='"+season+"' and a.newnumber=b.newnumber group by b.newnumber) where a.year='"+year+"' and a.season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		//将kbiscore得分更新到p_score中
        		sql = "update p_score a set a.kbiscore=(SELECT sum(b.sum) from p_kbiscore b where b.year='"+year+"' and b.season='"+season+"' and a.newnumber=b.newnumber group by b.newnumber) where a.year='"+year+"' and a.season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
//        		//将kciscore得分更新到p_score中
        		sql = "update p_score a set a.kciscore=(SELECT sum(b.sum) from p_kciscore b where b.year='"+year+"' and b.season='"+season+"' and a.newnumber=b.newnumber group by b.newnumber) where a.year='"+year+"' and a.season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		//更新p_score总分
        		sql = "update p_score a set a.score=CAST((a.kpiscore*a.kpiprop+a.ktiscore*a.ktiprop+a.kbiscore*a.kbiprop+a.kciscore*a.kciprop) AS DECIMAL(18,2)) where a.year='"+year+"' and a.season='"+season+"'";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_ktiscore set sum=0 where sum is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_kbiscore set sum=0 where sum is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_kciscore set sum=0 where sum is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_score set ktiscore=0 where ktiscore is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_score set kbiscore=0 where kbiscore is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_score set kciscore=0 where kciscore is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		sql = "update p_score set score=0 where score is NULL";
        		session.createSQLQuery(sql).executeUpdate();
        		pf.setFlag(2);
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
