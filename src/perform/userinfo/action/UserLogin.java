package perform.userinfo.action;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.userinfo.pojo.PUser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import ccb.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserLogin extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
    private Transaction trans;
    private String hql;
    private Query query;
    private int errNum = 0;
    private int restNum;
    
    Timestamp d = new Timestamp(System.currentTimeMillis());
    

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
   public void setServletResponse(HttpServletResponse response)
       {
       }

	public String execute() throws Exception
	{
	/*固定写法*/
		  int views = 0;//访问量统计
		  PFlagDAO pfdao = new PFlagDAO();
		  Session h_session=HibernateSessionFactory.getSession();
		  trans=h_session.beginTransaction();
		  //username=new String(username.getBytes("ISO8859-1"),"UTF-8");
		  hql="from PUser as user where user.name=:u";
		  query=h_session.createQuery(hql);
		  query.setString("u",username);//设置查询参数
		  System.out.println(username+":"+hql);
          List l=query.list();
     	  if((l==null)||(l.size()<=0))
     	  {
     		  this.addFieldError("用户","用户不存在!");
			  return "failed";
     	  }
     	  else 
     	  {
     		  PUser u=(PUser)(l.get(0));
     		  id=u.getId();
     		  System.out.print("username:"+username);
     		  System.out.print("password:"+password);
     		  System.out.print("DBpwd:"+u.getPassword());
     		 if(ActionContext.getContext().getSession().get(username) == null)
 			 {
 				 errNum = 0;
 			 }
 			 else{
 				 errNum=(Integer)ActionContext.getContext().getSession().get(username);
 			 }
 			 System.out.print(ActionContext.getContext().getSession().get(username));
 			 if(errNum>=5)
 			 {
 				this.addFieldError("用户","该用户已经被锁定,请找管理员解锁");
				return "failed";
 			 }
     		  if(!password.equals(u.getPassword()))
     		  {
     			 if(errNum==0)
        		  {
        			 errNum = errNum+1;
        			 ActionContext.getContext().getSession().put(username,errNum);  
        			 this.addFieldError("用户","密码输入错误");
       			  	 return "failed";
        		  }
     			 else 
     			 {
     					restNum = 5-errNum;
     					this.addFieldError("用户","你已经输入错误"+errNum+"次，还有"+restNum+"次");
     					errNum = errNum+1;
     					ActionContext.getContext().getSession().put(username,errNum);  
     					return "failed";
     			 }
     			
     		  }
     		  
     		  
     		  System.out.print("time1"+d);
        	  ActionContext.getContext().getSession().put("username",u.getName());
        	  ActionContext.getContext().getSession().put("newnumber",u.getNewnumber());
        	  ActionContext.getContext().getSession().put("zhis",u.getPosition().substring(0,1));//0武汉1成都
        	  ActionContext.getContext().getSession().put("paixus",u.getPosition().substring(1,2));//30303
        	  ActionContext.getContext().getSession().put("chus",u.getPosition().substring(2,3));//30303
        	  ActionContext.getContext().getSession().put("tuans",u.getPosition().substring(3,4));//30303
        	  ActionContext.getContext().getSession().put("zus",u.getPosition().substring(4,5));//30303
//        	  ActionContext.getContext().getSession().put("citys",u.getPosition().substring(5,6));//30303
        	  ActionContext.getContext().getSession().put("canscores",u.getCanscore());//30303
        	  ActionContext.getContext().getSession().put("authos",u.getAutho());
        	  ActionContext.getContext().getSession().put("authoA",u.getAutho().substring(0,1));
        	  ActionContext.getContext().getSession().put("authoB",u.getAutho().substring(1,2));
        	  ActionContext.getContext().getSession().put("authoC",u.getAutho().substring(2,3));
        	  ActionContext.getContext().getSession().put("authoD",u.getAutho().substring(3,4));
        	  ActionContext.getContext().getSession().put("authoE",u.getAutho().substring(4,5));
        	  ActionContext.getContext().getSession().put("authoF",u.getAutho().substring(5,6));
        	  ActionContext.getContext().getSession().put("authoG",u.getAutho().substring(6,7));
        	  ActionContext.getContext().getSession().put("authoH",u.getAutho().substring(7,8));
        	  ActionContext.getContext().getSession().put("authoI",u.getAutho().substring(8,9));
        	  ActionContext.getContext().getSession().put("authoJ",u.getAutho().substring(9,10));
        	  ActionContext.getContext().getSession().put("authoK",u.getAutho().substring(10,11));
        	  ActionContext.getContext().getSession().put("authoL",u.getAutho().substring(11,12));
        	  ActionContext.getContext().getSession().put("authoM",u.getAutho().substring(12,13));
        	  ActionContext.getContext().getSession().put("authoN",u.getAutho().substring(13,14));
        	  ActionContext.getContext().getSession().put("authoO",u.getAutho().substring(14,15));
        	  ActionContext.getContext().getSession().put("authoP",u.getAutho().substring(15,16));
        	  ActionContext.getContext().getSession().put("authoQ",u.getAutho().substring(16,17));
        	  ActionContext.getContext().getSession().put("authoR",u.getAutho().substring(17,18));
        	  ActionContext.getContext().getSession().put("authoS",u.getAutho().substring(18,19));
        	  ActionContext.getContext().getSession().put("authoT",u.getAutho().substring(19,20));
        	  ActionContext.getContext().getSession().put("authoU",u.getAutho().substring(20,21));
        	  ActionContext.getContext().getSession().put("authoV",u.getAutho().substring(21,22));
        	  ActionContext.getContext().getSession().put("authoW",u.getAutho().substring(22,23));
        	  ActionContext.getContext().getSession().put("authoX",u.getAutho().substring(23,24));
        	  ActionContext.getContext().getSession().put("authoY",u.getAutho().substring(24,25));
        	  ActionContext.getContext().getSession().put("authoZ",u.getAutho().substring(25,26));
        	  ActionContext.getContext().getSession().put("ids",id);
        	  
        	  //访问量统计
//        	  HttpSession session=null;
//        	  session.setAttribute("user", this.getUsername());
        	  trans.commit();
        	  h_session.flush();
        	  h_session.clear();
        	  h_session.close();
        	  return "success";
     	  }
     	 
	}
	
	
}
