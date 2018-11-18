package perform.seasonrate.pojo;
// default package



/**
 * PKPIScore entity. @author MyEclipse Persistence Tools
 */
public class PKPIScore extends AbstractPKPIScore implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PKPIScore() {
    }

    
    /** full constructor */
    public PKPIScore(String kpiname, String target, Double score, String rule, Integer kpinumber, String name, String newnumber, Double sum, Integer year, Integer season, String kpipdpname) {
        super(kpiname, target, score, rule, kpinumber, name, newnumber, sum, year, season, kpipdpname);        
    }
   
}
