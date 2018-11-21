package perform.norm.dao;

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKpinorm;
import perform.norm.pojo.PKtinorm;
/**
 * A data access object (DAO) providing persistence and search support for
 * PKbinorm entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see perform.norm.pojo.PKbinorm
 * @author MyEclipse Persistence Tools
 */

public class PKbinormDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PKbinormDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";

	public void save(PKbinorm transientInstance) {
		log.debug("saving PKbinorm instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PKbinorm persistentInstance) {
		log.debug("deleting PKbinorm instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PKbinorm findById(java.lang.Integer id) {
		log.debug("getting PKbinorm instance with id: " + id);
		try {
			PKbinorm instance = (PKbinorm) getSession().get(
					"perform.norm.pojo.PKbinorm", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PKbinorm instance) {
		log.debug("finding PKbinorm instance by example");
		try {
			List results = getSession().createCriteria(
					"perform.norm.pojo.PKbinorm").add(Example.create(instance))
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
		log.debug("finding PKbinorm instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PKbinorm as model where model."
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

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
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
		log.debug("finding all PKbinorm instances");
		try {
			String queryString = "from PKbinorm";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PKbinorm merge(PKbinorm detachedInstance) {
		log.debug("merging PKbinorm instance");
		try {
			PKbinorm result = (PKbinorm) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PKbinorm instance) {
		log.debug("attaching dirty PKbinorm instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PKbinorm instance) {
		log.debug("attaching clean PKbinorm instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	 public PKbinorm findByLevelAndName(String name) {
	    	log.debug("finding all PKtinorm instances");
	    	try {
	    		String queryString = "from PKbinorm as kb where kb.name='"+name+"'";
	             Query queryObject = getSession().createQuery(queryString);
	    		 List list = queryObject.list();
	    		 if(list.isEmpty())
	    		 {
	    			 return null;
	    		 }
	    		 else
	    		 {
	    			 return (PKbinorm)list.get(0);
	    		 }
	              
	    	} catch (RuntimeException re) {
	    		log.error("find all failed", re);
	    		throw re;
	    	}
	    }
	 
	 public PKbinorm findAllById(int id) {
			log.debug("finding all PKbinorm instances");
			try {
				String queryString = "from PKbinorm where id='"+id+"'";
		         Query queryObject = getSession().createQuery(queryString);
				 List<PKbinorm> list = queryObject.list();
				 if(list.isEmpty())
				 {
					 return null;
				 }
				 else
				 {
					 return list.get(0);
				 }
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}
}