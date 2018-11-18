package perform.position.action;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.pojo.PKcinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class PosModifyKbi_mang {
	private String[] kbinorm;
	private String[] kbinormprop;
	private int id;
	private int posid;
	private String message;
	private List<PKcinorm> listkc;	
	private String kcinormsave;
	private String kcinormpropsave;
    private String level;

    
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getKcinormsave() {
		return kcinormsave;
	}

	public void setKcinormsave(String kcinormsave) {
		this.kcinormsave = kcinormsave;
	}

	public String getKcinormpropsave() {
		return kcinormpropsave;
	}

	public void setKcinormpropsave(String kcinormpropsave) {
		this.kcinormpropsave = kcinormpropsave;
	}

	public int getPosid() {
		return posid;
	}

	public void setPosid(int posid) {
		this.posid = posid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String[] getKbinormprop() {
		return kbinormprop;
	}

	public void setKbinormprop(String[] kbinormprop) {
		this.kbinormprop = kbinormprop;
	}

	public List<PKcinorm> getListkc() {
		return listkc;
	}

	public void setListkc(List<PKcinorm> listkc) {
		this.listkc = listkc;
	}

	public String execute() throws Exception
	{
		Query query;
		level = "wu";
		String hql = "";
		String kbinormcun = "";
		String kbinormpropcun = "";
		int kbipropnum=0;
		if(kbinorm!=null&&kbinorm.length!=0)
		{	
		  kbinormcun =kbinorm[0];
		 for(int i=1;i<kbinorm.length;i++)
	    {
			kbinormcun += "、";
			kbinormcun += kbinorm[i];
			
	    }
		}
		if(kbinormprop!=null&&kbinormprop.length!=0)
		{	
		  //kbinormpropcun =kbinormprop[0];
		  for(int i=0;i<kbinormprop.length;i++)
	    {
			if(kbinormprop[i]!="")
			{				
			   kbinormpropcun += kbinormprop[i];
			   kbinormpropcun += "、";
			   kbipropnum++;
			}
	    }
		  if(kbipropnum!=0)
		  {	  
		  kbinormpropcun=kbinormpropcun.substring(0, kbinormpropcun.length()-1);
		  }
		}
		if(kbipropnum==0)
		{
			message="指标权重未填！";
			return "failed";
		}
		if(kbinorm==null||kbinorm.length==0)
		{
			message="指标未选！";
			return "failed";
		}
		if(kbinorm.length!=kbipropnum)
		{
			message="指标数量和指标权重数量不匹配！";
			return "failed";
		}
		if(kbipropnum!=0)
		{
			
			String propstr="";
			BigDecimal result = new BigDecimal("0");
			for(int i=0;i<kbinormprop.length;i++)
		    {
				if(kbinormprop[i]!="")
				{
					propstr= kbinormprop[i];
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
 	    
 	    pp.setKbinorm(kbinormcun);
 	    pp.setKbinormprop(kbinormpropcun);
 	    ppdao.merge(pp);
 	    id=pp.getId();
 	    kcinormsave=pp.getKcinorm();
 	    kcinormpropsave=pp.getKcinormprop();
 	    message="添加成功";
 	    hql = "from PKcinorm as kc ";
		hql +=" order by kc.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkc = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
