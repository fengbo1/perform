package perform.position.action;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class PosModifyKci_mang {
	private String[] kcinorm;
	private String[] kcinormprop;
	private int posid;
	
	private String message;

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

	public String[] getKcinorm() {
		return kcinorm;
	}

	public void setKcinorm(String[] kcinorm) {
		this.kcinorm = kcinorm;
	}

	public String[] getKcinormprop() {
		return kcinormprop;
	}

	public void setKcinormprop(String[] kcinormprop) {
		this.kcinormprop = kcinormprop;
	}

	public String execute() throws Exception
	{
		Query query;
		String hql = "";
		String kcinormcun = "";
		String kcinormpropcun = "";
		int kcipropnum=0;
		if(kcinorm!=null&&kcinorm.length!=0)
		{	
		  kcinormcun =kcinorm[0];
		 for(int i=1;i<kcinorm.length;i++)
	    {
			kcinormcun += "、";
			kcinormcun += kcinorm[i];
			
	    }
		}
		if(kcinormprop!=null&&kcinormprop.length!=0)
		{	
		  //kcinormpropcun =kcinormprop[0];
		  for(int i=0;i<kcinormprop.length;i++)
	    {
			if(kcinormprop[i]!="")
			{				
			   kcinormpropcun += kcinormprop[i];
			   kcinormpropcun += "、";
			   kcipropnum++;
			}
	    }
		  if(kcipropnum!=0)
		  {	  
		  kcinormpropcun=kcinormpropcun.substring(0, kcinormpropcun.length()-1);
		  }
		}
		if(kcipropnum==0)
		{
			message="指标权重未填！";
			return "failed";
		}
		if(kcinorm==null||kcinorm.length==0)
		{
			message="指标未选！";
			return "failed";
		}
		if(kcinorm.length!=kcipropnum)
		{
			message="指标数量和指标权重数量不匹配！";
			return "failed";
		}
		if(kcipropnum!=0)
		{
			
			String propstr="";
			BigDecimal result = new BigDecimal("0");
			for(int i=0;i<kcinormprop.length;i++)
		    {
				if(kcinormprop[i]!="")
				{
					propstr= kcinormprop[i];
					BigDecimal b = new BigDecimal(propstr);			 	    
			 	    result = result.add(b);
				}	
		    }
			double prop=result.doubleValue();
			if(prop!=1.00)
			{
				message="指标权重数量之和不等于1！";
				return "failed";
			}
		}
		PPositionDAO ppdao=new PPositionDAO();
		PPosition pp =new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    pp=ppdao.findById(posid);  
 	    pp.setKcinorm(kcinormcun);
 	    pp.setKcinormprop(kcinormpropcun);
 	    ppdao.merge(pp);
 	    	  
 	    message="修改成功";

 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
