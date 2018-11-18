package perform.flag.pojo;
// default package



/**
 * AbstractPFlag entity provides the base persistence definition of the PFlag entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPFlag  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer year;
     private Integer season;
     private Integer flag;
     private Integer isnew;


    // Constructors

    /** default constructor */
    public AbstractPFlag() {
    }

    
    /** full constructor */
    public AbstractPFlag(Integer year, Integer season, Integer flag, Integer isnew) {
        this.year = year;
        this.season = season;
        this.flag = flag;
        this.isnew = isnew;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSeason() {
        return this.season;
    }
    
    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getFlag() {
        return this.flag;
    }
    
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getIsnew() {
        return this.isnew;
    }
    
    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }
   








}