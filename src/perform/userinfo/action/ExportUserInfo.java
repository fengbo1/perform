package perform.userinfo.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.bean.KbinormBean;
import perform.norm.pojo.PKbinorm;
import perform.userinfo.bean.UserBean;
import perform.userinfo.pojo.PUser;
import perform.util.ExportExcel;
import perform.util.UserUtil;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class ExportUserInfo {
	private String Path;

	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String execute() throws Exception{
		String filePath = "";
		String hql = "";
		List<PUser> list;
		List<UserBean> listub = new ArrayList<UserBean>();
		ExportExcel<UserBean> ex = new ExportExcel<UserBean>();
		String[] headers =  {"新一代编号","用户名","密码","中心",
				"职务","处室","团队","班组","权限","岗位"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PUser as pu order by pu.position";
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		for(int i=0;i<list.size();i++)
		{
			PUser pu = list.get(i);
			UserBean ub = new UserBean();
			ub.setNewnumber(pu.getNewnumber());
			ub.setName(pu.getName());
			ub.setPassword(pu.getPassword());
			ub.setZx(UserUtil.positionToZX(pu.getPosition()));
			ub.setZhi(UserUtil.positionToZhi(pu.getPosition()));
			ub.setChu(UserUtil.positionToChu(pu.getPosition()));
			ub.setTuan(UserUtil.positionToTuan(pu.getPosition()));
			ub.setZu(UserUtil.positionToZu(pu.getPosition()));
			ub.setAutho(pu.getAutho());
			ub.setPnum(UserUtil.pnumToName(session, pu.getPnum()));
			
			listub.add(ub);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"yuangongmingxi.xls";
				Path = "yuangongmingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("员工明细表",headers, listub, out);
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
