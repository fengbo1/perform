package perform.seasonquery.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import perform.seasonquery.bean.PScoreBean;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PScore;
import perform.util.ExportExcel;
import perform.util.Util;

import ccb.hibernate.HibernateSessionFactory;

public class ExportPscore {

	private int year;
	private int season;
	private String chu;
	private String Path;
	private String type;//kpi/all
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public String getChu() {
		return chu;
	}
	public void setChu(String chu) {
		this.chu = chu;
	}

	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String execute() throws Exception{
		String filePath = "";
		String hql = "";
		
		PScoreDAO psdao = new PScoreDAO();
		List<PScore> list;
		List<PScoreBean> listpsb = new ArrayList<PScoreBean>();
		ExportExcel<PScoreBean> ex = new ExportExcel<PScoreBean>();
		String[] headers = {"年","季度","新一代编号","姓名",
				"处室","团队","班组","岗位","关键业务指标占比","关键业务指标得分",
				"关键任务目标占比","关键任务目标得分","品能目标占比","品能目标得分","加分项占比","加分项","总得分"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		hql = "from PScore as ps where ps.year='"+year+"' and ps.season='"+season+"'";
		if(type.equals("office"))
		{
			chu = new String(chu.getBytes("ISO8859-1"),"UTF-8");
			hql += " and ps.positionchu='"+chu+"'";
		}
		System.out.println(hql);
		query = session.createQuery(hql);
		list = query.list();
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		
		for(int i=0;i<list.size();i++)
		{
			PScore ps = list.get(i);
			PScoreBean psb = new PScoreBean();
			psb.setYear(String.valueOf(ps.getYear()));
			psb.setSeason(String.valueOf(ps.getSeason()));
			psb.setNewnumber(ps.getNewnumber());
			psb.setName(ps.getName());
			psb.setChu(ps.getPositionchu());
			psb.setTuan(ps.getPositiontuan());
			psb.setZu(ps.getPositionzu());
			psb.setPosition(ps.getPositionname());
			psb.setKpiprop(String.valueOf(ps.getKpiprop()));
			psb.setKpiscore(String.valueOf(ps.getKpiscore()));
			if(type.equals("kpi"))
			{
				psb.setKtiprop("-");
				psb.setKtiscore("-");
				psb.setKbiprop("-");
				psb.setKbiscore("-");
				psb.setKciprop("-");
				psb.setKciscore("-");
			}
			else
			{
				psb.setKtiprop(String.valueOf(ps.getKtiprop()));
				psb.setKtiscore(String.valueOf(ps.getKtiscore()));
				psb.setKbiprop(String.valueOf(ps.getKbiprop()));
				psb.setKbiscore(String.valueOf(ps.getKbiscore()));
				psb.setKciprop(String.valueOf(ps.getKciprop()));
				psb.setKciscore(String.valueOf(ps.getKciscore()));
			}
			psb.setScore(String.valueOf(ps.getScore()));
			listpsb.add(psb);
		}
		//导出
		 try {
			 	filePath=Util.downloadpath+"pdp_"+type+"_"+year+"_"+season+".xls";
				Path = "pdp_"+type+"_"+year+"_"+season+".xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("员工绩效明细",headers, listpsb, out);
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
