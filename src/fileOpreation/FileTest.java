package fileOpreation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.BBHModel;
import model.BookModel;
import model.BorrowBookModel;
import model.OrderBookModel;

public class FileTest {
	
	
	
	public static void inputBook(BookModel book){
		
	}
	
	
	public static List<Map<String, Object>> getList(){
		
		List<Map<String, Object>> lists= new ArrayList<>();
		Map<String, Object> param = null;
		
		
		for(int i=0;i<8;i++){
			param =new HashMap<>();
			param.put("name", "wkw");
			param.put("id", "2015211383");
			param.put("get", "wkw");
			param.put("set", "wkw");
			param.put("what", "wkw");
			param.put("you", "wkw");
			param.put("i", i);
			param.put("he", 5);

			lists.add(param);
		}
		
		return lists;
	}
	
	
	public static List<OrderBookModel> getListOBM() {

		List<OrderBookModel> lists = new ArrayList<>();
		OrderBookModel obm = new OrderBookModel();
		Date date = new Date(System.currentTimeMillis());

		for (int i = 0; i < 25; i++) {
			obm = new OrderBookModel();
			//bbm.setAIBook(i + 1);
			obm.setBookAuthor("wkw");
			obm.setBookISBN("ISBN"+i);
			obm.setBookName("计算机组成原理");
			obm.setOrderDate(date);
			obm.setID("2015211383");
			obm.setName("魏宽伟");
			//bbm.setRTBook(i);
			lists.add(obm);
			
			System.out.println(obm.hashCode());
			
			
		}

		return lists;
	}
	
	public static List<BBHModel> getListBBH() {

		List<BBHModel> lists = new ArrayList<>();
		BBHModel obm = new BBHModel();
		Date date = new Date(System.currentTimeMillis());

		for (int i = 0; i < 25; i++) {
			obm = new BBHModel();
			//bbm.setAIBook(i + 1);
			obm.setBookAuthor("zuozge");
			obm.setBorrowDate(date);
			obm.setBookISBN("ISBN"+i);
			obm.setBookName("计算机组成原理");
			obm.setReturnDate(date);
			obm.setID("2015211383");
			obm.setName("魏宽伟");
			//bbm.setRTBook(i);
			lists.add(obm);
		}

		return lists;
	}
	
	
	
	public static List<BorrowBookModel> getListBBM() {

		List<BorrowBookModel> lists = new ArrayList<>();
		BorrowBookModel bbm = new BorrowBookModel();
		Date date = new Date(System.currentTimeMillis());

		for (int i = 0; i < 20; i++) {
			bbm = new BorrowBookModel();
			bbm.setAIBook(i + 1);
			bbm.setBookAuthor("wkw");
			bbm.setBookISBN("3243535");
			bbm.setBookName("计算机组成原理");
			bbm.setBorrowDate(date);
			bbm.setID("2015211383");
			bbm.setName("魏宽伟");
			bbm.setRTBook(i);
			lists.add(bbm);
		}

		return lists;
	}
	
	public static List<BookModel> getListBM() {

		List<BookModel> lists = new ArrayList<>();
		BookModel bm = new BookModel();
		Date date = new Date(System.currentTimeMillis());

		for (int i = 0; i < 300; i++) {
			bm = new BookModel();
			bm.setAuthor("wkw");;
			bm.setIntroduction("jidjisajfdsafdanfjdjanfjdafjdka fdahjhdjafndan nfdjajfhdja");
			bm.setISBN("ISBN"+i);
			bm.setName("shuming");
			bm.setPowerNeed(3);
			bm.setPress("chubanshe");
			bm.setRN(3);
			bm.setStorageTime(date);
			bm.setTN(i);
			lists.add(bm);
		}

		return lists;
	}
	
	public static List<OrderBookModel> getListAllOB() {

		List<OrderBookModel> lists = new ArrayList<>();
		OrderBookModel obm =null;
		Date date = new Date(System.currentTimeMillis());

		for (int i = 0; i < 300; i++) {
			obm = new OrderBookModel();
			//bbm.setAIBook(i + 1);
			obm.setBookAuthor("wkw");
			obm.setBookISBN("ISBN"+i);
			obm.setBookName("计算机组成原理");
			obm.setOrderDate(date);
			obm.setID("2015211383");
			obm.setName("魏宽伟");
			lists.add(obm);
		}

		return lists;
	}
	
	
	
	
	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static List<String> readFileByLines(String fileName) {
		
		List<String> lists = new ArrayList<>();
		
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println(" 以行为单位读取文件内容，一次读一整行： ");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				lists.add(tempString);
				// 显示行号
				System.out.println(" line  " + line + " :  " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		
		return lists;
	}

}
