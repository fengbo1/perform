package perform.userinfo.action;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;
public class UserUpdate extends ActionSupport implements ServletResponseAware {
	    
	    private String newnumber;
	    private String name;
	    private String city;
	    private String zhiwu;
	    private String chu;
	    private String tuan;
	    private String zu;
	    private String role;
	    private int canscore;
	    private int pos;
	    private String message;
	    private int paraid;
	   
		public int getCanscore() {
			return canscore;
		}

		public void setCanscore(int canscore) {
			this.canscore = canscore;
		}

		public int getParaid() {
			return paraid;
		}

		public void setParaid(int paraid) {
			this.paraid = paraid;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}


		public String getNewnumber() {
			return newnumber;
		}

		public void setNewnumber(String newnumber) {
			this.newnumber = newnumber;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	

		public String getZhiwu() {
			return zhiwu;
		}

		public void setZhiwu(String zhiwu) {
			this.zhiwu = zhiwu;
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

		public String getZu() {
			return zu;
		}

		public void setZu(String zu) {
			this.zu = zu;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}


		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			
		}
		public String execute() throws Exception
		{
			PUserDAO uidao=new PUserDAO();
			PUser ui =new PUser();
			PUser uitemp = new PUser();
			Session session = HibernateSessionFactory.getSession();
	 	    Transaction trans = session.beginTransaction();
	 	    uitemp=uidao.findByNameId(name,paraid);
	 	    ui= uidao.findAllById(paraid);
	 	   if((chu==null||chu.equals("wu"))&&(zhiwu.equals("0")||zhiwu.equals("1")))
		    {
		    	chu="z";
		    }
	 	   if((tuan==null||tuan.equals("wu"))&&(zhiwu.equals("0")||zhiwu.equals("1")))
		    {
		    	tuan="z";
		    }
	 	   if(zu==null||zu.equals("wu"))
	 	    {
	 	    	zu="0";
	 	    }
	 	    if(ui==null)
	 	    {
	 	    	this.addFieldError("用户","修改错误");
			  	 return "failed";
	 	    }
			if(uitemp!=null)
			{
				this.addFieldError("用户","姓名已存在");
			  	 return "failed";
			}
			uitemp=uidao.findByNewNumberId(newnumber,paraid);
			if(uitemp!=null)
			{
				this.addFieldError("用户","新一代编号已存在");
			  	 return "failed";
			}
	 	    if(newnumber==null||newnumber.equals(""))
			  {
	 			 this.addFieldError("用户","新一代编号为空");
				  	 return "failed";
			  } 
			  else if(name==null||name.equals(""))
			  {
				 this.addFieldError("用户","姓名为空");
			  	 return "failed";
			  }
			 
			  else if(city==null||city.equals("")||city.equals("wu"))
			  {
				 this.addFieldError("用户","所属中心为空");
			  	 return "failed";
			  }
			
			  else if(zhiwu==null||zhiwu.equals("")||zhiwu.equals("wu"))
			  {
				 this.addFieldError("用户","职务为空");
			  	 return "failed";
			  }
			  else if((chu==null||chu.equals("")||chu.equals("wu"))&&!zhiwu.equals("0")&&!zhiwu.equals("1"))
			  {
				 this.addFieldError("用户","处室为空");
			  	 return "failed";
			  }
			  else if((tuan==null||tuan.equals("")||tuan.equals("wu"))&&!zhiwu.equals("0")&&!zhiwu.equals("1"))
			  {
				 this.addFieldError("用户","团队为空");
			  	 return "failed";
			  }
			  /*else if(zu.equals(""))
			  {
				 this.addFieldError("用户","班组为空");
			  	 return "false";
			  }*/
			  else if(role==null||role.equals("")||role.equals("wu"))
			  {
				 this.addFieldError("用户","角色权限为空");
			  	 return "failed";
			  }
			  else if(pos==0)
			  {
				 this.addFieldError("用户","岗位为空");
			  	 return "failed";
			  }
	 	    String position="";
	 	   
	 	    position=zhiwu+"0"+chu+tuan+zu;
	 	    ui.setNewnumber(newnumber);
	 	    ui.setName(name);
	 	    ui.setCanscore(canscore);
	 	    ui.setPosition(position);
	 	    ui.setAutho(roletoautho(role));
	 	    ui.setPnum(pos);
	 	    uidao.merge(ui);
	 	    message="修改成功";
	 	    trans.commit();
			session.flush();
			session.clear();
			session.close();
			return "success";
		}
		 public static String roletoautho(String role)
		    {
			 	String autho="";
		    	autho = Util.autho.substring(0, Util.AUTHO.indexOf("E"))+"E"+ Util.autho.substring(Util.AUTHO.indexOf("E")+1,Util.autho.length());
		    	autho = autho.substring(0, Util.AUTHO.indexOf(role))+role+ autho.substring(Util.AUTHO.indexOf(role)+1,Util.autho.length());
		    	
		    	return autho;
		    }

}
