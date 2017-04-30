package model;

import java.sql.Date;
/**
 * 消息通知类模型
 * @author 宽伟
 *
 */

public class InfoModel {
	private String informer;//通知人
	private String informerID;//通知人ID
	private String informeder;//被通知人
	private String informederID;//被通知人ID
	private String informThing;//通知事件
	private Date informDate;//通知时间

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
