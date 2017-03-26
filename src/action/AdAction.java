package action;

import java.util.List;

import fileOpreation.FileTest;
import model.BookModel;
import model.BorrowBookModel;
import model.OrderBookModel;

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
		// TODO Auto-generated method stub
		return FileTest.getListBM();
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

}
