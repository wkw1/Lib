package action;

import java.util.List;

import db.SearchTypeFeedback;
import fileOpreation.BookFormOp;
import fileOpreation.FileTest;
import model.BBHModel;
import model.BookModel;
import model.BorrowBookModel;
import model.InfoModel;
import model.OrderBookModel;
import model.UserModel;

/**
 * 用户操作类
 * 创建时传入登录信息
 * 只能在登录时传入用户信息，此后不得更改
 * 整个程序只能使用一个用户
 * 
 * @author 宽伟
 *
 */

public class UserAction {

    public UserModel user;
	/**
	 * 用户登录方法 View层调用
	 * 查询文件看是否有此用户
	 * 返回整数表示登录成功或失败TODO
	 */	
    
    public UserAction(){
    	user = UserModel.userModel;
    }
    
    public static UserAction userAction=null;
    
    public static UserAction getInstance(){
    	if(userAction==null){
    		userAction =new UserAction();
    	}
    	return userAction;
    }
	
	/**
	 * 更改列表中的数据
	 */
	public boolean borrowBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		return bookFormOp.borrowBook(bookISBN);//更改内存中的图书表
	}
	
	//预约图书
	public boolean orderBook(String bookISBN){
		return false;
	}
	
	public boolean cancelOrder(String bookISBN){
		return true;
	}
	
	//归还图书
	public boolean returnBook(String bookISBN){
		return true;
	}
	
	//搜索图书
	public List<BookModel> searchBook(String keyWord,String Type){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		int type=0;
		switch (Type){
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
	
	//得到借书表
	public List<BorrowBookModel> getBorrowBook(){
		
		return FileTest.getListBBM();//测试数据
	}
	
	//得到预约表
	public List<OrderBookModel> getOrderBook() {

		return FileTest.getListOBM();// 测试数据
	}
	
	//得到借书历史表
	public List<BBHModel> getBorrowHistory(){
		return FileTest.getListBBH();
	}
	
	//得到消息通知
	public List<InfoModel> getInfo(){
		return null;
	}
}
