package perform.norm.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.pojo.PPosition;
import ccb.hibernate.HibernateSessionFactory;

public class KPIDel_mang {
	private int id;
	private String message;
	private List<PPosition> listpos;
	
	public List<PPosition> getListpos() {
		return listpos;
	}

	public void setListpos(List<PPosition> listpos) {
		this.listpos = listpos;
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
	    hql = "from PPosition as pos where pos.id>0";
	    hql +=" order by pos.id";
		System.out.println(hql);
		query = session.createQuery(hql);
		listpos = query.list();
		PPosition pos =new PPosition();
		String kpinorm = "";
		int flag=0;
		if(listpos!=null)
		{		
		for(int i=0;i<listpos.size();i++)
		{
			pos=listpos.get(i);
			kpinorm=pos.getKpinorm();
			String temp[]=kpinorm.split("、");
			for(int j=0;j<temp.length;j++)
			{
				int result = Integer.parseInt(temp[j]);
				if(result==id)
				{
					flag=1;
					break;
				}
			}
			if(flag==1)
			{
				break;
			}
		}
		}
		if(flag==1)
		{
			message="存在岗位关联此指标，要删除请先取消关联！";
			return "failed";
		}
	    String sql = "";
		try {
			sql = "delete from p_kpinorm where id="+id;
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
