package action;

import java.util.List;


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

    private static UserModel user; 
	/**
	 * �û���¼���� View�����
	 * ��ѯ�ļ����Ƿ��д��û�
	 * ����������ʾ��¼�ɹ���ʧ��TODO
	 * @param ID
	 * @param password
	 * @return
	 * 
	 */	
    
    public UserAction(){
    	user = UserModel.getIstance();
    }
    
    public static UserAction userAction=null;
    
    public static UserAction getInstance(){
    	if(userAction==null){
    		userAction =new UserAction();
    	}
    	return userAction;
    }
	
	/**
	 * �������в�������
	 * �޸�ͼ���
	 * @return
	 */
	//����
	public boolean borrowBook(String bookISBN){
		
		return true;
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
		
		return FileTest.getListBM();
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
