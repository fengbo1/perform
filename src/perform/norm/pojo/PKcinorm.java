package perform.norm.pojo;
// default package



/**
 * PKcinorm entity. @author MyEclipse Persistence Tools
 */
public class PKcinorm extends AbstractPKcinorm implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PKcinorm() {
    }

	/** minimal constructor */
    public PKcinorm(String name, String level) {
        super(name, level);        
    }
    
    /** full constructor */
    public PKcinorm(String name, String level, String target, Double score, String rule, String remark) {
        super(name, level, target, score, rule, remark);        
    }
   
}
