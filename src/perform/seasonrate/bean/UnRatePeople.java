package perform.seasonrate.bean;

/**
 * 未评分人员名单
 * @author htzx
 *
 */
public class UnRatePeople {

	private String chu;
	private String name;
	private String type;//未评分指标：KPI/KTI/KBI/KCI
	private String unrater;
	public String getChu() {
		return chu;
	}
	public void setChu(String chu) {
		this.chu = chu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnrater() {
		return unrater;
	}
	public void setUnrater(String unrater) {
		this.unrater = unrater;
	}
	
}
