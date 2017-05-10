package model;

import java.sql.Date;

/**
 * 借书历史类
 * @author 宽伟
 *
 */
public class BBHModel {
	private String name;//借书者的姓名
	private String ID;//借书者的ID
	private String bookName;
	private String bookISBN;
	private String bookAuthor;
	private Date borrowDate;//借书时间
	private Date returnDate;//还书时间

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
