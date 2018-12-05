package perform.norm.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.norm.pojo.PKbinorm;
import perform.norm.pojo.PKpinorm;

/**
 	* A data access object (DAO) providing persistence and search support for PKpinorm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PKpinorm
  * @author MyEclipse Persistence Tools 
 */

public class PKpinormDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PKpinormDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";
	public static final String PDPNAME = "pdpname";
	public static final String REMARK = "remark";



    
    public void save(PKpinorm transientInstance) {
        log.debug("saving PKpinorm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PKpinorm persistentInstance) {
        log.debug("deleting PKpinorm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PKpinorm findById( java.lang.Integer id) {
        log.debug("getting PKpinorm instance with id: " + id);
        try {
            PKpinorm instance = (PKpinorm) getSession()
                    .get("PKpinorm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PKpinorm instance) {
        log.debug("finding PKpinorm instance by example");
        try {
            List results = getSession()
                    .createCriteria("PKpinorm")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding PKpinorm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PKpinorm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByTarget(Object target
	) {
		return findByProperty(TARGET, target
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findByRule(Object rule
	) {
		return findByProperty(RULE, rule
		);
	}
	
	public List findByPdpname(Object pdpname
	) {
		return findByProperty(PDPNAME, pdpname
		);
	}
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	public List findAll() {
		log.debug("finding all PKpinorm instances");
		try {
			String queryString = "from PKpinorm";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PKpinorm merge(PKpinorm detachedInstance) {
        log.debug("merging PKpinorm instance");
        try {
            PKpinorm result = (PKpinorm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PKpinorm instance) {
        log.debug("attaching dirty PKpinorm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PKpinorm instance) {
        log.debug("attaching clean PKpinorm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
	 public PKpinorm findByLevelAndName(String name) {
	    	log.debug("finding all PKtinorm instances");
	    	try {
	    		String queryString = "from PKpinorm as kb where kb.name='"+name+"'";
	             Query queryObject = getSession().createQuery(queryString);
	    		 List list = queryObject.list();
	    		 if(list.isEmpty())
	    		 {
	    			 return null;
	    		 }
	    		 else
	    		 {
	    			 return (PKpinorm)list.get(0);
	    		 }
	              
	    	} catch (RuntimeException re) {
	    		log.error("find all failed", re);
	    		throw re;
	    	}
	    }
		public PKpinorm findAllById(String id) {
			log.debug("finding all PKpinorm instances");
			try {
				String queryString = "from PKpinorm where id='"+id+"'";
		         Query queryObject = getSession().createQuery(queryString);
				 List<PKpinorm> list = queryObject.list();
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