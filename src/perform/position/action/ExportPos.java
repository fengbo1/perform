package perform.position.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.position.bean.PositionBean;
import perform.position.pojo.PPosition;
import perform.userinfo.bean.UserBean;
import perform.userinfo.pojo.PUser;
import perform.util.ExportExcel;
import perform.util.UserUtil;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class ExportPos {
	private String Path;
	private String chu;

	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getChu() {
		return chu;
	}
	public void setChu(String chu) {
		this.chu = chu;
	}
	public String execute() throws Exception{
		String filePath = "";
		String hql = "";
		List<PPosition> list;
		List<PositionBean> listpb = new ArrayList<PositionBean>();
		ExportExcel<PositionBean> ex = new ExportExcel<PositionBean>();
		String[] headers =  {"岗位编号","岗位名称","所属处室","所属团队",
				"kpi指标权重","kti指标权重","kbi指标权重","kci指标权重"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PPosition as pp";
		if(!chu.equals("all"))
		{
			hql+=" where pp.chu='"+chu+"'";
		}
		hql+= " order by pp.id";
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		for(int i=0;i<list.size();i++)
		{
			PPosition pp = list.get(i);
			PositionBean pb = new PositionBean();
			pb.setId(String.valueOf(pp.getId()));
			pb.setName(pp.getName());
			pb.setChu(UserUtil.positionToChu(pp.getChu()));
			pb.setTuan(UserUtil.positionToTuan(pp.getChu()+pp.getTuan()));
			pb.setKpip(String.valueOf(pp.getKpiprop()));
			pb.setKtip(String.valueOf(pp.getKtiprop()));
			pb.setKbip(String.valueOf(pp.getKbiprop()));
			pb.setKcip(String.valueOf(pp.getKciprop()));
			listpb.add(pb);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"gangweimingxi.xls";
				Path = "gangweimingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("岗位明细表",headers, listpb, out);
				out.close();
				System.out.println("excel导出成功！");
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		return "success";
	}
}
