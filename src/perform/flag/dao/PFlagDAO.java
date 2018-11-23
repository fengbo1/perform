package perform.flag.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.flag.pojo.PFlag;

/**
 	* A data access object (DAO) providing persistence and search support for PFlag entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PFlag
  * @author MyEclipse Persistence Tools 
 */

public class PFlagDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PFlagDAO.class);
		//property constants
	public static final String YEAR = "year";
	public static final String SEASON = "season";
	public static final String FLAG = "flag";
	public static final String ISNEW = "isnew";
	public static final String ALREADYRATE = "alreadyrate";



    
    public void save(PFlag transientInstance) {
        log.debug("saving PFlag instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PFlag persistentInstance) {
        log.debug("deleting PFlag instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PFlag findById( java.lang.Integer id) {
        log.debug("getting PFlag instance with id: " + id);
        try {
            PFlag instance = (PFlag) getSession()
                    .get("PFlag", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PFlag instance) {
        log.debug("finding PFlag instance by example");
        try {
            List results = getSession()
                    .createCriteria("PFlag")
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
      log.debug("finding PFlag instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PFlag as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
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
	
	public List findByFlag(Object flag
	) {
		return findByProperty(FLAG, flag
		);
	}
	
	public List findByIsnew(Object isnew
	) {
		return findByProperty(ISNEW, isnew
		);
	}
	
	public List findByAlreadyrate(Object alreadyrate
	) {
		return findByProperty(ALREADYRATE, alreadyrate
		);
	}
	

	public List findAll() {
		log.debug("finding all PFlag instances");
		try {
			String queryString = "from PFlag";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PFlag merge(PFlag detachedInstance) {
        log.debug("merging PFlag instance");
        try {
            PFlag result = (PFlag) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PFlag instance) {
        log.debug("attaching dirty PFlag instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PFlag instance) {
        log.debug("attaching clean PFlag instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public PFlag findByIsNew(int isnew) {
		log.debug("finding all PFlag instances");
		try {
			String queryString = "from PFlag as pf where pf.isnew='"+isnew+"'";
	         Query queryObject = getSession().createQuery(queryString);
	         List list = queryObject.list();
	         if(!list.isEmpty())
	         {
	        	 return (PFlag) list.get(0);
	         }
	         else
	         {
	        	 return null;
	         }
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public int findNowFlag() {
		log.debug("finding all PFlag instances");
		try {
			String queryString = "from PFlag as pf where pf.isnew='1'";
	         Query queryObject = getSession().createQuery(queryString);
	         List list = queryObject.list();
	         if(!list.isEmpty())
	         {
	        	 return ((PFlag)list.get(0)).getFlag();
	         }
	         else
	         {
	        	 return 0;
	         }
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    public PFlag findByYearSeason(int year,int season) {
		log.debug("finding all PFlag instances");
		try {
			String queryString = "from PFlag as pf where pf.year='"+year+"' and pf.season='"+season+"'";
	         Query queryObject = getSession().createQuery(queryString);
	         List<PFlag> list = queryObject.list();
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
    
    public List findByStatus(String status) {
		log.debug("finding all PFlag instances");
		try {
			String queryString = "from PFlag as pf where pf.flag in ("+status+")";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}