package perform.norm.pojo;
// default package



/**
 * PKpinorm entity. @author MyEclipse Persistence Tools
 */
public class PKpinorm extends AbstractPKpinorm implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PKpinorm() {
    }

	/** minimal constructor */
    public PKpinorm(String name) {
        super(name);        
    }
    
    /** full constructor */
    public PKpinorm(String name, String target, String score, String rule, String pdpname, String remark) {
        super(name, target, score, rule, pdpname, remark);        
    }
   
}
