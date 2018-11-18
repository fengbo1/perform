package perform.userinfo.action;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import ccb.hibernate.HibernateSessionFactory;
public class UserDel_mang {
	private int id;
	private String message;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() throws Exception
	{	
		Session session = HibernateSessionFactory.getSession();
	    Transaction trans = session.beginTransaction();
	    String sql = "";
		try {
			sql = "delete from p_user where id="+id;
			session.createSQLQuery(sql).executeUpdate();	
			message="删除成功";
		} catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
		}finally{
			trans.commit();
			session.flush();
			session.clear();
			session.close();
		}
	
		return "success";
	}
}
