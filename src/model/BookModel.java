package model;

import java.sql.Date;

/**
 * ͼ��ģ����
 * ���ͼ�����������
 * @author ��ΰ
 *
 */

public class BookModel {
	private String ISBN;//��ı��
	private String name;//����
	private String introduction;//���
	private String author;//����
	private String press;//������
	private int TN;//ͼ��������
	private int RN;//ͼ��ʣ������
	private int powerNeed;//��������Ȩ��
	private Date storageTime;//ͼ�����ʱ��
	private String bookType;
	
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getTN() {
		return TN;
	}
	public void setTN(int tN) {
		TN = tN;
	}
	public int getRN() {
		return RN;
	}
	public void setRN(int rN) {
		RN = rN;
	}
	public int getPowerNeed() {
		return powerNeed;
	}
	public void setPowerNeed(int powerNeed) {
		this.powerNeed = powerNeed;
	}
	public Date getStorageTime() {
		return storageTime;
	}
	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}
	@Override
	public String toString() {
		return "BookModel [ISBN=" + ISBN + ", name=" + name + ", introduction=" + introduction + ", author=" + author
				+ ", press=" + press + ", TN=" + TN + ", RN=" + RN + ", powerNeed=" + powerNeed + ", storageTime="
				+ storageTime + ", bookType=" + bookType + "]";
	}
	

}
