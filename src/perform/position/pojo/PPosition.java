package perform.position.pojo;
// default package



/**
 * PPosition entity. @author MyEclipse Persistence Tools
 */
public class PPosition extends AbstractPPosition implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PPosition() {
    }

    
    /** full constructor */
    public PPosition(String name, String chu, String tuan, String kpinorm, String kpinormprop, String kpirater, String ktinorm, String ktinormprop, String ktirater, String kcinorm, String kcinormprop, String kcirater, String kbinorm, String kbinormprop, String kbirater, Double kpiprop, Double ktiprop, Double kciprop, Double kbiprop) {
        super(name, chu, tuan, kpinorm, kpinormprop, kpirater, ktinorm, ktinormprop, ktirater, kcinorm, kcinormprop, kcirater, kbinorm, kbinormprop, kbirater, kpiprop, ktiprop, kciprop, kbiprop);        
    }
   
}
