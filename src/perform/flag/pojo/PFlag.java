package perform.flag.pojo;
// default package



/**
 * PFlag entity. @author MyEclipse Persistence Tools
 */
public class PFlag extends AbstractPFlag implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PFlag() {
    }

    
    /** full constructor */
    public PFlag(Integer year, Integer season, Integer flag, Integer isnew) {
        super(year, season, flag, isnew);        
    }
   
}
