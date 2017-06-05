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
 * �û�������
 * ����ʱ�����¼��Ϣ
 * ֻ���ڵ�¼ʱ�����û���Ϣ���˺󲻵ø���
 * ��������ֻ��ʹ��һ���û�
 * @author ��ΰ
 */

public class UserAction {
	//ȫ��Ψһ�û�
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
	 * �������
	 * 1����Ҫ����ͼ���ʾ���ݣ������ʣ����-1
	 * 2�����˵Ľ�����Ϣ����������1
	 * 3�����ӽ��������
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
		return borrowBookFormOp.borrowOne(bookModel,user);//�����ڴ��е�ͼ���
	}
	
	//ԤԼͼ��,����ԤԼ������
	public boolean orderBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		BookModel bookModel = bookFormOp.getOneBook(bookISBN);
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();

		return orderBookFormOp.orderBook(bookModel,user);
	}
	//ȡ��ԤԼ
	public boolean cancelOrder(String bookISBN){
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.cancelOrder(bookISBN,user.getID());
	}
	
	//�黹ͼ��
	public boolean returnBook(String bookISBN){
		BookFormOp bookFormOp =  BookFormOp.getInstance();
		UserFormOp userFormOp = UserFormOp.getInstance();
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		if(!(orderBookFormOp.searchForOrder(bookISBN)==null)){//����ԤԼ��ͼ��
			OrderBookModel orderBookModel = orderBookFormOp.searchForOrder(bookISBN);
			SystemAction systemAction = SystemAction.getInstance();//�ǵ�¼�û��������
			if(!systemAction.borrowForOder(orderBookModel))
				return false;
		}
		else{//����ԤԼ����ͼ�����Ŀ��������
			if(!bookFormOp.returnBook(bookISBN))//ͼ����������
				return false;
		}
		if(!borrowBookFormOp.returnOne(bookISBN,user.getID()))//�����ɾ������
			return false;
		if(!userFormOp.borrowBook(user.getID(),-1))//�û��Ľ�������-1
			return false;
		//���ӽ�����ʷ
		BookModel bookModel = bookFormOp.getOneBook(bookISBN);
		if(bookModel==null){//�黹����ͼ�����û��,�������ӽ����¼ TODO
			return true;
		}
		else{
			BBHDao bbhDao = BBHDao.getInstance();
			bbhDao.addOne(bookModel,user);
		}
		return true;
	}

	//�û����飬����Ƿ����Ϣ���µ��û��Ķ������
	public boolean reduceBalance(float money){
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.reduceBalance(money,user.getID());
	}

	//�õ������
	public List<BorrowBookModel> getBorrowBook(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.searchBookForm(user.getID(),SearchTypeFeedback.USER_ID);
	}
	
	//�õ�ԤԼ��
	public List<OrderBookModel> getOrderBook() {
		OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
		return orderBookFormOp.searchBookForm(user.getID(),SearchTypeFeedback.USER_ID);
	}
	//�õ��ҵĽ�������
	public int getMyBN(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		return borrowBookFormOp.getNumberByID(user.getID());
	}
	
	//�õ�������ʷ��
	public List<BBHModel> getBorrowHistory(){
		//���ļ��ж�ȡ����
		BBHDao bbhDao = BBHDao.getInstance();
		return bbhDao.getBbhLists(user.getID());
	}
	
	//�õ���Ϣ֪ͨ
	public List<InfoModel> getInfo(){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.getInfoList(user.getID());
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

	//��������
	public boolean alterPassword(String newPassword){
		UserFormOp userFormOp = UserFormOp.getInstance();
		return userFormOp.alterPassword(user.getID(),newPassword);
	}
	
	//������Ա������Ϣ��ֵ
	public boolean recharge(float money){
		InfoFormOp infoFormOp = InfoFormOp.getInstance();
		return infoFormOp.recharge(money, user);
	}
}
