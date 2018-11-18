package perform.seasonrate.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.util.DateTimeUtil;

import ccb.hibernate.HibernateSessionFactory;

public class RateSet {

	private List<Integer> listyear;
	private String status;
	
	public List<Integer> getListyear() {
		return listyear;
	}
	public void setListyear(List<Integer> listyear) {
		this.listyear = listyear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String execute() throws Exception
	{
		DateTimeUtil dtu = new DateTimeUtil();
		PFlagDAO pfdao = new PFlagDAO();
		listyear = dtu.getLast10Years();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		PFlag pf = pfdao.findByIsNew(1);
    		if(pf==null)
    		{
    			status = "无";
    		}
    		else
    		{
    			if(pf.getFlag()==0)
    			{
    				status = "初始化状态，请开启评分！";
    			}
    			else if(pf.getFlag()==1)
    			{
    				status = "（1）当前评分季度："+pf.getYear()+"年"+pf.getSeason()+"季度，";
        			status+= "评分进行中！";
    			}
    			else if(pf.getFlag()==2)
    			{
    				status = "（2）当前评分季度："+pf.getYear()+"年"+pf.getSeason()+"季度，";
        			status+= "已结束并计算评分结果！注：此时点击开始评分，可修改"+pf.getYear()+"年"+pf.getSeason()+"季度评分";
    			}
    			else if(pf.getFlag()==3)
    			{
    				status = "（3）最近评分季度："+pf.getYear()+"年"+pf.getSeason()+"季度，";
        			status+= "已提交并保存评分结果！注：此时点击开始评分，将清空"+pf.getYear()+"年"+pf.getSeason()+"季度评分";
    			}
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
		return "success";
	}
}
