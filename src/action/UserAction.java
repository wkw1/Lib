package action;

import java.util.List;

import dao.BBHDao;
import db.SearchTypeFeedback;
import fileOpreation.*;
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
 * @author 宽伟
 */

public class UserAction {
	//全局唯一用户
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
	 * 借书操作
	 * 1，需要更改图书表示数据，此书的剩余量-1
	 * 2，此人的借书信息，借书量加1
	 * 3，增加借书表数据
	 */
	public boolean borrowBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		UserFormOp userFormOp = UserFormOp.getInstance();
		if (!bookFormOp.borrowBook(bookISBN))
			return false;
		if(!userFormOp.borrowBook(user.getID(),1))
			return false;
		BookModel bookModel = bookFormOp.getOneBook(bookISBN);
		return borrowBookFormOp.borrowOne(bookModel,user);//更改内存中的图书表
	}
	
	//预约图书,增加预约表数据
	public boolean orderBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		BookModel bookModel = bookFormOp.getOneBook(bookISBN);
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();

		return orderBookFormOp.orderBook(bookModel,user);
	}
	//取消预约
	public boolean cancelOrder(String bookISBN){
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.cancelOrder(bookISBN,user.getID());
	}
	
	//归还图书
	public boolean returnBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		UserFormOp userFormOp = UserFormOp.getInstance();
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		if(!(orderBookFormOp.searchForOrder(bookISBN)==null)){//有人预约此图书
			OrderBookModel orderBookModel = orderBookFormOp.searchForOrder(bookISBN);
			SystemAction systemAction = SystemAction.getInstance();//非登录用户借书操作
			if(!systemAction.borrowForOder(orderBookModel))
				return false;
		}
		else{//有人预约，此图书的数目不做更改
			if(!bookFormOp.returnBook(bookISBN))//图书表更改数据
				return false;
		}
		if(!borrowBookFormOp.returnOne(bookISBN,user.getID()))//借书表删除数据
			return false;
		if(!userFormOp.borrowBook(user.getID(),-1))//用户的借书数量-1
			return false;
		//增加借书历史
		BookModel bookModel = bookFormOp.getOneBook(bookISBN);
		if(bookModel==null){//归还的书图书表中没有,不再增加借书记录 TODO
			return true;
		}
		else{
			BBHDao bbhDao = BBHDao.getInstance();
			bbhDao.addOne(bookModel,user);
		}
		return true;
	}

	//用户还书，此书欠费信息更新到用户的额余额中
	public boolean reduceBalance(float money){
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.reduceBalance(money,user.getID());
	}

	//得到借书表
	public List<BorrowBookModel> getBorrowBook(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.searchBookForm(user.getID(),SearchTypeFeedback.USER_ID);
	}
	
	//得到预约表
	public List<OrderBookModel> getOrderBook() {
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.searchBookForm(user.getID(),SearchTypeFeedback.USER_ID);
	}
	//得到我的借书数量
	public int getMyBN(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.getNumberByID(user.getID());
	}
	
	//得到借书历史表
	public List<BBHModel> getBorrowHistory(){
		//从文件中读取数据
		BBHDao bbhDao = BBHDao.getInstance();
		return bbhDao.getBbhLists(user.getID());
	}
	
	//得到消息通知
	public List<InfoModel> getInfo(){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.getInfoList(user.getID());
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

	//更改密码
	public boolean alterPassword(String newPassword){
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.alterPassword(user.getID(),newPassword);
	}
	
	//给管理员发送消息充值
	public boolean recharge(float money){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.recharge(money, user);
	}
}
