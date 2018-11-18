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

import ccb.hibernate.HibernateSessionFactory;

import perform.norm.bean.KpinormBean;
import perform.norm.dao.PKpinormDAO;
import perform.norm.pojo.PKpinorm;
import perform.util.ExportExcel;
import perform.util.Util;

public class ExportKpiNorm {
	
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
		List<PKpinorm> list;
		List<KpinormBean> listkb = new ArrayList<KpinormBean>();
		ExportExcel<KpinormBean> ex = new ExportExcel<KpinormBean>();
		String[] headers =  {"指标编号","所属层级","指标名称","目标值",
				"分值","考核规则"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PKpinorm as pk order by pk.id";
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		for(int i=0;i<list.size();i++)
		{
			PKpinorm pk = list.get(i);
			KpinormBean kb = new KpinormBean();
			kb.setId(String.valueOf(pk.getId()));
			kb.setName(pk.getName());
			kb.setPdpname(pk.getPdpname());
			kb.setTarget(pk.getTarget());
			kb.setScore(pk.getScore());
			kb.setRule(pk.getRule());
			listkb.add(kb);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"kpizhibiaomingxi.xls";
				Path = "kpizhibiaomingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("kpi指标明细",headers, listkb, out);
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
