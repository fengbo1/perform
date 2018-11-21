package perform.position.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.pojo.PKpinorm;
import perform.position.dao.PPositionDAO;
import perform.position.dao.PPositiontempDAO;
import perform.position.pojo.PPosition;
import perform.position.pojo.PPositiontemp;
import com.opensymphony.xwork2.ActionSupport;
import ccb.hibernate.HibernateSessionFactory;

public class PosAdd_mang extends ActionSupport implements ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String chu;
	private double kpiprop;
	private double ktiprop;
	private double kbiprop;
	private double kciprop;
	private int typea;
	private int typeb;
	private int typec;
	private int typed;
	private String message;
	private List<PKpinorm> listkp;	
	
	
	public List<PKpinorm> getListkp() {
		return listkp;
	}

	public void setListkp(List<PKpinorm> listkp) {
		this.listkp = listkp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChu() {
		return chu;
	}

	public void setChu(String chu) {
		this.chu = chu;
	}

	public double getKpiprop() {
		return kpiprop;
	}

	public void setKpiprop(double kpiprop) {
		this.kpiprop = kpiprop;
	}

	public double getKtiprop() {
		return ktiprop;
	}

	public void setKtiprop(double ktiprop) {
		this.ktiprop = ktiprop;
	}

	public double getKbiprop() {
		return kbiprop;
	}

	public void setKbiprop(double kbiprop) {
		this.kbiprop = kbiprop;
	}

	public double getKciprop() {
		return kciprop;
	}

	public void setKciprop(double kciprop) {
		this.kciprop = kciprop;
	}

	public int getTypea() {
		return typea;
	}

	public void setTypea(int typea) {
		this.typea = typea;
	}

	public int getTypeb() {
		return typeb;
	}

	public void setTypeb(int typeb) {
		this.typeb = typeb;
	}

	public int getTypec() {
		return typec;
	}

	public void setTypec(int typec) {
		this.typec = typec;
	}

	public int getTyped() {
		return typed;
	}

	public void setTyped(int typed) {
		this.typed = typed;
	}

	public String execute() throws Exception
	{
		Query query;
		String hql = "";
		PPositiontempDAO ppdao=new PPositiontempDAO();
		PPositiontemp pp =new PPositiontemp();
		PPositionDAO ppcheckdao = new PPositionDAO();
		PPosition ppcheck = new PPosition();
		Session session = HibernateSessionFactory.getSession();
 	    Transaction trans = session.beginTransaction();
 	    ppcheck = ppcheckdao.findByNameandChuandTuan(name, chu);
 	    if(ppcheck!=null)
 	    {
 	    	 this.addFieldError("用户","已存在同名称、同处室岗位，不许重复添加！");
		  	 return "failed";
 	    }
 	    BigDecimal b1 = new BigDecimal(Double.valueOf(kpiprop));
 	    BigDecimal b2 = new BigDecimal(Double.valueOf(ktiprop));
 	    BigDecimal b3 = new BigDecimal(Double.valueOf(kbiprop));
 	    BigDecimal b4 = new BigDecimal(Double.valueOf(kciprop));
 	    
 	    BigDecimal temp = new BigDecimal(0.0);
 	    if(typea==0)
 	    {
 	    	temp = temp.add(b1);
 	    	 pp.setKpiprop(kpiprop);
 	    	if(kpiprop==0.00)
 			  {
 				 this.addFieldError("用户","关键业务指标权重为空");
 			  	 return "failed";
 			  }
 	    }
 	    else
 	    {
 	    	 pp.setKpiprop((double)typea);
 	    }
 	    if(typeb==0)
	    {
 	    	temp = temp.add(b2);
	    	pp.setKtiprop(ktiprop);
	    	if(ktiprop==0.00)
			  {
				 this.addFieldError("用户","关键任务目标权重为空");
			  	 return "failed";
			  }
	    }
 	    else
	    {
	    	 pp.setKtiprop((double)typeb);
	    }
 	   	if(typec==0)
	    {
 	   		temp = temp.add(b3);
	    	pp.setKbiprop(kbiprop);
	    	if(kbiprop==0.00)
			  {
				 this.addFieldError("用户","品能目标权重为空");
			  	 return "failed";
			  }
	    }
 	   	else
	    {
	    	 pp.setKbiprop((double)typec);
	    }
 	  	if(typed==0)
	    {
 	  		temp = temp.add(b4);
	    	pp.setKciprop(kciprop);
	    	if(kciprop==0.00)
			  {
				 this.addFieldError("用户","加分项权重为空");
			  	 return "failed";
			  }
	    }
 		else
	    {
	    	 pp.setKciprop((double)typed);
	    }
 	    double prop = temp.doubleValue();
 	    if(name.equals(""))
		  {
			 this.addFieldError("用户","岗位名为空");
			  	 return "failed";
		  } 
		  else if(chu.equals("")||chu.equals("wu"))
		  {
			 this.addFieldError("用户","处室为空");
		  	 return "failed";
		  }
		  else if (prop!=1.00)
		  {
			  this.addFieldError("用户","权重之和不为1");
			   return "failed";
		  }
 	    pp.setName(name);
 	    pp.setChu(chu);
 	    ppdao.merge(pp);
 	    message="添加成功";
 		hql = "from PKpinorm as kp ";
		hql +=" order by kp.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listkp = query.list();
 	    trans.commit();
		session.flush();
		session.clear();
		session.close();
		return "success";
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
}
