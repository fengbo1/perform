package perform.norm.pojo;
// default package



/**
 * AbstractPKcinorm entity provides the base persistence definition of the PKcinorm entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKcinorm  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String level;
     private String target;
     private Double score;
     private String rule;
     private String remark;


    // Constructors

    /** default constructor */
    public AbstractPKcinorm() {
    }

	/** minimal constructor */
    public AbstractPKcinorm(String name, String level) {
        this.name = name;
        this.level = level;
    }
    
    /** full constructor */
    public AbstractPKcinorm(String name, String level, String target, Double score, String rule, String remark) {
        this.name = name;
        this.level = level;
        this.target = target;
        this.score = score;
        this.rule = rule;
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

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }

    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getRule() {
        return this.rule;
    }
    
    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}