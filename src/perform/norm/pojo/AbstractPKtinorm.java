package perform.norm.pojo;

/**
 * AbstractPKtinorm entity provides the base persistence definition of the
 * PKtinorm entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKtinorm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String chu;
	private String tuan;
	private String target;
	private Double score;
	private String rule;

	// Constructors

	/** default constructor */
	public AbstractPKtinorm() {
	}

	/** minimal constructor */
	public AbstractPKtinorm(String name, String chu, String tuan) {
		this.name = name;
		this.chu = chu;
		this.tuan = tuan;
	}

	/** full constructor */
	public AbstractPKtinorm(String name, String chu, String tuan,
			String target, Double score, String rule) {
		this.name = name;
		this.chu = chu;
		this.tuan = tuan;
		this.target = target;
		this.score = score;
		this.rule = rule;
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

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}