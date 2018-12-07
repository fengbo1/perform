package perform.seasonrate.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.seasonrate.pojo.PKCIScore;

/**
 	* A data access object (DAO) providing persistence and search support for PKCIScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PKCIScore
  * @author MyEclipse Persistence Tools 
 */

public class PKCIScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PKCIScoreDAO.class);
		//property constants
	public static final String KCINAME = "kciname";
	public static final String TARGET = "target";
	public static final String SCORE = "score";
	public static final String RULE = "rule";
	public static final String KCINUMBER = "kcinumber";
	public static final String NAME = "name";
	public static final String NEWNUMBER = "newnumber";
	public static final String RESULT1 = "result1";
	public static final String RATER1 = "rater1";
	public static final String REMARK1 = "remark1";
	public static final String RESULT2 = "result2";
	public static final String RATER2 = "rater2";
	public static final String REMARK2 = "remark2";
	public static final String RESULT3 = "result3";
	public static final String RATER3 = "rater3";
	public static final String REMARK3 = "remark3";
	public static final String PROP = "prop";
	public static final String SUM = "sum";
	public static final String YEAR = "year";
	public static final String SEASON = "season";



    
    public void save(PKCIScore transientInstance) {
        log.debug("saving PKCIScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PKCIScore persistentInstance) {
        log.debug("deleting PKCIScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PKCIScore findById( java.lang.Integer id) {
        log.debug("getting PKCIScore instance with id: " + id);
        try {
            PKCIScore instance = (PKCIScore) getSession()
                    .get("PKCIScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PKCIScore instance) {
        log.debug("finding PKCIScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("PKCIScore")
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
      log.debug("finding PKCIScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PKCIScore as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByKciname(Object kciname
	) {
		return findByProperty(KCINAME, kciname
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
	
	public List findByKcinumber(Object kcinumber
	) {
		return findByProperty(KCINUMBER, kcinumber
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
	
	public List findByResult1(Object result1
	) {
		return findByProperty(RESULT1, result1
		);
	}
	
	public List findByRater1(Object rater1
	) {
		return findByProperty(RATER1, rater1
		);
	}
	
	public List findByRemark1(Object remark1
	) {
		return findByProperty(REMARK1, remark1
		);
	}
	
	public List findByResult2(Object result2
	) {
		return findByProperty(RESULT2, result2
		);
	}
	
	public List findByRater2(Object rater2
	) {
		return findByProperty(RATER2, rater2
		);
	}
	
	public List findByRemark2(Object remark2
	) {
		return findByProperty(REMARK2, remark2
		);
	}
	
	public List findByResult3(Object result3
	) {
		return findByProperty(RESULT3, result3
		);
	}
	
	public List findByRater3(Object rater3
	) {
		return findByProperty(RATER3, rater3
		);
	}
	
	public List findByRemark3(Object remark3
	) {
		return findByProperty(REMARK3, remark3
		);
	}
	
	public List findByProp(Object prop
	) {
		return findByProperty(PROP, prop
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
	

	public List findAll() {
		log.debug("finding all PKCIScore instances");
		try {
			String queryString = "from PKCIScore";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PKCIScore merge(PKCIScore detachedInstance) {
        log.debug("merging PKCIScore instance");
        try {
            PKCIScore result = (PKCIScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PKCIScore instance) {
        log.debug("attaching dirty PKCIScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PKCIScore instance) {
        log.debug("attaching clean PKCIScore instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    public List<PKCIScore> findByYearSeasonNewnumber(int year,int season,String newnumber) {
    	log.debug("finding all PKCIScore instances");
    	try {
    		String queryString = "from PKCIScore as p where p.year='"+year+"' and p.season='"+season+"' and p.newnumber='"+newnumber+"'";
             Query queryObject = getSession().createQuery(queryString);
    		 List<PKCIScore> list = queryObject.list();
    		 return list;
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }
    public PKCIScore findByYearSeasonNewnumberKciname(int year,int season,String newnumber,String kciname) {
    	log.debug("finding all PKCIScore instances");
    	try {
    		String queryString = "from PKCIScore as p where p.year='"+year+"' and p.season='"+season+"' and p.newnumber='"+newnumber+"' and p.kciname='"+kciname+"'";
             Query queryObject = getSession().createQuery(queryString);
    		 List<PKCIScore> list = queryObject.list();
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
    public PKCIScore findByYearSeasonNewnumberTiyanduijie(int year,int season,String newnumber) {
    	log.debug("finding all PKCIScore instances");
    	try {
    		String queryString = "from PKCIScore as p where p.year='"+year+"' and p.season='"+season+"' and p.newnumber='"+newnumber+"' and (p.kciname like '%对接%' or p.kciname like '%体验%')";
             Query queryObject = getSession().createQuery(queryString);
    		 List<PKCIScore> list = queryObject.list();
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