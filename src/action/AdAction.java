package action;

import java.util.List;

import db.SearchTypeFeedback;
import fileOpreation.BookFormOp;
import fileOpreation.FileTest;
import fileOpreation.UserFormOp;
import model.BookModel;
import model.BorrowBookModel;
import model.OrderBookModel;
import model.UserModel;

public class AdAction {
	
	public AdAction(){
		
	}
	//单例模式
	public static AdAction adAction;
	public static AdAction getInstance(){
		if(adAction==null){
			adAction = new AdAction();
		}
		return adAction;
	}
	
	//录入图书
	public boolean inputBook(BookModel book){
		FileTest.inputBook(book);
		return true;
	}
	
	//删除图书
	public boolean delBook(String ISBN){
		return true;
	}
	
	//更新一本图书
	public boolean updateBook(BookModel book){
		
		return true;
	}
	
	//搜索图书
	public List<BookModel> searchBook(String keyWord, String searchType) {
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		int type=0;
		switch (searchType){
			case "ISBN":
				type = SearchTypeFeedback.BOOK_ISBN;
				break;
			case "书名":
				type = SearchTypeFeedback.BOOK_NAME;
				break;
			case "出版社":
				type = SearchTypeFeedback.BOOK_PRESS;
				break;
			case "作者":
				type = SearchTypeFeedback.BOOK_AUTHOR;
				break;
			case "书类型":
				type = SearchTypeFeedback.BOOK_TYPE;
				break;
		}
		List<BookModel> lists = bookFormOp.searchBookForm(keyWord ,type);
		if(lists==null)
			System.out.println("未找到图书");
		return lists;
	}
	
	//得到预约表 
	public List<OrderBookModel> getAllOrderBook(){
		return FileTest.getListAllOB();
	}
	//得到借书表
	public List<BorrowBookModel> getAllBorrowBook(){
		return FileTest.getListBBM();
	}
	
	//提醒用户归还图书
	public boolean remindUser(String ID,String ISBN){
		return true;
	}
	
	//录入用户
	public boolean inputUser(UserModel userModel){
		return true;
	}
	//搜索用户
	public List<UserModel> searchUser(String keyWord, String searchType){
		UserFormOp userFormOp =  UserFormOp.getInstance();
		int type=0;
		switch (searchType){
			case "ID":
				type = SearchTypeFeedback.USER_ID;
				break;
			case "姓名":
				type = SearchTypeFeedback.USER_NMAE;
				break;
			case "学院":
				type = SearchTypeFeedback.USER_SCHOOL;
			    break;
		}
		List<UserModel> lists = userFormOp.searchUserList(keyWord ,type);
		if(lists==null)
			System.out.println("未找到图书");
		return lists;
	}
	
	//查看近期用户
	public List<UserModel> recentUser(){
		return null;
	}
	
	
	//删除用户
	public boolean delUser(String ID){
		return true;
	}
	//发送消息提醒
	public boolean sendMessage(String message,String ID){
		return true;
	}
}
