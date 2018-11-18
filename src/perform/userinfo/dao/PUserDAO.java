package perform.userinfo.dao;
// default package

import ccb.dao.BaseHibernateDAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perform.userinfo.pojo.PUser;
import perform.util.UserUtil;

/**
 	* A data access object (DAO) providing persistence and search support for PUser entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .PUser
  * @author MyEclipse Persistence Tools 
 */

public class PUserDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PUserDAO.class);
		//property constants
	public static final String NEWNUMBER = "newnumber";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String POSITION = "position";
	public static final String AUTHO = "autho";
	public static final String CANSCORE = "canscore";
	public static final String PNUM = "pnum";



    
    public void save(PUser transientInstance) {
        log.debug("saving PUser instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PUser persistentInstance) {
        log.debug("deleting PUser instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PUser findById( java.lang.Integer id) {
        log.debug("getting PUser instance with id: " + id);
        try {
            PUser instance = (PUser) getSession()
                    .get("PUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PUser instance) {
        log.debug("finding PUser instance by example");
        try {
            List results = getSession()
                    .createCriteria("PUser")
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
      log.debug("finding PUser instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PUser as model where model." 
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
	
	public List findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	
	public List findByPosition(Object position
	) {
		return findByProperty(POSITION, position
		);
	}
	
	public List findByAutho(Object autho
	) {
		return findByProperty(AUTHO, autho
		);
	}
	
	public List findByCanscore(Object canscore
	) {
		return findByProperty(CANSCORE, canscore
		);
	}
	
	public List findByPnum(Object pnum
	) {
		return findByProperty(PNUM, pnum
		);
	}
	

	public List findAll() {
		log.debug("finding all PUser instances");
		try {
			String queryString = "from PUser";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PUser merge(PUser detachedInstance) {
        log.debug("merging PUser instance");
        try {
            PUser result = (PUser) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PUser instance) {
        log.debug("attaching dirty PUser instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PUser instance) {
        log.debug("attaching clean PUser instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    /**
	 * 根据传入的参数查询考核人名单
	 * @param para season,year
	 * @return
	 */
	public List findAllKaoheByPara(String para) {
		log.debug("finding all PUser instances");
		try {
			String queryString = "";
			if(para.equals("year"))
			{
				queryString = "from PUser as pu where mid(pu.position,1,1)>1 order by pu.position";
			}
			else if(para.equals("season"))
			{
				queryString = "from PUser as pu where mid(pu.position,1,1)>3 order by pu.position";
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 查询有权评分人
	 * @param position
	 * @return
	 */
	public List findRaterByPostion(String position) {
		log.debug("finding all PUser instances");
		try {
			String chu = "无";
			if(position.length()==1)
			{
				chu = position.substring(0, 1);
			}
			else if(position.length()==UserUtil.positionlength)
			{
				chu = position.substring(2, 3);
			}
			List list =  new ArrayList<PUser>();
			String queryString = "from PUser as pu where pu.position like '3_"+chu+"____' or pu.position like '2_"+chu+"____' order by pu.position";
			System.out.println(queryString);
			Query queryObject = getSession().createQuery(queryString);
			list =  queryObject.list();
			return list;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findByChu(String chu) {
		log.debug("finding all PUser instances");
		try {
			List list =  new ArrayList<PUser>();
			String queryString = "from PUser as pu where pu.position like '__"+chu+"____' order by pu.position";
			Query queryObject = getSession().createQuery(queryString);
			list =  queryObject.list();
			return list;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public PUser findByNewNumber(String newnumber) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PUser as pu where pu.newnumber='"+newnumber+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PUser) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public PUser findByNewNumberId(String newnumber, int id) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PUser as pu where pu.newnumber='"+newnumber+"' and pu.id!='"+id+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PUser) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public PUser findByName(String name) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PUser as pu where pu.name='"+name+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PUser) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public PUser findByNameId(String name,int id) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PUser as pu where pu.name='"+name+"' and pu.id!='"+id+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PUser) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public PUser findAllById(int id) {
		log.debug("finding all PerformanceUser instances");
		try {
			String queryString = "from PUser as pu where pu.id='"+id+"'";
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			if(list.isEmpty())
			{
				return null;
			}
			else
			{
				return (PUser) list.get(0);
			}
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}