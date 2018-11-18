package perform.norm.pojo;

/**
 * AbstractPKbinorm entity provides the base persistence definition of the
 * PKbinorm entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPKbinorm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String level;
	private String target;
	private Double score;
	private String rule;

	// Constructors

	/** default constructor */
	public AbstractPKbinorm() {
	}

	/** minimal constructor */
	public AbstractPKbinorm(String name, String level) {
		this.name = name;
		this.level = level;
	}

	/** full constructor */
	public AbstractPKbinorm(String name, String level, String target,
			Double score, String rule) {
		this.name = name;
		this.level = level;
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

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
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