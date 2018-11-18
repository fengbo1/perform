package perform.position.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.userinfo.pojo.PUser;

import ccb.hibernate.HibernateSessionFactory;

public class PosDel_mang {
	private int id;
	private List<PUser>listui;
	private String message;
	
	public List<PUser> getListui() {
		return listui;
	}

	public void setListui(List<PUser> listui) {
		this.listui = listui;
	}

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
		String hql="";
		Query query;
		Session session = HibernateSessionFactory.getSession();
	    Transaction trans = session.beginTransaction();
	    hql = "from PUser as ui where ui.pnum='"+id+"'";
	    hql +=" order by ui.id";
	    System.out.println(hql);
		query = session.createQuery(hql);
		listui = query.list();
		if(listui.size()!=0)
		{
			message="存在员工关联此岗位，要删除请先取消关联！";
			return "failed";
		}
	    String sql = "";
		try {
			sql = "delete from p_position where id="+id;
			session.createSQLQuery(sql).executeUpdate();		
		} catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
		}finally{
			trans.commit();
			session.flush();
			session.clear();
			session.close();
		}
	    message="删除成功";
		return "success";
	}
}
