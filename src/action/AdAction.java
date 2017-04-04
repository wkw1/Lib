package action;

import java.util.List;

import fileOpreation.FileTest;
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
		FileTest.inputBook(book);
		return true;
	}
	
	//ɾ��ͼ��
	public boolean delBook(String ISBN){
		return true;
	}
	
	//����һ��ͼ��
	public boolean updateBook(BookModel book){
		
		return true;
	}
	
	//����ͼ��
	public List<BookModel> searchBook(String keyWord, String searchType) {
		// TODO Auto-generated method stub
		return FileTest.getListBM();
	}
	
	//�õ�ԤԼ�� 
	public List<OrderBookModel> getAllOrderBook(){
		return FileTest.getListAllOB();
	}
	//�õ������
	public List<BorrowBookModel> getAllBorrowBook(){
		return FileTest.getListBBM();
	}
	
	//�����û��黹ͼ��
	public boolean remindUser(String ID,String ISBN){
		return true;
	}
	
	//¼���û�
	public boolean inputUser(UserModel userModel){
		return true;
	}
	//�����û�
	public List<UserModel> searchUser(String keyWord, String searchType){
		return FileTest.getListUM();
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
