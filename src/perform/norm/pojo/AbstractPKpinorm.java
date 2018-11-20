package perform.norm.pojo;
// default package



/**
 * AbstractPKpinorm entity provides the base persistence definition of the PKpinorm entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKpinorm  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String target;
     private String score;
     private String rule;
     private String pdpname;
     private String remark;


    // Constructors

    /** default constructor */
    public AbstractPKpinorm() {
    }

	/** minimal constructor */
    public AbstractPKpinorm(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public AbstractPKpinorm(String name, String target, String score, String rule, String pdpname, String remark) {
        this.name = name;
        this.target = target;
        this.score = score;
        this.rule = rule;
        this.pdpname = pdpname;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }

    public String getScore() {
        return this.score;
    }
    
    public void setScore(String score) {
        this.score = score;
    }

    public String getRule() {
        return this.rule;
    }
    
    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getPdpname() {
        return this.pdpname;
    }
    
    public void setPdpname(String pdpname) {
        this.pdpname = pdpname;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}