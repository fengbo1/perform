package perform.userinfo.bean;

public class AjaxBean {

	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public AjaxBean(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
}