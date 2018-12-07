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
public class UserAdd_mang extends ActionSupport implements ServletResponseAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newnumber;
    private String name;
    private String password;
    private String zhiwu;
    private String chu;
    private String tuan;
    private String zu;
    private String role;
    private int canscore;
    private int pos;
    private String message;
    
    

	public int getCanscore() {
		return canscore;
	}

	public void setCanscore(int canscore) {
		this.canscore = canscore;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
 		uitemp=uidao.findByName(name);
		if(uitemp!=null)
		{
			this.addFieldError("用户","姓名已存在");
		  	 return "failed";
		}
		uitemp=uidao.findByNewNumber(newnumber);
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
		  else if(password==null||password.equals(""))
		  {
			 this.addFieldError("用户","密码为空");
		  	 return "failed";
		  }
		  else if(password.length()<6||password.length()>20)
		  {
			 this.addFieldError("密码","密码长度应该在6-20之间");
		  	 return "failed";
		  }
		  else if(zhiwu.equals("wu")||zhiwu.equals(""))
		  {
			 this.addFieldError("用户","职务为空");
		  	 return "failed";
		  }
		  else if((chu.equals("wu")||chu.equals(""))&&!zhiwu.equals("0")&&!zhiwu.equals("1"))
		  {
			 this.addFieldError("用户","处室为空");
		  	 return "failed";
		  }
		  else if((tuan.equals("wu")||tuan.equals(""))&&!zhiwu.equals("0")&&!zhiwu.equals("1"))
		  {
			 this.addFieldError("用户","团队为空");
		  	 return "failed";
		  }
		  /*else if(zu.equals(""))
		  {
			 this.addFieldError("用户","班组为空");
		  	 return "false";
		  }*/
		  else if(role.equals("wu")||role.equals(""))
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
 	   if(chu==null||chu.equals("wu")||chu.equals(""))
	    {
	    	chu="z";
	    }
 	   if(tuan==null||tuan.equals("wu")||tuan.equals(""))
	    {
	    	tuan="z";
	    }
 	    if(zu==null||zu.equals("wu")||zu.equals(""))
 	    {
 	    	zu="0";
 	    }
 	    position=zhiwu+"0"+chu+tuan+zu;
 	    ui.setNewnumber(newnumber);
 	    ui.setName(name);
 	    ui.setCanscore(canscore);
 	    ui.setPassword(password);
 	    ui.setPosition(position);
 	    ui.setAutho(roletoautho(role));
 	    ui.setPnum(pos);
 	    uidao.merge(ui);
 	    message="添加成功";
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
