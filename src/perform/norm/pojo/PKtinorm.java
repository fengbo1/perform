package perform.norm.pojo;

/**
 * PKtinorm entity. @author MyEclipse Persistence Tools
 */
public class PKtinorm extends AbstractPKtinorm implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PKtinorm() {
	}

	/** minimal constructor */
	public PKtinorm(String name, String chu, String tuan) {
		super(name, chu, tuan);
	}

	/** full constructor */
	public PKtinorm(String name, String chu, String tuan, String target,
			Double score, String rule) {
		super(name, chu, tuan, target, score, rule);
	}

}
