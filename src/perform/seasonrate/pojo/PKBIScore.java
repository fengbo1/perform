package perform.seasonrate.pojo;
// default package



/**
 * PKBIScore entity. @author MyEclipse Persistence Tools
 */
public class PKBIScore extends AbstractPKBIScore implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PKBIScore() {
    }

    
    /** full constructor */
    public PKBIScore(String kbiname, String target, Double score, String rule, Integer kbinumber, String name, String newnumber, Double result1, String rater1, String remark1, Double result2, String rater2, String remark2, Double result3, String rater3, String remark3, Double sum, Integer year, Integer season) {
        super(kbiname, target, score, rule, kbinumber, name, newnumber, result1, rater1, remark1, result2, rater2, remark2, result3, rater3, remark3, sum, year, season);        
    }
   
}
