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

    public static BookFormOp bookFormOp;
    public static BookFormOp getInstance(){
        if(bookFormOp==null)
           bookFormOp = new BookFormOp();
        return bookFormOp;
    }

    public BookFormOp() {
        bookLists = BookDao.bookLists;
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

    public boolean borrowBook(String ISBN){
        for(int i=0;i<bookLists.size();i++){
            if(ISBN.equals(bookLists.get(i).getISBN())){
                bookLists.get(i).setRN(bookLists.get(i).getRN()-1);
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
                return true;
            }
        }
        return false;
    }

    //录入图书
    public boolean inputOne(BookModel bookModel){
        System.out.println(bookModel.toString());
        return bookLists.add(bookModel);
    }

    //更新一本图书
    public boolean updateOne(BookModel bookModel){
        for(int i=0;i<bookLists.size();i++){
            if(bookLists.get(i).getISBN().equals(bookModel.getISBN())){
                bookLists.set(i,bookModel);
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
                    if(target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_NAME:
                    target=bookLists.get(i).getName();
                    if(target.matches("(.*)"+keyWords+"(.*)"))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_TYPE:
                    target=bookLists.get(i).getBookType();
                    if(target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_AUTHOR:
                    target=bookLists.get(i).getAuthor();
                    if(target.equals(keyWords))
                    {
                        searchedList.add(bookLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_PRESS:
                    target=bookLists.get(i).getPress();
                    if(target.matches("(.*)"+keyWords+"(.*)"))
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
