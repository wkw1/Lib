package fileOpreation;

import dao.BookDao;
import db.SearchTypeFeedback;
import model.BookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/29.
 * 图书表操作类
 * 1，查询图书列表
 * 2, 借书操作
 */
public class BookFormOp {

    public List<BookModel> bookLists = new ArrayList<>();
    public BookDao bookDao;

    public static BookFormOp bookFormOp;
    public static BookFormOp getInstance(){
        if(bookFormOp==null)
           bookFormOp = new BookFormOp();
        return bookFormOp;
    }

    public BookFormOp() {
        bookDao = BookDao.getInstance();
        bookLists = bookDao.bookLists;
    }


    //借书操作找到相应的书返回
    public BookModel getOneBook(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(ISBN.equals(bookLists.get(i).getISBN())){
                bookLists.get(i).setRN(bookLists.get(i).getRN());
                return bookLists.get(i);
            }
        }
        return null;
    }

    //借书，修改此本图书的信息
    public boolean borrowBook(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(ISBN.equals(bookLists.get(i).getISBN())){
                bookLists.get(i).setRN(bookLists.get(i).getRN()-1);
                bookDao.iSModify = true;
                return true;
            }
        }
        return false;
    }

    //还书
    public boolean returnBook(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(bookLists.get(i).getISBN().equals(ISBN)){
                bookLists.get(i).setRN(bookLists.get(i).getRN()+1);
                bookDao.iSModify = true;
                return true;
            }
        }
        return false;
    }

    //删除一本图书
    public boolean delOne(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(bookLists.get(i).getISBN().equals(ISBN)){
                bookLists.remove(i);
                bookDao.iSModify = true;
                return true;
            }
        }
        return false;
    }

    //录入图书,此书已存在，直接增加数量
    public boolean inputOne(BookModel bookModel){
        System.out.println(bookModel.toString());
        bookDao.iSAdd = true;
        BookModel b = findISBN(bookModel.getISBN());
        if(b!=null){//已存在此图书，单纯的数量增加
            b.setTN(b.getTN()+bookModel.getTN());
            b.setRN(b.getRN()+bookModel.getTN());
        }
        return bookLists.add(bookModel);
    }

    //查询ISBN是否存在
    public BookModel findISBN(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(bookLists.get(i).getISBN().equals(ISBN)){
                return bookLists.get(i);
            }
        }
        return null;
    }

    //更新一本图书
    public boolean updateOne(BookModel bookModel){
        for(int i=0;i<bookLists.size();i++){
            if(bookLists.get(i).getISBN().equals(bookModel.getISBN())){
                bookLists.set(i,bookModel);
                bookDao.iSModify = true;
                return true;
            }
        }
        return false;
    }

    //搜索函数，搜索列表
    public List<BookModel> searchBookForm(String keyWords, int keyType){
        List<BookModel> searchedList=new ArrayList<BookModel>();
        int whetherExist=0;
        for(int i=0;i<bookLists.size();i++)
        {
            String target;
            switch(keyType){
                case SearchTypeFeedback.BOOK_ISBN:
                    target=bookLists.get(i).getISBN();
                    if(target!=null&&target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_NAME:
                    target=bookLists.get(i).getName();
                    if(target!=null&&target.matches("(.*)"+keyWords+"(.*)"))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_TYPE:
                    target=bookLists.get(i).getBookType();
                    if(target!=null&&target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_AUTHOR:
                    target=bookLists.get(i).getAuthor();
                    if(target!=null&&target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_PRESS:
                    target=bookLists.get(i).getPress();
                    if(target!=null&&target.matches("(.*)"+keyWords+"(.*)"))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
            }
        }
        if(whetherExist==0)
            return null;
        else
            return searchedList;
    }
}
