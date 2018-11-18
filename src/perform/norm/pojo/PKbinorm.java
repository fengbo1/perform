package perform.norm.pojo;

/**
 * PKbinorm entity. @author MyEclipse Persistence Tools
 */
public class PKbinorm extends AbstractPKbinorm implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PKbinorm() {
	}

	/** minimal constructor */
	public PKbinorm(String name, String level) {
		super(name, level);
	}

	/** full constructor */
	public PKbinorm(String name, String level, String target, Double score,
			String rule) {
		super(name, level, target, score, rule);
	}

}
