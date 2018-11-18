package perform.position.pojo;
// default package



/**
 * AbstractPPositiontemp entity provides the base persistence definition of the PPositiontemp entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPPositiontemp  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String chu;
     private String tuan;
     private String kpinorm;
     private String kpinormprop;
     private String kpirater;
     private String ktinorm;
     private String ktinormprop;
     private String ktirater;
     private String kcinorm;
     private String kcinormprop;
     private String kcirater;
     private String kbinorm;
     private String kbinormprop;
     private String kbirater;
     private Double kpiprop;
     private Double ktiprop;
     private Double kciprop;
     private Double kbiprop;


    // Constructors

    /** default constructor */
    public AbstractPPositiontemp() {
    }

    
    /** full constructor */
    public AbstractPPositiontemp(String name, String chu, String tuan, String kpinorm, String kpinormprop, String kpirater, String ktinorm, String ktinormprop, String ktirater, String kcinorm, String kcinormprop, String kcirater, String kbinorm, String kbinormprop, String kbirater, Double kpiprop, Double ktiprop, Double kciprop, Double kbiprop) {
        this.name = name;
        this.chu = chu;
        this.tuan = tuan;
        this.kpinorm = kpinorm;
        this.kpinormprop = kpinormprop;
        this.kpirater = kpirater;
        this.ktinorm = ktinorm;
        this.ktinormprop = ktinormprop;
        this.ktirater = ktirater;
        this.kcinorm = kcinorm;
        this.kcinormprop = kcinormprop;
        this.kcirater = kcirater;
        this.kbinorm = kbinorm;
        this.kbinormprop = kbinormprop;
        this.kbirater = kbirater;
        this.kpiprop = kpiprop;
        this.ktiprop = ktiprop;
        this.kciprop = kciprop;
        this.kbiprop = kbiprop;
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

    public String getChu() {
        return this.chu;
    }
    
    public void setChu(String chu) {
        this.chu = chu;
    }

    public String getTuan() {
        return this.tuan;
    }
    
    public void setTuan(String tuan) {
        this.tuan = tuan;
    }

    public String getKpinorm() {
        return this.kpinorm;
    }
    
    public void setKpinorm(String kpinorm) {
        this.kpinorm = kpinorm;
    }

    public String getKpinormprop() {
        return this.kpinormprop;
    }
    
    public void setKpinormprop(String kpinormprop) {
        this.kpinormprop = kpinormprop;
    }

    public String getKpirater() {
        return this.kpirater;
    }
    
    public void setKpirater(String kpirater) {
        this.kpirater = kpirater;
    }

    public String getKtinorm() {
        return this.ktinorm;
    }
    
    public void setKtinorm(String ktinorm) {
        this.ktinorm = ktinorm;
    }

    public String getKtinormprop() {
        return this.ktinormprop;
    }
    
    public void setKtinormprop(String ktinormprop) {
        this.ktinormprop = ktinormprop;
    }

    public String getKtirater() {
        return this.ktirater;
    }
    
    public void setKtirater(String ktirater) {
        this.ktirater = ktirater;
    }

    public String getKcinorm() {
        return this.kcinorm;
    }
    
    public void setKcinorm(String kcinorm) {
        this.kcinorm = kcinorm;
    }

    public String getKcinormprop() {
        return this.kcinormprop;
    }
    
    public void setKcinormprop(String kcinormprop) {
        this.kcinormprop = kcinormprop;
    }

    public String getKcirater() {
        return this.kcirater;
    }
    
    public void setKcirater(String kcirater) {
        this.kcirater = kcirater;
    }

    public String getKbinorm() {
        return this.kbinorm;
    }
    
    public void setKbinorm(String kbinorm) {
        this.kbinorm = kbinorm;
    }

    public String getKbinormprop() {
        return this.kbinormprop;
    }
    
    public void setKbinormprop(String kbinormprop) {
        this.kbinormprop = kbinormprop;
    }

    public String getKbirater() {
        return this.kbirater;
    }
    
    public void setKbirater(String kbirater) {
        this.kbirater = kbirater;
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

    public Double getKciprop() {
        return this.kciprop;
    }
    
    public void setKciprop(Double kciprop) {
        this.kciprop = kciprop;
    }

    public Double getKbiprop() {
        return this.kbiprop;
    }
    
    public void setKbiprop(Double kbiprop) {
        this.kbiprop = kbiprop;
    }
   








}