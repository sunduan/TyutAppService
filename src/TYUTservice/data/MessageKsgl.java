package TYUTservice.data;

public class MessageKsgl extends MessageTyut {

	private int status;
	private String ksmc;
	private String ksks;
	private String ksjs;
	private int bmzt;
	public int getStatus() {
		return status;
	}
	public MessageKsgl(int status, String ksmc, String ksks, String ksjs,
			int bmzt) {
		super();
		this.status = status;
		this.ksmc = ksmc;
		this.ksks = ksks;
		this.ksjs = ksjs;
		this.bmzt = bmzt;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getKsks() {
		return ksks;
	}
	public void setKsks(String ksks) {
		this.ksks = ksks;
	}
	public String getKsjs() {
		return ksjs;
	}
	public void setKsjs(String ksjs) {
		this.ksjs = ksjs;
	}
	public int getBmzt() {
		return bmzt;
	}
	public void setBmzt(int bmzt) {
		this.bmzt = bmzt;
	}
	

}
