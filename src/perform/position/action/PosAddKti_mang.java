package perform.position.action;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKtinorm;
import perform.position.dao.PPositiontempDAO;
import perform.position.pojo.PPositiontemp;
import ccb.hibernate.HibernateSessionFactory;

public class PosAddKti_mang {
	private String[] ktinorm;
	private String[] ktinormprop;
	private int posid;
	private int id;
	private String message;
	private String level;
	private List<PKbinorm> listkb;	
	
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PKbinorm> getListkb() {
		return listkb;
	}

	public void setListkb(List<PKbinorm> listkb) {
		this.listkb = listkb;
	}

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

	public String[] getKtinorm() {
		return ktinorm;
	}

	public void setKtinorm(String[] ktinorm) {
		this.ktinorm = ktinorm;
	}

	public String[] getKtinormprop() {
		return ktinormprop;
	}

	public void setKtinormprop(String[] ktinormprop) {
		this.ktinormprop = ktinormprop;
	}

	public String execute() throws Exception
	{
		Query query;
		level="wu";
		String hql = "";
		String ktinormcun = "";
		String ktinormpropcun = "";
		int ktipropnum=0;
		if(ktinorm!=null&&ktinorm.length!=0)
		{	
		  ktinormcun =ktinorm[0];
		 for(int i=1;i<ktinorm.length;i++)
	    {
			ktinormcun += "、";
			ktinormcun += ktinorm[i];
			
	    }
		}
		if(ktinormprop!=null&&ktinormprop.length!=0)
		{	
		  //ktinormpropcun =ktinormprop[0];
		  for(int i=0;i<ktinormprop.length;i++)
	    {
			if(ktinormprop[i]!="")
			{				
			   ktinormpropcun += ktinormprop[i];
			   ktinormpropcun += "、";
			   ktipropnum++;
			}
	    }
		 if(ktipropnum!=0)
		 {	 
		  ktinormpropcun=ktinormpropcun.substring(0, ktinormpropcun.length()-1);
		 }
		}
		if(ktipropnum==0)
		{
			message="指标权重未填！";
			return "failed";
		}
		if(ktinorm==null||ktinorm.length==0)
		{
			message="指标未选！";
			return "failed";
		}
		if(ktinorm.length!=ktipropnum)
		{
			message="指标数量和指标权重数量不匹配！";
			return "failed";
		}
		if(ktipropnum!=0)
		{
			
			String propstr="";
			BigDecimal result = new BigDecimal("0");	
			for(int i=0;i<ktinormprop.length;i++)
		    {
				if(ktinormprop[i]!="")
				{
					propstr= ktinormprop[i];	
					BigDecimal b = new BigDecimal(propstr);			 	    
			 	    result = result.add(b);
			       
				}	
		    }
			double prop=result.doubleValue();
			
			if(prop!=1.0)
			{
				message="指标权重数量之和不等于1！";
				return "failed";
			}
		}
		PPositiontempDAO ppdao=new PPositiontempDAO();
		PPositiontemp pp =new PPositiontemp();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    System.out.println("posid:"+posid);
 	    pp=ppdao.findById(posid);	    
 	    pp.setKtinorm(ktinormcun);
 	    pp.setKtinormprop(ktinormpropcun);
 	    ppdao.merge(pp);
 	    id=pp.getId();
 	    message="添加成功";
 	    hql = "from PKbinorm as kb ";
		hql +=" order by kb.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkb = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}
}
