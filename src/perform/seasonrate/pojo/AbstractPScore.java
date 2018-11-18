package perform.seasonrate.pojo;
// default package



/**
 * AbstractPScore entity provides the base persistence definition of the PScore entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPScore  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String newnumber;
     private String name;
     private String positionname;
     private String positionchu;
     private String positiontuan;
     private String positionzu;
     private Double kpiscore;
     private Double ktiscore;
     private Double kbiscore;
     private Double kciscore;
     private Double kpiprop;
     private Double ktiprop;
     private Double kbiprop;
     private Double kciprop;
     private Double score;
     private Integer year;
     private Integer season;


    // Constructors

    /** default constructor */
    public AbstractPScore() {
    }

    
    /** full constructor */
    public AbstractPScore(String newnumber, String name, String positionname, String positionchu, String positiontuan, String positionzu, Double kpiscore, Double ktiscore, Double kbiscore, Double kciscore, Double kpiprop, Double ktiprop, Double kbiprop, Double kciprop, Double score, Integer year, Integer season) {
        this.newnumber = newnumber;
        this.name = name;
        this.positionname = positionname;
        this.positionchu = positionchu;
        this.positiontuan = positiontuan;
        this.positionzu = positionzu;
        this.kpiscore = kpiscore;
        this.ktiscore = ktiscore;
        this.kbiscore = kbiscore;
        this.kciscore = kciscore;
        this.kpiprop = kpiprop;
        this.ktiprop = ktiprop;
        this.kbiprop = kbiprop;
        this.kciprop = kciprop;
        this.score = score;
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

    public String getNewnumber() {
        return this.newnumber;
    }
    
    public void setNewnumber(String newnumber) {
        this.newnumber = newnumber;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPositionname() {
        return this.positionname;
    }
    
    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    public String getPositionchu() {
        return this.positionchu;
    }
    
    public void setPositionchu(String positionchu) {
        this.positionchu = positionchu;
    }

    public String getPositiontuan() {
        return this.positiontuan;
    }
    
    public void setPositiontuan(String positiontuan) {
        this.positiontuan = positiontuan;
    }

    public String getPositionzu() {
        return this.positionzu;
    }
    
    public void setPositionzu(String positionzu) {
        this.positionzu = positionzu;
    }

    public Double getKpiscore() {
        return this.kpiscore;
    }
    
    public void setKpiscore(Double kpiscore) {
        this.kpiscore = kpiscore;
    }

    public Double getKtiscore() {
        return this.ktiscore;
    }
    
    public void setKtiscore(Double ktiscore) {
        this.ktiscore = ktiscore;
    }

    public Double getKbiscore() {
        return this.kbiscore;
    }
    
    public void setKbiscore(Double kbiscore) {
        this.kbiscore = kbiscore;
    }

    public Double getKciscore() {
        return this.kciscore;
    }
    
    public void setKciscore(Double kciscore) {
        this.kciscore = kciscore;
    }

    public Double getKpiprop() {
        return this.kpiprop;
    }
    
    public void setKpiprop(Double kpiprop) {
        this.kpiprop = kpiprop;
    }

    public Double getKtiprop() {
        return this.ktiprop;
    }
    
    public void setKtiprop(Double ktiprop) {
        this.ktiprop = ktiprop;
    }

    public Double getKbiprop() {
        return this.kbiprop;
    }
    
    public void setKbiprop(Double kbiprop) {
        this.kbiprop = kbiprop;
    }

    public Double getKciprop() {
        return this.kciprop;
    }
    
    public void setKciprop(Double kciprop) {
        this.kciprop = kciprop;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
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