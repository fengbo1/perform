package perform.position.dao;
// default package

import ccb.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.position.pojo.PPosition;
import perform.position.pojo.PPositiontemp;

/**
 	* A data access object (DAO) providing persistence and search support for PPosition entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PPosition
  * @author MyEclipse Persistence Tools 
 */

public class PPositionDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PPositionDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String CHU = "chu";
	public static final String TUAN = "tuan";
	public static final String KPINORM = "kpinorm";
	public static final String KPINORMPROP = "kpinormprop";
	public static final String KPIRATER = "kpirater";
	public static final String KTINORM = "ktinorm";
	public static final String KTINORMPROP = "ktinormprop";
	public static final String KTIRATER = "ktirater";
	public static final String KCINORM = "kcinorm";
	public static final String KCINORMPROP = "kcinormprop";
	public static final String KCIRATER = "kcirater";
	public static final String KBINORM = "kbinorm";
	public static final String KBINORMPROP = "kbinormprop";
	public static final String KBIRATER = "kbirater";
	public static final String KPIPROP = "kpiprop";
	public static final String KTIPROP = "ktiprop";
	public static final String KCIPROP = "kciprop";
	public static final String KBIPROP = "kbiprop";



    
    public void save(PPosition transientInstance) {
        log.debug("saving PPosition instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PPosition persistentInstance) {
        log.debug("deleting PPosition instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PPosition findById( java.lang.Integer id) {
        log.debug("getting PPosition instance with id: " + id);
        try {
            PPosition instance = (PPosition) getSession()
                    .get("PPosition", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PPosition instance) {
        log.debug("finding PPosition instance by example");
        try {
            List results = getSession()
                    .createCriteria("PPosition")
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
      log.debug("finding PPosition instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PPosition as model where model." 
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
	
	public List findByChu(Object chu
	) {
		return findByProperty(CHU, chu
		);
	}
	
	public List findByTuan(Object tuan
	) {
		return findByProperty(TUAN, tuan
		);
	}
	
	public List findByKpinorm(Object kpinorm
	) {
		return findByProperty(KPINORM, kpinorm
		);
	}
	
	public List findByKpinormprop(Object kpinormprop
	) {
		return findByProperty(KPINORMPROP, kpinormprop
		);
	}
	
	public List findByKpirater(Object kpirater
	) {
		return findByProperty(KPIRATER, kpirater
		);
	}
	
	public List findByKtinorm(Object ktinorm
	) {
		return findByProperty(KTINORM, ktinorm
		);
	}
	
	public List findByKtinormprop(Object ktinormprop
	) {
		return findByProperty(KTINORMPROP, ktinormprop
		);
	}
	
	public List findByKtirater(Object ktirater
	) {
		return findByProperty(KTIRATER, ktirater
		);
	}
	
	public List findByKcinorm(Object kcinorm
	) {
		return findByProperty(KCINORM, kcinorm
		);
	}
	
	public List findByKcinormprop(Object kcinormprop
	) {
		return findByProperty(KCINORMPROP, kcinormprop
		);
	}
	
	public List findByKcirater(Object kcirater
	) {
		return findByProperty(KCIRATER, kcirater
		);
	}
	
	public List findByKbinorm(Object kbinorm
	) {
		return findByProperty(KBINORM, kbinorm
		);
	}
	
	public List findByKbinormprop(Object kbinormprop
	) {
		return findByProperty(KBINORMPROP, kbinormprop
		);
	}
	
	public List findByKbirater(Object kbirater
	) {
		return findByProperty(KBIRATER, kbirater
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
	
	public List findByKciprop(Object kciprop
	) {
		return findByProperty(KCIPROP, kciprop
		);
	}
	
	public List findByKbiprop(Object kbiprop
	) {
		return findByProperty(KBIPROP, kbiprop
		);
	}
	

	public List findAll() {
		log.debug("finding all PPosition instances");
		try {
			String queryString = "from PPosition";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PPosition merge(PPosition detachedInstance) {
        log.debug("merging PPosition instance");
        try {
            PPosition result = (PPosition) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PPosition instance) {
        log.debug("attaching dirty PPosition instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PPosition instance) {
        log.debug("attaching clean PPosition instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public PPosition findByNameandChuandTuan(String name,String chu,String tuan) {
    	log.debug("finding all PPosition instances");
    	try {
    		String queryString = "from PPosition as pp where pp.name='"+name+"'and pp.chu='"+chu+"'and pp.tuan='"+tuan+"'";
    		Query queryObject = getSession().createQuery(queryString);
    		List list = queryObject.list();
    		if(list.isEmpty())
    		{
    			return null;
    		}
    		else
    		{
    			return (PPosition) list.get(0);
    		}
    		 
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }

    public PPosition findByNameandChuandTuanandId(String name,String chu,String tuan,int id) {
    	log.debug("finding all PPosition instances");
    	try {
    		String queryString = "from PPosition as pp where pp.name='"+name+"' and pp.chu='"+chu+"' and pp.tuan='"+tuan+"' and pp.id!='"+id+"'";
    		Query queryObject = getSession().createQuery(queryString);
    		List list = queryObject.list();
    		if(list.isEmpty())
    		{
    			return null;
    		}
    		else
    		{
    			return (PPosition) list.get(0);
    		}
    		 
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }
    public PPosition findByID(int id) {
    	log.debug("finding all PPosition instances");
    	try {
    		String queryString = "from PPosition as pp where pp.id='"+id+"'";
    		Query queryObject = getSession().createQuery(queryString);
    		List list = queryObject.list();
    		if(list.isEmpty())
    		{
    			return null;
    		}
    		else
    		{
    			return (PPosition) list.get(0);
    		}
    		 
    	} catch (RuntimeException re) {
    		log.error("find all failed", re);
    		throw re;
    	}
    }
}
