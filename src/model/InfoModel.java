package model;

import java.sql.Date;
/**
 * ��Ϣ֪ͨ��ģ��
 * @author ��ΰ
 *
 */

public class InfoModel {
	private String infromer;//֪ͨ��
	private String infromeder;//��֪ͨ��
	private String infromederId;//��֪ͨ��ID
	private String infromThing;//֪ͨ�¼�
	private Date infromDate;//֪ͨʱ��
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
