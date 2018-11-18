package perform.seasonrate.pojo;
// default package



/**
 * AbstractPKPIScore entity provides the base persistence definition of the PKPIScore entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKPIScore  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String kpiname;
     private String target;
     private Double score;
     private String rule;
     private Integer kpinumber;
     private String name;
     private String newnumber;
     private Double sum;
     private Integer year;
     private Integer season;
     private String kpipdpname;


    // Constructors

    /** default constructor */
    public AbstractPKPIScore() {
    }

    
    /** full constructor */
    public AbstractPKPIScore(String kpiname, String target, Double score, String rule, Integer kpinumber, String name, String newnumber, Double sum, Integer year, Integer season, String kpipdpname) {
        this.kpiname = kpiname;
        this.target = target;
        this.score = score;
        this.rule = rule;
        this.kpinumber = kpinumber;
        this.name = name;
        this.newnumber = newnumber;
        this.sum = sum;
        this.year = year;
        this.season = season;
        this.kpipdpname = kpipdpname;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getKpiname() {
        return this.kpiname;
    }
    
    public void setKpiname(String kpiname) {
        this.kpiname = kpiname;
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

    public Integer getKpinumber() {
        return this.kpinumber;
    }
    
    public void setKpinumber(Integer kpinumber) {
        this.kpinumber = kpinumber;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getNewnumber() {
        return this.newnumber;
    }
    
    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }

    public Double getSum() {
        return this.sum;
    }
    
    public void setSum(Double sum) {
        this.sum = sum;
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

    public String getKpipdpname() {
        return this.kpipdpname;
    }
    
    public void setKpipdpname(String kpipdpname) {
        this.kpipdpname = kpipdpname;
    }
   








}