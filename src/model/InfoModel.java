package model;

import java.sql.Date;
/**
 * 消息通知类模型
 * @author 宽伟
 *
 */

public class InfoModel {
	private String infromer;//通知人
	private String infromeder;//被通知人
	private String infromederId;//被通知人ID
	private String infromThing;//通知事件
	private Date infromDate;//通知时间
	public String getInfromer() {
		return infromer;
	}
	public void setInfromer(String infromer) {
		this.infromer = infromer;
	}
	public String getInfromeder() {
		return infromeder;
	}
	public void setInfromeder(String infromeder) {
		this.infromeder = infromeder;
	}
	public String getInfromederId() {
		return infromederId;
	}
	public void setInfromederId(String infromederId) {
		this.infromederId = infromederId;
	}
	public String getInfromThing() {
		return infromThing;
	}
	public void setInfromThing(String infromThing) {
		this.infromThing = infromThing;
	}
	public Date getInfromDate() {
		return infromDate;
	}
	public void setInfromDate(Date infromDate) {
		this.infromDate = infromDate;
	}
	
	

}
