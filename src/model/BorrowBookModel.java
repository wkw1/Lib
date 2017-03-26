package model;

import java.sql.Date;

public class BorrowBookModel {
	private String name;//借书者的姓名
	private String ID;//借书者的ID
	private String bookName;
	private String bookISBN;
	private String bookAuthor;
	private Date borrowDate;//借书时间
	private int RTBook;//剩余借书时间
	private int AIBook;//借此本书产生的欠费信息
	
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
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
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public int getRTBook() {
		return RTBook;
	}
	public void setRTBook(int rTBook) {
		RTBook = rTBook;
	}
	public int getAIBook() {
		return AIBook;
	}
	public void setAIBook(int aIBook) {
		AIBook = aIBook;
	}
	
	

}
