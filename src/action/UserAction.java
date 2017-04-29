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
 * �û�������
 * ����ʱ�����¼��Ϣ
 * ֻ���ڵ�¼ʱ�����û���Ϣ���˺󲻵ø���
 * ��������ֻ��ʹ��һ���û�
 * 
 * @author ��ΰ
 *
 */

public class UserAction {

    public UserModel user;
	/**
	 * �û���¼���� View�����
	 * ��ѯ�ļ����Ƿ��д��û�
	 * ����������ʾ��¼�ɹ���ʧ��TODO
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
	 * �����б��е�����
	 */
	public boolean borrowBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		return bookFormOp.borrowBook(bookISBN);//�����ڴ��е�ͼ���
	}
	
	//ԤԼͼ��
	public boolean orderBook(String bookISBN){
		return false;
	}
	
	public boolean cancelOrder(String bookISBN){
		return true;
	}
	
	//�黹ͼ��
	public boolean returnBook(String bookISBN){
		return true;
	}
	
	//����ͼ��
	public List<BookModel> searchBook(String keyWord,String Type){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		int type=0;
		switch (Type){
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
	
	//�õ������
	public List<BorrowBookModel> getBorrowBook(){
		
		return FileTest.getListBBM();//��������
	}
	
	//�õ�ԤԼ��
	public List<OrderBookModel> getOrderBook() {

		return FileTest.getListOBM();// ��������
	}
	
	//�õ�������ʷ��
	public List<BBHModel> getBorrowHistory(){
		return FileTest.getListBBH();
	}
	
	//�õ���Ϣ֪ͨ
	public List<InfoModel> getInfo(){
		return null;
	}
}
