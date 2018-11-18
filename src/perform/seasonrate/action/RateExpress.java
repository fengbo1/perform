package perform.seasonrate.action;

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
import perform.seasonrate.bean.UnRatePeople;
import perform.seasonrate.dao.PKBIScoreDAO;
import perform.seasonrate.dao.PKCIScoreDAO;
import perform.seasonrate.dao.PKPIScoreDAO;
import perform.seasonrate.dao.PKTIScoreDAO;
import perform.seasonrate.pojo.PKBIScore;
import perform.seasonrate.pojo.PKCIScore;
import perform.seasonrate.pojo.PKTIScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.ExportExcel;
import perform.util.UserUtil;
import perform.util.Util;

/**
 * 导出未评分人员名单
 * @author htzx
 *
 */
public class RateExpress {
	private String Path;
	private int year;
	private int season;
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
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
	public String execute() throws Exception{
		String filePath = "";
		String hql = "";
		String sql = "";
		PUserDAO pudao = new PUserDAO();
		List<PKTIScore> listktiA = new ArrayList<PKTIScore>();
		List<PKBIScore> listkbiA = new ArrayList<PKBIScore>();
		List<PKCIScore> listkciA = new ArrayList<PKCIScore>();
		List<PKTIScore> listktiB = new ArrayList<PKTIScore>();
		List<PKBIScore> listkbiB = new ArrayList<PKBIScore>();
		List<PKCIScore> listkciB = new ArrayList<PKCIScore>();
		List<PKTIScore> listktiC = new ArrayList<PKTIScore>();
		List<PKBIScore> listkbiC = new ArrayList<PKBIScore>();
		List<PKCIScore> listkciC = new ArrayList<PKCIScore>();
		List<UnRatePeople> listurp = new ArrayList<UnRatePeople>();
		ExportExcel<UnRatePeople> ex = new ExportExcel<UnRatePeople>();
		String[] headers =  {"处室","姓名","未评分指标","未评分人姓名"};
		Query query;
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
//		sql = "select distinct(newnumber) from p_ktiscore a where a.year='"+year+"' and a.season='"+season+"' and ((a.rater1 is not NULL and a.result1=0) or (a.rater2 is not NULL and a.result2=0) or (a.rater3 is not NULL and a.result3=0))";
//		listkti = session.createSQLQuery(sql).list();
//		sql = "select distinct(newnumber) from p_kbiscore a where a.year='"+year+"' and a.season='"+season+"' and ((a.rater1 is not NULL and a.result1=0) or (a.rater2 is not NULL and a.result2=0) or (a.rater3 is not NULL and a.result3=0))";
//		listkbi = session.createSQLQuery(sql).list();
//		sql = "select distinct(newnumber) from p_kciscore a where a.year='"+year+"' and a.season='"+season+"' and ((a.rater1 is not NULL and a.result1=0) or (a.rater2 is not NULL and a.result2=0) or (a.rater3 is not NULL and a.result3=0))";
//		listkci = session.createSQLQuery(sql).list();
		
		sql = "select * from p_ktiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater1 is not NULL and a.result1=0) group by a.newnumber";
		listktiA = session.createSQLQuery(sql).addEntity(PKTIScore.class).list();
		sql = "select * from p_kbiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater1 is not NULL and a.result1=0) group by a.newnumber";
		listkbiA = session.createSQLQuery(sql).addEntity(PKBIScore.class).list();
		sql = "select * from p_kciscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater1 is not NULL and a.result1=0) group by a.newnumber";
		listkciA = session.createSQLQuery(sql).addEntity(PKCIScore.class).list();
		sql = "select * from p_ktiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater2 is not NULL and a.result2=0) group by a.newnumber";
		listktiB = session.createSQLQuery(sql).addEntity(PKTIScore.class).list();
		sql = "select * from p_kbiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater2 is not NULL and a.result2=0) group by a.newnumber";
		listkbiB = session.createSQLQuery(sql).addEntity(PKBIScore.class).list();
		sql = "select * from p_kciscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater2 is not NULL and a.result2=0) group by a.newnumber";
		listkciB = session.createSQLQuery(sql).addEntity(PKCIScore.class).list();
		sql = "select * from p_ktiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater3 is not NULL and a.result3=0) group by a.newnumber";
		listktiC = session.createSQLQuery(sql).addEntity(PKTIScore.class).list();
		sql = "select * from p_kbiscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater3 is not NULL and a.result3=0) group by a.newnumber";
		listkbiC = session.createSQLQuery(sql).addEntity(PKBIScore.class).list();
		sql = "select * from p_kciscore a where a.year='"+year+"' and a.season='"+season+"' and (a.rater3 is not NULL and a.result3=0) group by a.newnumber";
		listkciC = session.createSQLQuery(sql).addEntity(PKCIScore.class).list();
		
		//A
		for(int i=0;i<listktiA.size();i++)
		{
			PKTIScore temp = listktiA.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kti");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater1()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkbiA.size();i++)
		{
			PKBIScore temp = listkbiA.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kbi");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater1()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkciA.size();i++)
		{
			PKCIScore temp = listkciA.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kci");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater1()));
			}
			listurp.add(urp);
		}
		//B
		for(int i=0;i<listktiB.size();i++)
		{
			PKTIScore temp = listktiB.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kti");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater2()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkbiB.size();i++)
		{
			PKBIScore temp = listkbiB.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kbi");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater2()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkciB.size();i++)
		{
			PKCIScore temp = listkciB.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kci");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater2()));
			}
			listurp.add(urp);
		}
		//C
		for(int i=0;i<listktiC.size();i++)
		{
			PKTIScore temp = listktiC.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kti");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater3()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkbiC.size();i++)
		{
			PKBIScore temp = listkbiC.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kbi");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater3()));
			}
			listurp.add(urp);
		}
		for(int i=0;i<listkciC.size();i++)
		{
			PKCIScore temp = listkciC.get(i);
			UnRatePeople urp = new UnRatePeople();
			PUser pu = pudao.findByNewNumber(temp.getNewnumber());
			if(pu!=null)
			{
				urp.setChu(UserUtil.positionToChu(pu.getPosition()));
				urp.setName(pu.getName());
				urp.setType("kci");
				urp.setUnrater(UserUtil.newnumberToName(session, temp.getRater3()));
			}
			listurp.add(urp);
		}
		
		trans.commit();
		session.flush();
		session.clear();
		session.close();
		
		
		//导出
		 try {
			 	filePath=Util.downloadpath+"weidafenmingxi.xls";
				Path = "weidafenmingxi.xls";
				OutputStream out = new FileOutputStream(filePath);
				ex.exportExcel("未打分人员明细",headers, listurp, out);
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
