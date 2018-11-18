package perform.norm.dao;

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import perform.norm.pojo.PKtinorm;
import perform.seasonrate.pojo.PScore;
/**
 * A data access object (DAO) providing persistence and search support for
 * PKtinorm entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see perform.norm.pojo.PKtinorm
 * @author MyEclipse Persistence Tools
 */

public class PKtinormDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PKtinormDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CHU = "chu";
	public static final String TUAN = "tuan";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";

	public void save(PKtinorm transientInstance) {
		log.debug("saving PKtinorm instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PKtinorm persistentInstance) {
		log.debug("deleting PKtinorm instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PKtinorm findById(java.lang.Integer id) {
		log.debug("getting PKtinorm instance with id: " + id);
		try {
			PKtinorm instance = (PKtinorm) getSession().get(
					"perform.norm.pojo.PKtinorm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PKtinorm instance) {
		log.debug("finding PKtinorm instance by example");
		try {
			List results = getSession().createCriteria(
					"perform.norm.pojo.PKtinorm").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PKtinorm instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PKtinorm as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByChu(Object chu) {
		return findByProperty(CHU, chu);
	}

	public List findByTuan(Object tuan) {
		return findByProperty(TUAN, tuan);
	}

	public List findByTarget(Object target) {
		return findByProperty(TARGET, target);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findByRule(Object rule) {
		return findByProperty(RULE, rule);
	}

	public List findAll() {
		log.debug("finding all PKtinorm instances");
		try {
			String queryString = "from PKtinorm";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PKtinorm merge(PKtinorm detachedInstance) {
		log.debug("merging PKtinorm instance");
		try {
			PKtinorm result = (PKtinorm) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PKtinorm instance) {
		log.debug("attaching dirty PKtinorm instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PKtinorm instance) {
		log.debug("attaching clean PKtinorm instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	 public PKtinorm findByChuAndTuanAndName(String chu,String tuan,String name) {
	    	log.debug("finding all PKtinorm instances");
	    	try {
	    		String queryString = "from PKtinorm as kt where kt.chu='"+chu+"' and kt.tuan='"+tuan+"' and kt.name='"+name+"'";
	             Query queryObject = getSession().createQuery(queryString);
	    		 List list = queryObject.list();
	    		 if(list.isEmpty())
	    		 {
	    			 return null;
	    		 }
	    		 else
	    		 {
	    			 return (PKtinorm)list.get(0);
	    		 }
	              
	    	} catch (RuntimeException re) {
	    		log.error("find all failed", re);
	    		throw re;
	    	}
	    }
}