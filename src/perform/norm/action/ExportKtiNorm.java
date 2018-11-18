package perform.norm.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.norm.bean.KtinormBean;
import perform.norm.pojo.PKtinorm;
import perform.util.ExportExcel;
import perform.util.UserUtil;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class ExportKtiNorm {
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
		List<PKtinorm> list;
		List<KtinormBean> listkb = new ArrayList<KtinormBean>();
		ExportExcel<KtinormBean> ex = new ExportExcel<KtinormBean>();
		String[] headers =  {"指标编号","指标名称","所属处室","所属团队","目标值",
				"分值","考核规则"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PKtinorm as pk order by pk.id";
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		for(int i=0;i<list.size();i++)
		{
			PKtinorm pk = list.get(i);
			KtinormBean kb = new KtinormBean();
			kb.setId(String.valueOf(pk.getId()));
			kb.setName(pk.getName());
			kb.setChu(UserUtil.positionToChu(pk.getChu()));
			kb.setTuan(UserUtil.positionToTuan(pk.getChu()+pk.getTuan()));
			kb.setTarget(pk.getTarget());
			kb.setScore(String.valueOf(pk.getScore()));
			kb.setRule(pk.getRule());
			listkb.add(kb);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"ktizhibiaomingxi.xls";
				Path = "ktizhibiaomingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("kti指标明细",headers, listkb, out);
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
