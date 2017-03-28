package fileOpreation;

import java.util.ArrayList;
import java.util.List;

import model.BBHModel;
import model.BookModel;
import model.BorrowBookModel;
import model.OrderBookModel;
/**
 * �ļ�������
 * �����ļ��Ĳ�����Ҫ���������
 * ϵͳ��������ִ��������ļ��ж�������
 * �������������ݵ���Ϊ����������������ݲ������ļ�
 * @author ��ΰ
 *
 */
public class FileDao {
	
	private List<BookModel> bookList = null;//����ļ������
	private List<BorrowBookModel> borrowBookList=null;
	private List<OrderBookModel> orderBookList=null;
	private List<BBHModel> BBHList=null;
	
	public static FileDao fileDao =null;
	public static FileDao getInstance(){
		if(fileDao==null){
			fileDao=new FileDao();
		}
		return fileDao;
	}
	
	/**
	 * ͼ���ϵ�в���
	 * ��ɾ�Ĳ�
	 * ����һ��ͼ�� ������Ա������� --addBookList(BookModel bookModel)
	 * ɾ��һ��ͼ�� ������Ա������� --delBookFromBooklist(BookModel bookModel)
	 * ����һ��ͼ��Ĳ������� ������Ա����ֱ�Ӹ�������û����黹���ӵ���ͼ���ڼ���Ŀ�����Ķ���
	 *       --alterBookInBookList(BookModel bookModel)
	 * ��ѯ����ͼ�飨����Ա���û����ɲ�ѯ�����ݲ�ѯ�Ĺؼ��ַ��ض�Ӧ�Ĳ���ͼ���
	 *       --searchBook(String type,String keyWord)
	 */
	
	public List<BookModel> getBookList() {
		return bookList;
	}
	//���ļ���ͼ������ݣ�������bookList��
	public void setBookList() {
		//TODO 
	}
	
	public boolean addBookToBookList(BookModel bookModel){
		
		bookList.add(bookModel);
		
		//TODO �޸ĺ�����ļ���ֱ�����ļ��и�������
		return true;
	}
	
	public boolean delBookFromBooklist(BookModel bookModel){
		int listSize = bookList.size();
		String ISBN =bookModel.getISBN();
		int i=0;
		for (i = 0; i < listSize && bookList.get(i).getISBN() != ISBN; i++) {

		}
		bookList.remove(i);
		//TODO �޸ĺ�����ļ���ֱ�����ļ��и�������
		return true;
	}
	
	public boolean alterBookInBookList(BookModel bookModel){
		int listSize = bookList.size();
		String ISBN =bookModel.getISBN();
		int i=0;
		for(i=0;i<listSize&&bookList.get(i).getISBN()!=ISBN;i++){
			
		}
		bookList.set(i, bookModel);
		//TODO �޸ĺ�����ļ���ֱ�����ļ��и�������
		
		return true;
	}
	
	public List<BookModel> searchBook(String type,String keyWord){
		// TODO listBook�в�ѯ����
		List<BookModel> list = new ArrayList<>();
		int listSize=0;
		listSize = bookList.size();
		switch (type) {
		case "ISBN":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getISBN().equals(keyWord))
					list.add(bookList.get(i));
			break;
		case "����":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getName().equals(keyWord))
					list.add(bookList.get(i));
			break;

		case "����":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getAuthor().equals(keyWord))
					list.add(bookList.get(i));
			break;
		case "������":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getPress().equals(keyWord))
					list.add(bookList.get(i));
			break;

		default:
			break;
		}
		return list;
	}
	
	
	
	public List<BorrowBookModel> getBorrowBookList() {
		return borrowBookList;
	}
	
	public void setBorrowBookList() {
		
	}
	
	public List<OrderBookModel> getOrderBookList() {
		return orderBookList;
	}
	
	public void setOrderBookList() {
	}
	
	public List<BBHModel> getBBHList() {
		return BBHList;
	}
	
	public void setBBHList() {
	}
	
	

}
