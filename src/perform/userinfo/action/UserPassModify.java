package perform.userinfo.action;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.opensymphony.xwork2.ActionSupport;
import perform.userinfo.pojo.PUser;
import ccb.hibernate.HibernateSessionFactory;
public class UserPassModify extends ActionSupport implements ServletResponseAware {

	private String newnumber;
	private String oldpassword;
	private String newpassword1;
	private String newpassword2;
	private Session h_session;
    private Transaction trans;
    private String hql;
    private Query query;
    private PUser ui;
    private String message;
    

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



	public String getOldpassword() {
		return oldpassword;
	}



	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}



	public String getNewpassword1() {
		return newpassword1;
	}



	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}



	public String getNewpassword2() {
		return newpassword2;
	}



	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}



	public Session getH_session() {
		return h_session;
	}



	public void setH_session(Session hSession) {
		h_session = hSession;
	}



	public Transaction getTrans() {
		return trans;
	}



	public void setTrans(Transaction trans) {
		this.trans = trans;
	}



	public String getHql() {
		return hql;
	}



	public void setHql(String hql) {
		this.hql = hql;
	}



	public Query getQuery() {
		return query;
	}



	public void setQuery(Query query) {
		this.query = query;
	}



	public PUser getUi() {
		return ui;
	}



	public void setUi(PUser ui) {
		this.ui = ui;
	}



	public String execute() throws Exception
	{
		String hqltemp="";
		try {
		  h_session=HibernateSessionFactory.getSession();
		  trans=h_session.beginTransaction();
		  hql="from PUser as user where user.newnumber=:u";
		  query=h_session.createQuery(hql);
		  query.setString("u",newnumber);//设置查询参数
		//  query.setString("p",password);//设置查询参数
		  System.out.println(newnumber);
          List l=query.list();
          ui =(PUser)(l.get(0));
     		 if(!oldpassword.equals(ui.getPassword()))
     		  {
        			 this.addFieldError("用户","旧密码错误");
       			  	 return "failed";
     		  } 
     		  else if(!newpassword1.equals(newpassword2))
     		  {
     			 this.addFieldError("用户","两次输入的密码不一致");
   			  	 return "failed";
     		  }
     		  else if(newpassword1.length()<6||newpassword1.length()>20)
    		  {
    			 this.addFieldError("密码","密码长度应该在6-20之间");
  			  	 return "failed";
    		  }
     		  else
     		  {
     			 hqltemp = " update PUser as ui set ui.password='"+newpassword2+"' where ui.newnumber="+newnumber;
     			 h_session.createQuery(hqltemp).executeUpdate();
     			 message="修改成功";
     		  }
			} catch (HibernateException e) {
			e.printStackTrace();
			trans.rollback();
			}finally{
				trans.commit();
				h_session.flush();
				h_session.clear();
				h_session.close();
			} 
//        	  HttpSession session=null;
//        	  session.setAttribute("user", this.getUsername());
        	  return "success";
     	  
     	 
	}



	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
}
