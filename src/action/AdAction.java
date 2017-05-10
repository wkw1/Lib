package action;

import java.util.List;

import db.SearchTypeFeedback;
import fileOpreation.*;
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
		BookFormOp bookFormOp = BookFormOp.getInstance();
		return bookFormOp.inputOne(book);
	}
	
	//删除图书
	public boolean delBook(String ISBN){
		BookFormOp bookFormOp = BookFormOp.getInstance();
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		borrowBookFormOp.delByISBN(ISBN);//删除借书表此ISBN的记录
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		orderBookFormOp.cancelByISBN(ISBN);//删除借书表此图书的预约记录
		return bookFormOp.delOne(ISBN);
	}
	
	//更新一本图书
	public boolean updateBook(BookModel book){
		BookFormOp bookFormOp = BookFormOp.getInstance();
		return bookFormOp.updateOne(book);
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
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.obLists;// 测试数据
	}
	//得到借书表
	public List<BorrowBookModel> getAllBorrowBook(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.bbLists;
	}
	
	//提醒用户归还图书
	public boolean remindUser(String ID,String ISBN){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.addReturnInfo(ID,ISBN);
	}
	
	//录入用户
	public boolean inputUser(UserModel userModel){
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.addOne(userModel);
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
				type = SearchTypeFeedback.USER_NAME;
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
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.getRecentUser();
	}
	
	//删除用户 预约记录取消预约
	public boolean delUser(String ID){
		OrderBookFormOp orderBookFormOp=OrderBookFormOp.getInstance();
		orderBookFormOp.cancelByID(ID);
		UserFormOp userFormOp =  UserFormOp.getInstance();
		return userFormOp.delOne(ID);
	}
	//发送消息提醒
	public boolean sendMessage(String message,String ID){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.sendOne(message,ID);
	}
}
