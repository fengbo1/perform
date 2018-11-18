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
	private String tuan;
	private double kpiprop;
	private double ktiprop;
	private double kbiprop;
	private double kciprop;
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

	public String getTuan() {
		return tuan;
	}

	public void setTuan(String tuan) {
		this.tuan = tuan;
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
 	    ppcheck = ppcheckdao.findByNameandChuandTuan(name, chu, tuan);
 	    if(ppcheck!=null)
 	    {
 	    	 this.addFieldError("用户","已存在同名称、同处室、同团队岗位，不许重复添加！");
		  	 return "failed";
 	    }
 	    BigDecimal b1 = new BigDecimal(Double.valueOf(kpiprop));
 	    BigDecimal b2 = new BigDecimal(Double.valueOf(ktiprop));
 	    BigDecimal b3 = new BigDecimal(Double.valueOf(kbiprop));
 	    BigDecimal b4 = new BigDecimal(Double.valueOf(kciprop));
 	    
 	    double prop = b1.add(b2).add(b3).add(b4).doubleValue();
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
		  else if(tuan.equals("")||tuan.equals("wu"))
		  {
			 this.addFieldError("用户","团队为空");
		  	 return "failed";
		  }
		  else if(kpiprop==0.00)
		  {
			 this.addFieldError("用户","KPI权重为空");
		  	 return "failed";
		  }
		  else if(ktiprop==0.00)
		  {
			 this.addFieldError("用户","KTI权重为空");
		  	 return "failed";
		  }
		  else if(kciprop==0.00)
		  {
			 this.addFieldError("用户","KCI权重为空");
		  	 return "failed";
		  }
		  else if(kbiprop==0.00)
		  {
			 this.addFieldError("用户","KBI权重为空");
		  	 return "failed";
		  }
		  else if (prop!=1.00)
		  {
			  this.addFieldError("用户","4K权重之和不为1");
			   return "failed";
		  }
 	    pp.setName(name);
 	    pp.setChu(chu);
 	    pp.setTuan(tuan);
 	    pp.setKbiprop(kbiprop);
 	    pp.setKciprop(kciprop);
 	    pp.setKpiprop(kpiprop);
 	    pp.setKtiprop(ktiprop);
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
