package perform.seasonrate.pojo;
// default package



/**
 * AbstractPKTIScore entity provides the base persistence definition of the PKTIScore entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKTIScore  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String ktiname;
     private String target;
     private Double score;
     private String rule;
     private Integer ktinumber;
     private String name;
     private String newnumber;
     private Double result1;
     private String rater1;
     private String remark1;
     private Double result2;
     private String rater2;
     private String remark2;
     private Double result3;
     private String rater3;
     private String remark3;
     private Double sum;
     private Integer year;
     private Integer season;


    // Constructors

    /** default constructor */
    public AbstractPKTIScore() {
    }

    
    /** full constructor */
    public AbstractPKTIScore(String ktiname, String target, Double score, String rule, Integer ktinumber, String name, String newnumber, Double result1, String rater1, String remark1, Double result2, String rater2, String remark2, Double result3, String rater3, String remark3, Double sum, Integer year, Integer season) {
        this.ktiname = ktiname;
        this.target = target;
        this.score = score;
        this.rule = rule;
        this.ktinumber = ktinumber;
        this.name = name;
        this.newnumber = newnumber;
        this.result1 = result1;
        this.rater1 = rater1;
        this.remark1 = remark1;
        this.result2 = result2;
        this.rater2 = rater2;
        this.remark2 = remark2;
        this.result3 = result3;
        this.rater3 = rater3;
        this.remark3 = remark3;
        this.sum = sum;
        this.year = year;
        this.season = season;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getKtiname() {
        return this.ktiname;
    }
    
    public void setKtiname(String ktiname) {
        this.ktiname = ktiname;
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

    public Integer getKtinumber() {
        return this.ktinumber;
    }
    
    public void setKtinumber(Integer ktinumber) {
        this.ktinumber = ktinumber;
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

    public Double getResult1() {
        return this.result1;
    }
    
    public void setResult1(Double result1) {
        this.result1 = result1;
    }

    public String getRater1() {
        return this.rater1;
    }
    
    public void setRater1(String rater1) {
        this.rater1 = rater1;
    }

    public String getRemark1() {
        return this.remark1;
    }
    
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public Double getResult2() {
        return this.result2;
    }
    
    public void setResult2(Double result2) {
        this.result2 = result2;
    }

    public String getRater2() {
        return this.rater2;
    }
    
    public void setRater2(String rater2) {
        this.rater2 = rater2;
    }

    public String getRemark2() {
        return this.remark2;
    }
    
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public Double getResult3() {
        return this.result3;
    }
    
    public void setResult3(Double result3) {
        this.result3 = result3;
    }

    public String getRater3() {
        return this.rater3;
    }
    
    public void setRater3(String rater3) {
        this.rater3 = rater3;
    }

    public String getRemark3() {
        return this.remark3;
    }
    
    public void setRemark3(String remark3) {
        this.remark3 = remark3;
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
   








}