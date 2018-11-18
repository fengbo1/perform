package perform.pdp.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.norm.dao.PKbinormDAO;
import perform.norm.dao.PKcinormDAO;
import perform.norm.dao.PKpinormDAO;
import perform.norm.dao.PKtinormDAO;
import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKcinorm;
import perform.norm.pojo.PKpinorm;
import perform.norm.pojo.PKtinorm;
import perform.pdp.bean.PdpBean;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;
import perform.util.Util;

import ccb.hibernate.HibernateSessionFactory;

public class PDPPerson {
	private String rater;
	private PPosition pp;
	private PUser pu;
	private List<PdpBean> listkpi;
	private List<PdpBean> listkti;
	private List<PdpBean> listkbi;
	private List<PdpBean> listkci;
	
	
	public String getRater() {
		return rater;
	}
	public void setRater(String rater) {
		this.rater = rater;
	}
	public PPosition getPp() {
		return pp;
	}
	public void setPp(PPosition pp) {
		this.pp = pp;
	}
	public PUser getPu() {
		return pu;
	}
	public void setPu(PUser pu) {
		this.pu = pu;
	}
	public List<PdpBean> getListkpi() {
		return listkpi;
	}
	public void setListkpi(List<PdpBean> listkpi) {
		this.listkpi = listkpi;
	}
	public List<PdpBean> getListkti() {
		return listkti;
	}
	public void setListkti(List<PdpBean> listkti) {
		this.listkti = listkti;
	}
	public List<PdpBean> getListkbi() {
		return listkbi;
	}
	public void setListkbi(List<PdpBean> listkbi) {
		this.listkbi = listkbi;
	}
	public List<PdpBean> getListkci() {
		return listkci;
	}
	public void setListkci(List<PdpBean> listkci) {
		this.listkci = listkci;
	}
	public String execute() throws Exception
	{
		PUserDAO pudao = new PUserDAO();
		PKpinormDAO kpindao = new PKpinormDAO();
		PKtinormDAO ktindao = new PKtinormDAO();
		PKbinormDAO kbindao = new PKbinormDAO();
		PKcinormDAO kcindao = new PKcinormDAO();
		PPositionDAO ppdao = new PPositionDAO();
		listkpi = new ArrayList<PdpBean>();
		listkti = new ArrayList<PdpBean>();
		listkbi = new ArrayList<PdpBean>();
		listkci = new ArrayList<PdpBean>();
		Session session = HibernateSessionFactory.getSession();
    	Transaction trans=session.beginTransaction();
    	try {
    		pu = pudao.findByNewNumber(rater);
    		int pnum = pu.getPnum();
    		pp = ppdao.findByID(pnum);
    		if(pp!=null)
    		{
    			String[] kpis = pp.getKpinorm().split("、");
        		String[] ktis = pp.getKtinorm().split("、");
        		String[] kbis = pp.getKbinorm().split("、");
        		String[] kcis = pp.getKcinorm().split("、");
        		
        		String[] kpips = pp.getKpinormprop().split("、");
        		String[] ktips = pp.getKtinormprop().split("、");
        		String[] kbips = pp.getKbinormprop().split("、");
        		String[] kcips = pp.getKcinormprop().split("、");
        		for(int i=0;i<kpis.length;i++)
        		{
        			int tempkpi = Integer.valueOf(kpis[i]);
        			PKpinorm tempkpin = kpindao.findById(tempkpi);
        			if(tempkpin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempkpin.getId());
        				temppb.setName(tempkpin.getName());
        				temppb.setTarget(tempkpin.getTarget());
        				temppb.setScore(tempkpin.getScore());
        				temppb.setRule(tempkpin.getRule());
        				temppb.setPdpname(tempkpin.getPdpname());
        				listkpi.add(temppb);
        			}
        		}
        		for(int i=0;i<ktis.length;i++)
        		{
        			int tempkti = Integer.valueOf(ktis[i]);
        			PKtinorm tempktin = ktindao.findById(tempkti);
        			if(tempktin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempktin.getId());
        				temppb.setName(tempktin.getName());
        				temppb.setTarget(tempktin.getTarget());
        				temppb.setScore(Util.DoubleTo2(tempktin.getScore()*Double.parseDouble(ktips[i])*pp.getKtiprop()));
        				temppb.setRule(tempktin.getRule());
        				listkti.add(temppb);
        			}
        		}
        		for(int i=0;i<kbis.length;i++)
        		{
        			int tempkbi = Integer.valueOf(kbis[i]);
        			PKbinorm tempkbin = kbindao.findById(tempkbi);
        			if(tempkbin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempkbin.getId());
        				temppb.setName(tempkbin.getName());
        				temppb.setTarget(tempkbin.getTarget());
        				temppb.setScore(Util.DoubleTo2(tempkbin.getScore()*Double.parseDouble(kbips[i])*pp.getKbiprop()));
        				temppb.setRule(tempkbin.getRule());
        				listkbi.add(temppb);
        			}
        		}
        		for(int i=0;i<kcis.length;i++)
        		{
        			int tempkci = Integer.valueOf(kcis[i]);
        			PKcinorm tempkcin = kcindao.findById(tempkci);
        			if(tempkcin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempkcin.getId());
        				temppb.setName(tempkcin.getName());
        				temppb.setTarget(tempkcin.getTarget());
        				temppb.setScore(Util.DoubleTo2(tempkcin.getScore()*Double.parseDouble(kcips[i])*pp.getKciprop()));
        				temppb.setRule(tempkcin.getRule());
        				listkci.add(temppb);
        			}
        		}
    		}
    	}catch (Exception e) {
			trans.rollback();//出错回滚
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
