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
import perform.norm.bean.KbinormBean;
import perform.norm.pojo.PKbinorm;
import perform.util.ExportExcel;
import perform.util.UserUtil;
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class ExportKbiNorm {
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
		List<PKbinorm> list;
		List<KbinormBean> listkb = new ArrayList<KbinormBean>();
		ExportExcel<KbinormBean> ex = new ExportExcel<KbinormBean>();
		String[] headers =  {"指标编号","所属层级","指标名称","目标值",
				"分值","考核规则"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PKbinorm as pk order by pk.id";
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		for(int i=0;i<list.size();i++)
		{
			PKbinorm pk = list.get(i);
			KbinormBean kb = new KbinormBean();
			kb.setId(String.valueOf(pk.getId()));
			kb.setLevel(UserUtil.positionToZhi(pk.getLevel()));
			kb.setName(pk.getName());
			kb.setTarget(pk.getTarget());
			kb.setScore(String.valueOf(pk.getScore()));
			kb.setRule(pk.getRule());
			listkb.add(kb);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"kbizhibiaomingxi.xls";
				Path = "kbizhibiaomingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("kbi指标明细",headers, listkb, out);
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
