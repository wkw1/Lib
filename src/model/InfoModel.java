package model;

import java.sql.Date;
/**
 * ��Ϣ֪ͨ��ģ��
 * @author ��ΰ
 *
 */

public class InfoModel {
	private String informer;//֪ͨ��
	private String informerID;//֪ͨ��ID
	private String informeder;//��֪ͨ��
	private String informederID;//��֪ͨ��ID
	private String informThing;//֪ͨ�¼�
	private Date informDate;//֪ͨʱ��

	public String getInformerID() {
		return informerID;
	}

	public void setInformerID(String informerID) {
		this.informerID = informerID;
	}

	public String getInformer() {
		return informer;
	}

	public void setInformer(String informer) {
		this.informer = informer;
	}

	public String getInformeder() {
		return informeder;
	}

	public void setInformeder(String informeder) {
		this.informeder = informeder;
	}

	public String getInformederID() {
		return informederID;
	}

	public void setInformederID(String informederId) {
		this.informederID = informederId;
	}

	public String getInformThing() {
		return informThing;
	}

	public void setInformThing(String informThing) {
		this.informThing = informThing;
	}

	public Date getInformDate() {
		return informDate;
	}

	public void setInformDate(Date informDate) {
		this.informDate = informDate;
	}
}
