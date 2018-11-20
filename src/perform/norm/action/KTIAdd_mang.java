package perform.norm.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import perform.norm.pojo.*;
import perform.norm.dao.*;
import ccb.hibernate.HibernateSessionFactory;

public class KTIAdd_mang extends ActionSupport implements ServletResponseAware {

	    /**
	 * 
	 */
	    private static final long serialVersionUID = 1L;
		private String name;
	    private String target;
	    private double score;
	    private String rule;
	    private String chu;
	    private String message;    
	    
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

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		
		public double getScore() {
			return score;
		}

		public void setScore(double score) {
			this.score = score;
		}

		public String getRule() {
			return rule;
		}

		public void setRule(String rule) {
			this.rule = rule;
		}

		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			
		}
		public String execute() throws Exception
		{
			PKtinormDAO ktdao=new PKtinormDAO();
			PKtinorm kt =new PKtinorm();
			PKtinorm kttemp = new PKtinorm();
			Session session = HibernateSessionFactory.getSession();
	 	    Transaction trans = session.beginTransaction();
	 	    kttemp=ktdao.findByChuAndTuanAndName(chu,name);
	 	    if(kttemp!=null)
	 	    {
	 	    	 this.addFieldError("用户","存在处室，团队，名称完全相同指标，请勿重复添加！");
			  	 return "failed";
	 	    }
	 	    if(name==null||name.equals(""))
			  {
	 			 this.addFieldError("用户","指标名为空");
				  	 return "failed";
			  } 
			  else if(target==null||target.equals(""))
			  {
				 this.addFieldError("用户","目标值为空");
			  	 return "failed";
			  }
			  else if(score==0.00)
			  {
				 this.addFieldError("用户","指标分值为空");
			  	 return "failed";
			  }
			  else if(rule==null||rule.equals(""))
			  {
				 this.addFieldError("用户","规则为空");
			  	 return "failed";
			  }
			
			  else if(chu.equals("")||chu.equals("wu"))
			  {
				 this.addFieldError("用户","处室为空");
			  	 return "failed";
			  }
			  /*else if(zu.equals(""))
			  {
				 this.addFieldError("用户","班组为空");
			  	 return "false";
			  }*/
			
	        kt.setName(name);
	        kt.setChu(chu);
	        kt.setTuan("");
	        kt.setTarget(target);
	        kt.setScore(score);
	        kt.setRule(rule);
	 	    ktdao.merge(kt);
	 	    message="添加成功";
	 	    trans.commit();
			session.flush();
			session.clear();
			session.close();
			return "success";
		}
}
