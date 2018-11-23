package perform.seasonrate.pojo;
// default package



/**
 * PScore entity. @author MyEclipse Persistence Tools
 */
public class PScore extends AbstractPScore implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PScore() {
    }

    
    /** full constructor */
    public PScore(String newnumber, String name, String position, String positionname, String positionchu, String positiontuan, String positionzu, String kpirater, String ktirater, String kbirater, String kcirater, Double kpiscore, Double ktiscore, Double kbiscore, Double kciscore, Double kpiprop, Double ktiprop, Double kbiprop, Double kciprop, Double score, Integer year, Integer season) {
        super(newnumber, name, position, positionname, positionchu, positiontuan, positionzu, kpirater, ktirater, kbirater, kcirater, kpiscore, ktiscore, kbiscore, kciscore, kpiprop, ktiprop, kbiprop, kciprop, score, year, season);        
    }
   
}
