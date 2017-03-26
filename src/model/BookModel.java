package model;

import java.sql.Date;

/**
 * 图书模型类
 * 存放图书的所有属性
 * @author 宽伟
 *
 */

public class BookModel {
	private String ISBN;//书的编号
	private String name;//书名
	private String introduction;//简介
	private String author;//作者
	private String press;//出版社
	private int TN;//图书总数量
	private int RN;//图书剩余数量
	private int powerNeed;//借书所需权限
	private Date storageTime;//图书入库时间
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
