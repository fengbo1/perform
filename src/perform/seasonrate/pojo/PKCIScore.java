package perform.seasonrate.pojo;
// default package



/**
 * PKCIScore entity. @author MyEclipse Persistence Tools
 */
public class PKCIScore extends AbstractPKCIScore implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PKCIScore() {
    }

    
    /** full constructor */
    public PKCIScore(String kciname, String target, Double score, String rule, Integer kcinumber, String name, String newnumber, Double result1, String rater1, String remark1, Double result2, String rater2, String remark2, Double result3, String rater3, String remark3, Double sum, Integer year, Integer season) {
        super(kciname, target, score, rule, kcinumber, name, newnumber, result1, rater1, remark1, result2, rater2, remark2, result3, rater3, remark3, sum, year, season);        
    }
   
}
