package perform.norm.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.norm.pojo.PKcinorm;
import perform.norm.pojo.PKtinorm;

/**
 	* A data access object (DAO) providing persistence and search support for PKcinorm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PKcinorm
  * @author MyEclipse Persistence Tools 
 */

public class PKcinormDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PKcinormDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";
	public static final String REMARK = "remark";



    
    public void save(PKcinorm transientInstance) {
        log.debug("saving PKcinorm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PKcinorm persistentInstance) {
        log.debug("deleting PKcinorm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PKcinorm findById( java.lang.Integer id) {
        log.debug("getting PKcinorm instance with id: " + id);
        try {
            PKcinorm instance = (PKcinorm) getSession()
                    .get("PKcinorm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PKcinorm instance) {
        log.debug("finding PKcinorm instance by example");
        try {
            List results = getSession()
                    .createCriteria("PKcinorm")
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
      log.debug("finding PKcinorm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PKcinorm as model where model." 
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
	
	public List findByLevel(Object level
	) {
		return findByProperty(LEVEL, level
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
	
	public List findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	public List findAll() {
		log.debug("finding all PKcinorm instances");
		try {
			String queryString = "from PKcinorm";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PKcinorm merge(PKcinorm detachedInstance) {
        log.debug("merging PKcinorm instance");
        try {
            PKcinorm result = (PKcinorm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PKcinorm instance) {
        log.debug("attaching dirty PKcinorm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PKcinorm instance) {
        log.debug("attaching clean PKcinorm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
	
	 public PKcinorm findByLevelAndName(String name) {
	    	log.debug("finding all PKcinorm instances");
	    	try {
	    		String queryString = "from PKcinorm as kc where kc.name='"+name+"'";
	             Query queryObject = getSession().createQuery(queryString);
	    		 List list = queryObject.list();
	    		 if(list.isEmpty())
	    		 {
	    			 return null;
	    		 }
	    		 else
	    		 {
	    			 return (PKcinorm)list.get(0);
	    		 }
	              
	    	} catch (RuntimeException re) {
	    		log.error("find all failed", re);
	    		throw re;
	    	}
	    }
	 
	 public PKcinorm findAllById(String id) {
			log.debug("finding all PKcinorm instances");
			try {
				String queryString = "from PKcinorm where id='"+id+"'";
		         Query queryObject = getSession().createQuery(queryString);
				 List<PKcinorm> list = queryObject.list();
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