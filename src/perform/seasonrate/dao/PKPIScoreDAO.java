package perform.seasonrate.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.seasonrate.pojo.PKPIScore;
import perform.userinfo.pojo.PUser;

/**
 	* A data access object (DAO) providing persistence and search support for PKPIScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PKPIScore
  * @author MyEclipse Persistence Tools 
 */

public class PKPIScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PKPIScoreDAO.class);
		//property constants
	public static final String KPINAME = "kpiname";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";
	public static final String KPINUMBER = "kpinumber";
	public static final String NAME = "name";
	public static final String NEWNUMBER = "newnumber";
	public static final String SUM = "sum";
	public static final String YEAR = "year";
	public static final String SEASON = "season";
	public static final String KPIPDPNAME = "kpipdpname";



    
    public void save(PKPIScore transientInstance) {
        log.debug("saving PKPIScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PKPIScore persistentInstance) {
        log.debug("deleting PKPIScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PKPIScore findById( java.lang.Integer id) {
        log.debug("getting PKPIScore instance with id: " + id);
        try {
            PKPIScore instance = (PKPIScore) getSession()
                    .get("PKPIScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PKPIScore instance) {
        log.debug("finding PKPIScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("PKPIScore")
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
      log.debug("finding PKPIScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PKPIScore as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByKpiname(Object kpiname
	) {
		return findByProperty(KPINAME, kpiname
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
	
	public List findByKpinumber(Object kpinumber
	) {
		return findByProperty(KPINUMBER, kpinumber
		);
	}
	
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByNewnumber(Object newnumber
	) {
		return findByProperty(NEWNUMBER, newnumber
		);
	}
	
	public List findBySum(Object sum
	) {
		return findByProperty(SUM, sum
		);
	}
	
	public List findByYear(Object year
	) {
		return findByProperty(YEAR, year
		);
	}
	
	public List findBySeason(Object season
	) {
		return findByProperty(SEASON, season
		);
	}
	
	public List findByKpipdpname(Object kpipdpname
	) {
		return findByProperty(KPIPDPNAME, kpipdpname
		);
	}
	

	public List findAll() {
		log.debug("finding all PKPIScore instances");
		try {
			String queryString = "from PKPIScore";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PKPIScore merge(PKPIScore detachedInstance) {
        log.debug("merging PKPIScore instance");
        try {
            PKPIScore result = (PKPIScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PKPIScore instance) {
        log.debug("attaching dirty PKPIScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PKPIScore instance) {
        log.debug("attaching clean PKPIScore instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public PKPIScore findByNewNumbernew(String newnumber,int year,int season) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PKPIScore as kp where kp.newnumber='"+newnumber+"' and kp.year='"+year+"'  and kp.season='"+season+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PKPIScore) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}