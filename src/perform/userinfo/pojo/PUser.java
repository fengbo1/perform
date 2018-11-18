package perform.userinfo.pojo;
// default package



/**
 * PUser entity. @author MyEclipse Persistence Tools
 */
public class PUser extends AbstractPUser implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public PUser() {
    }

    
    /** full constructor */
    public PUser(String newnumber, String name, String password, String position, String autho, Integer canscore, Integer pnum) {
        super(newnumber, name, password, position, autho, canscore, pnum);        
    }
   
}
