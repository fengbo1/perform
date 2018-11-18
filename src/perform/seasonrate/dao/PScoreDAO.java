package perform.seasonrate.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.seasonrate.pojo.PScore;

/**
 	* A data access object (DAO) providing persistence and search support for PScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PScore
  * @author MyEclipse Persistence Tools 
 */

public class PScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PScoreDAO.class);
		//property constants
	public static final String NEWNUMBER = "newnumber";
	public static final String NAME = "name";
	public static final String POSITIONNAME = "positionname";
	public static final String POSITIONCHU = "positionchu";
	public static final String POSITIONTUAN = "positiontuan";
	public static final String POSITIONZU = "positionzu";
	public static final String KPISCORE = "kpiscore";
	public static final String KTISCORE = "ktiscore";
	public static final String KBISCORE = "kbiscore";
	public static final String KCISCORE = "kciscore";
	public static final String KPIPROP = "kpiprop";
	public static final String KTIPROP = "ktiprop";
	public static final String KBIPROP = "kbiprop";
	public static final String KCIPROP = "kciprop";
	public static final String SCORE = "score";
	public static final String YEAR = "year";
	public static final String SEASON = "season";



    
    public void save(PScore transientInstance) {
        log.debug("saving PScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PScore persistentInstance) {
        log.debug("deleting PScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PScore findById( java.lang.Integer id) {
        log.debug("getting PScore instance with id: " + id);
        try {
            PScore instance = (PScore) getSession()
                    .get("PScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PScore instance) {
        log.debug("finding PScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("PScore")
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
      log.debug("finding PScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PScore as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNewnumber(Object newnumber
	) {
		return findByProperty(NEWNUMBER, newnumber
		);
	}
	
	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByPositionname(Object positionname
	) {
		return findByProperty(POSITIONNAME, positionname
		);
	}
	
	public List findByPositionchu(Object positionchu
	) {
		return findByProperty(POSITIONCHU, positionchu
		);
	}
	
	public List findByPositiontuan(Object positiontuan
	) {
		return findByProperty(POSITIONTUAN, positiontuan
		);
	}
	
	public List findByPositionzu(Object positionzu
	) {
		return findByProperty(POSITIONZU, positionzu
		);
	}
	
	public List findByKpiscore(Object kpiscore
	) {
		return findByProperty(KPISCORE, kpiscore
		);
	}
	
	public List findByKtiscore(Object ktiscore
	) {
		return findByProperty(KTISCORE, ktiscore
		);
	}
	
	public List findByKbiscore(Object kbiscore
	) {
		return findByProperty(KBISCORE, kbiscore
		);
	}
	
	public List findByKciscore(Object kciscore
	) {
		return findByProperty(KCISCORE, kciscore
		);
	}
	
	public List findByKpiprop(Object kpiprop
	) {
		return findByProperty(KPIPROP, kpiprop
		);
	}
	
	public List findByKtiprop(Object ktiprop
	) {
		return findByProperty(KTIPROP, ktiprop
		);
	}
	
	public List findByKbiprop(Object kbiprop
	) {
		return findByProperty(KBIPROP, kbiprop
		);
	}
	
	public List findByKciprop(Object kciprop
	) {
		return findByProperty(KCIPROP, kciprop
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
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
	

	public List findAll() {
		log.debug("finding all PScore instances");
		try {
			String queryString = "from PScore";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PScore merge(PScore detachedInstance) {
        log.debug("merging PScore instance");
        try {
            PScore result = (PScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PScore instance) {
        log.debug("attaching dirty PScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PScore instance) {
        log.debug("attaching clean PScore instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public PScore findByNewnumberYearSeason(String newnumber,int year,int season) {
    	log.debug("finding all PScore instances");
    	try {
    		String queryString = "from PScore as ps where ps.newnumber='"+newnumber+"' and ps.year='"+year+"' and ps.season='"+season+"'";
             Query queryObject = getSession().createQuery(queryString);
    		 List list = queryObject.list();
    		 if(list.isEmpty())
    		 {
    			 return new PScore();
    		 }
    		 else
    		 {
    			 return (PScore)list.get(0);
    		 }
              
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }
    
    public PScore findByNewnumberYearSeasonnew(String newnumber,int year,int season) {
    	log.debug("finding all PScore instances");
    	try {
    		String queryString = "from PScore as ps where ps.newnumber='"+newnumber+"' and ps.year='"+year+"' and ps.season='"+season+"'";
             Query queryObject = getSession().createQuery(queryString);
    		 List list = queryObject.list();
    		 if(list.isEmpty())
    		 {
    			 return null;
    		 }
    		 else
    		 {
    			 return (PScore)list.get(0);
    		 }
              
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }
}