package perform.position.action;

import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.position.dao.PPositionDAO;
import perform.position.dao.PPositiontempDAO;
import perform.position.pojo.PPosition;
import perform.position.pojo.PPositiontemp;
import ccb.hibernate.HibernateSessionFactory;

public class PosAddKci_mang {
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
//		if(kcipropnum==0)
//		{
//			message="指标系数未填！";
//			return "failed";
//		}
//		if(kcinorm==null||kcinorm.length==0)
//		{
//			message="指标未选！";
//			return "failed";
//		}
//		if(kcinorm.length!=kcipropnum)
//		{
//			message="指标数量和指标系数数量不匹配！";
//			return "failed";
//		}
		PPositiontempDAO pptdao=new PPositiontempDAO();
		PPositiontemp ppt =new PPositiontemp();
		PPositionDAO ppdao=new PPositionDAO();
		PPosition pp =new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    ppt=pptdao.findAllById(posid);  
 	    ppt.setKcinorm(kcinormcun);
 	    ppt.setKcinormprop(kcinormpropcun);
 	    pptdao.merge(ppt);
 	    pp.setName(ppt.getName());
 	    pp.setChu(ppt.getChu());
 	    pp.setTuan(ppt.getTuan());
 	    pp.setKpinorm(ppt.getKpinorm());
 	    pp.setKpinormprop(ppt.getKpinormprop());
 	    pp.setKpirater(ppt.getKpirater());
 	    pp.setKtinorm(ppt.getKtinorm());
 	    pp.setKtinormprop(ppt.getKtinormprop());
 	    pp.setKtirater(ppt.getKtirater());
 	    pp.setKbinorm(ppt.getKbinorm());
 	    pp.setKbinormprop(ppt.getKbinormprop());
 	    pp.setKbirater(ppt.getKbirater());
 	    pp.setKcinorm(ppt.getKcinorm());
 	    pp.setKcinormprop(ppt.getKcinormprop());
 	    pp.setKcirater(ppt.getKcirater());
 	    pp.setKpiprop(ppt.getKpiprop());
 	    pp.setKtiprop(ppt.getKtiprop());
 	    pp.setKbiprop(ppt.getKbiprop());
 	    pp.setKciprop(ppt.getKciprop());
 	    ppdao.merge(pp);
 	   
 	  
 	    message="添加成功";
 
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
