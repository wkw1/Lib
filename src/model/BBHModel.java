package model;

import java.sql.Date;

/**
 * ������ʷ��
 * @author ��ΰ
 *
 */
public class BBHModel {
	private String name;//�����ߵ�����
	private String ID;//�����ߵ�ID
	private String bookName;
	private String bookISBN;
	private String bookAuthor;
	private Date borrowDate;//����ʱ��
	private Date returnDate;//����ʱ��

	public BBHModel(){}

	public BBHModel(String name, String ID, String bookName, String bookISBN, String bookAuthor, Date borrowDate, Date returnDate) {
		this.name = name;
		this.ID = ID;
		this.bookName = bookName;
		this.bookISBN = bookISBN;
		this.bookAuthor = bookAuthor;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	

}
