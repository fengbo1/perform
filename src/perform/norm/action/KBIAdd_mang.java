package perform.norm.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;
import perform.norm.dao.PKbinormDAO;
import perform.norm.pojo.PKbinorm;
import ccb.hibernate.HibernateSessionFactory;

public class KBIAdd_mang extends ActionSupport implements ServletResponseAware {
	
	 private static final long serialVersionUID = 1L;
		private String name;
	    private String target;
	    private double score;
	    private String rule;
	    private String level;
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


		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
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
			PKbinormDAO kbdao=new PKbinormDAO();
			PKbinorm kb =new PKbinorm();
			PKbinorm kbtemp = new PKbinorm();
			Session session = HibernateSessionFactory.getSession();
	 	    Transaction trans = session.beginTransaction();
	 	    kbtemp = kbdao.findByLevelAndName(level, name);
	 	   
	 	    if(kbtemp!=null)
	 	    {
	 	    	 this.addFieldError("用户","存在名称，层级完全相同指标，请勿重复添加！");
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
			
			  else if(level.equals("")||level.equals("wu"))
			  {
				 this.addFieldError("用户","层级为空");
			  	 return "failed";
			  }
			
			  /*else if(zu.equals(""))
			  {
				 this.addFieldError("用户","班组为空");
			  	 return "false";
			  }*/
			
	        kb.setName(name);
	        kb.setLevel(level);
	        kb.setTarget(target);
	        kb.setScore(score);
	        kb.setRule(rule);
	 	    kbdao.merge(kb);
	 	    message="添加成功";
	 	    trans.commit();
			session.flush();
			session.clear();
			session.close();
			return "success";
		}
}
