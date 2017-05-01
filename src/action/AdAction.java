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
	//����ģʽ
	public static AdAction adAction;
	public static AdAction getInstance(){
		if(adAction==null){
			adAction = new AdAction();
		}
		return adAction;
	}

	//¼��ͼ��
	public boolean inputBook(BookModel book){
		BookFormOp bookFormOp = BookFormOp.getInstance();
		return bookFormOp.inputOne(book);
	}
	
	//ɾ��ͼ��
	public boolean delBook(String ISBN){
		BookFormOp bookFormOp = BookFormOp.getInstance();
		return bookFormOp.delOne(ISBN);
	}
	
	//����һ��ͼ��
	public boolean updateBook(BookModel book){
		BookFormOp bookFormOp = BookFormOp.getInstance();
		return bookFormOp.updateOne(book);
	}
	
	//����ͼ��
	public List<BookModel> searchBook(String keyWord, String searchType) {
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		int type=0;
		switch (searchType){
			case "ISBN":
				type = SearchTypeFeedback.BOOK_ISBN;
				break;
			case "����":
				type = SearchTypeFeedback.BOOK_NAME;
				break;
			case "������":
				type = SearchTypeFeedback.BOOK_PRESS;
				break;
			case "����":
				type = SearchTypeFeedback.BOOK_AUTHOR;
				break;
			case "������":
				type = SearchTypeFeedback.BOOK_TYPE;
				break;
		}
		List<BookModel> lists = bookFormOp.searchBookForm(keyWord ,type);
		if(lists==null)
			System.out.println("δ�ҵ�ͼ��");
		return lists;
	}
	
	//�õ�ԤԼ�� 
	public List<OrderBookModel> getAllOrderBook(){
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.obLists;// ��������
	}
	//�õ������
	public List<BorrowBookModel> getAllBorrowBook(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.bbLists;
	}
	
	//�����û��黹ͼ�� TODO
	public boolean remindUser(String ID,String ISBN){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.addReturnInfo(ID,ISBN);
	}
	
	//¼���û�
	public boolean inputUser(UserModel userModel){
		return true;
	}
	//�����û�
	public List<UserModel> searchUser(String keyWord, String searchType){
		UserFormOp userFormOp =  UserFormOp.getInstance();
		int type=0;
		switch (searchType){
			case "ID":
				type = SearchTypeFeedback.USER_ID;
				break;
			case "����":
				type = SearchTypeFeedback.USER_NAME;
				break;
			case "ѧԺ":
				type = SearchTypeFeedback.USER_SCHOOL;
			    break;
		}
		List<UserModel> lists = userFormOp.searchUserList(keyWord ,type);
		if(lists==null)
			System.out.println("δ�ҵ�ͼ��");
		return lists;
	}
	
	//�鿴�����û�
	public List<UserModel> recentUser(){
		return null;
	}
	
	//ɾ���û�
	public boolean delUser(String ID){
		return true;
	}
	//������Ϣ����
	public boolean sendMessage(String message,String ID){
		return true;
	}
}
