package fileOpreation;

import java.util.ArrayList;
import java.util.List;

import model.BBHModel;
import model.BookModel;
import model.BorrowBookModel;
import model.OrderBookModel;
/**
 * 文件操作类
 * 所有文件的操作都要经过这个类
 * 系统启动（或执行命令）从文件中读入数据
 * 当产生更改数据的行为，启动命令更改数据并存入文件
 * @author 宽伟
 *
 */
public class FileDao {
	
	private List<BookModel> bookList = null;//存放文件里的书
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
	 * 图书表系列操作
	 * 增删改查
	 * 增加一本图书 （管理员发出命令） --addBookList(BookModel bookModel)
	 * 删除一本图书 （管理员发出命令） --delBookFromBooklist(BookModel bookModel)
	 * 更改一本图书的部分属性 （管理员发出直接更改命令，用户借书还书间接导致图书在架数目发生改动）
	 *       --alterBookInBookList(BookModel bookModel)
	 * 查询部分图书（管理员和用户都可查询，根据查询的关键字返回对应的部分图书表
	 *       --searchBook(String type,String keyWord)
	 */
	
	public List<BookModel> getBookList() {
		return bookList;
	}
	//从文件中图书表数据，并存入bookList中
	public void setBookList() {
		//TODO 
	}
	
	public boolean addBookToBookList(BookModel bookModel){
		
		bookList.add(bookModel);
		
		//TODO 修改后存入文件或直接在文件中更改数据
		return true;
	}
	
	public boolean delBookFromBooklist(BookModel bookModel){
		int listSize = bookList.size();
		String ISBN =bookModel.getISBN();
		int i=0;
		for (i = 0; i < listSize && bookList.get(i).getISBN() != ISBN; i++) {

		}
		bookList.remove(i);
		//TODO 修改后存入文件或直接在文件中更改数据
		return true;
	}
	
	public boolean alterBookInBookList(BookModel bookModel){
		int listSize = bookList.size();
		String ISBN =bookModel.getISBN();
		int i=0;
		for(i=0;i<listSize&&bookList.get(i).getISBN()!=ISBN;i++){
			
		}
		bookList.set(i, bookModel);
		//TODO 修改后存入文件或直接在文件中更改数据
		
		return true;
	}
	
	public List<BookModel> searchBook(String type,String keyWord){
		// TODO listBook中查询数据
		List<BookModel> list = new ArrayList<>();
		int listSize=0;
		listSize = bookList.size();
		switch (type) {
		case "ISBN":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getISBN().equals(keyWord))
					list.add(bookList.get(i));
			break;
		case "书名":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getName().equals(keyWord))
					list.add(bookList.get(i));
			break;

		case "作者":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getAuthor().equals(keyWord))
					list.add(bookList.get(i));
			break;
		case "出版社":
			for(int i=0;i<listSize;i++)
				if(bookList.get(i).getPress().equals(keyWord))
					list.add(bookList.get(i));
			break;

		default:
			break;
		}
		return list;
	}
	
	
	/**
	 * 借书表各种操作
	 * 从文件中读取所有的借书数据存放到borrowBookList中  --setBorrowBookList()
	 * 增加借书记录（用户借书，向借书表增加一条借书记录）--addBorrowInfo(BorrowBookModel borrowBookModel)
	 * 删除借书记录（用户归还一本书删除此条记录） --delBorrowRecord(BorrowBookModel borrowBookModel)
	 * 更改借书时间（系统随着时间的变化，每过一天增加每一条借书记录的已借书时间和产生的费用，系统线程更改）
	 *       --alterBorrowRecord()
	 * 查询特定用户的所借图书（用户查看自己的结束记录，根据用户的ID准确查询） --searchUserRecord(String ID)
	 */
	
	public List<BorrowBookModel> getBorrowBookList() {
		return borrowBookList;
	}
	
	public void setBorrowBookList() {
		//TODO 从文件中将所有借书信息存到borrowBookList列表中
		
	}
	public boolean addBorrowInfo(BorrowBookModel borrowBookModel){
		borrowBookList.add(borrowBookModel);
		return true;
	}
	public boolean delBorrowRecord(BorrowBookModel borrowBookModel){
		int listSize = borrowBookList.size();
		int i=0;
		for(i=0;i<listSize;i++){
			if(borrowBookList.get(i).getID()==borrowBookModel.getID()
					&&borrowBookList.get(i).getBookISBN()==borrowBookModel.getBookISBN()){
				borrowBookList.remove(i);
				break;
			}
		}
		return true;
	}
	
	public boolean alterBorrowRecord(){
		int listSize = borrowBookList.size();
		int i=0;
		for(i=0;i<listSize;i++){
			int rTime;
			rTime =borrowBookList.get(i).getRTBook();
			borrowBookList.get(i).setRTBook(rTime--);
			if(rTime>=0){
				borrowBookList.get(i).setAIBook((float) (-rTime*0.2));
			}
		}
		return true;
	}
	
	public List<BorrowBookModel> searchUserRecord(String ID){
		List<BorrowBookModel>list = new ArrayList<>();
		int listSize = borrowBookList.size();
		int i=0;
		for(i=0;i<listSize;i++){
			if(borrowBookList.get(i).getID()==ID)
				list.add(borrowBookList.get(i));
		}
		return list;
	}
	
	
	
	/**
	 * TODO ...
	 * 
	 * 预约表类似上面两个表
	 * 
	 * 
	 */
	
	
	public List<OrderBookModel> getOrderBookList() {
		return orderBookList;
	}
	
	public void setOrderBookList() {
	}
	/**
	 * 
	 */
	
	public List<BBHModel> getBBHList() {
		return BBHList;
	}
	
	public void setBBHList() {
	}
	
	

}
