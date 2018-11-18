package perform.userinfo.pojo;
// default package



/**
 * AbstractPUser entity provides the base persistence definition of the PUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String newnumber;
     private String name;
     private String password;
     private String position;
     private String autho;
     private Integer canscore;
     private Integer pnum;


    // Constructors

    /** default constructor */
    public AbstractPUser() {
    }

    
    /** full constructor */
    public AbstractPUser(String newnumber, String name, String password, String position, String autho, Integer canscore, Integer pnum) {
        this.newnumber = newnumber;
        this.name = name;
        this.password = password;
        this.position = position;
        this.autho = autho;
        this.canscore = canscore;
        this.pnum = pnum;
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

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    public String getAutho() {
        return this.autho;
    }
    
    public void setAutho(String autho) {
        this.autho = autho;
    }

    public Integer getCanscore() {
        return this.canscore;
    }
    
    public void setCanscore(Integer canscore) {
        this.canscore = canscore;
    }

    public Integer getPnum() {
        return this.pnum;
    }
    
    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }
   








}