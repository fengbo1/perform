package perform.position.action;

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
import perform.util.Util;
import ccb.hibernate.HibernateSessionFactory;

public class PosShow_mang {
	private int pnum;
	private PPosition pp;
	private List<PdpBean> listkpi;
	private List<PdpBean> listkti;
	private List<PdpBean> listkbi;
	private List<PdpBean> listkci;
	

	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public PPosition getPp() {
		return pp;
	}
	public void setPp(PPosition pp) {
		this.pp = pp;
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
    		
    		pp = ppdao.findByID(pnum);
    		if(pp!=null)
    		{
    			String[] kpis = pp.getKpinorm().split("、");
        		String[] ktis = pp.getKtinorm().split("、");
        		String[] kbis = pp.getKbinorm().split("、");
        		String[] kcis = pp.getKcinorm().split("、");
        		
        		String[] ktips = pp.getKtinormprop().split("、");
        		String[] kbips = pp.getKbinormprop().split("、");
        		for(int i=0;i<kpis.length;i++)
        		{
        			int tempkpi = Integer.valueOf(kpis[i]);
        			PKpinorm tempkpin = kpindao.findAllById(tempkpi);
        			if(tempkpin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempkpin.getId());
        				temppb.setPdpname(tempkpin.getPdpname());
        				temppb.setTarget(tempkpin.getTarget());
        				temppb.setScore(tempkpin.getScore());
        				temppb.setRule(tempkpin.getRule());
        				temppb.setRemark(tempkpin.getRemark());
        				listkpi.add(temppb);
        			}
        		}
        		for(int i=0;i<ktis.length;i++)
        		{
        			int tempkti = Integer.valueOf(ktis[i]);
        			PKtinorm tempktin = ktindao.findAllById(tempkti);
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
        			PKbinorm tempkbin = kbindao.findAllById(tempkbi);
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
        			PKcinorm tempkcin = kcindao.findAllById(tempkci);
        			if(tempkcin!=null)
        			{
        				PdpBean temppb = new PdpBean();
        				temppb.setId(tempkcin.getId());
        				temppb.setName(tempkcin.getName());
        				temppb.setTarget(tempkcin.getTarget());
        				temppb.setScore("");
        				temppb.setRule(tempkcin.getRule());
        				temppb.setRemark(tempkcin.getRemark());
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
