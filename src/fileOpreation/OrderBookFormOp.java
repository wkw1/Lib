package fileOpreation;

import dao.OrderBookDao;
import db.SearchTypeFeedback;
import model.BookModel;
import model.OrderBookModel;
import model.UserModel;
import view.SystemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 */
public class OrderBookFormOp {
    public List<OrderBookModel> obLists= new ArrayList<>();
    public OrderBookDao orderBookDao;

    public static OrderBookFormOp orderBookFormOp;

    public static OrderBookFormOp getInstance() {
        if (orderBookFormOp == null)
            orderBookFormOp = new OrderBookFormOp();
        return orderBookFormOp;
    }

    public OrderBookFormOp() {
        orderBookDao = OrderBookDao.getInstance();
        obLists = orderBookDao.obLists;
    }

    //查询预约表是否有人有人预约某图书
    public OrderBookModel searchForOrder(String ISBN){
        for(OrderBookModel model:obLists){
            if(model.getBookISBN().equals(ISBN)){
                return model;
            }
        }
        return null;
    }

    //预约图书,预约列表增加信息 TODO 判断重复预约
    public boolean orderBook(BookModel bookModel, UserModel userModel){
        OrderBookModel orderBookModel = new OrderBookModel();
        orderBookModel.setOrderDate(SystemEntry.date);
        orderBookModel.setBookAuthor(bookModel.getAuthor());
        orderBookModel.setBookISBN(bookModel.getISBN());
        orderBookModel.setBookName(bookModel.getName());
        orderBookModel.setID(userModel.getID());
        orderBookModel.setName(userModel.getName());
        orderBookDao.iSAdd = true;
        return obLists.add(orderBookModel);
    }
    //取消预约
    public boolean cancelOrder(String ISBN,String ID){
        for(int i=0;i<obLists.size();i++){
            if(obLists.get(i).getBookISBN().equals(ISBN)&&obLists.get(i).getID().equals(ID)){
                obLists.remove(i);
                orderBookDao.iSModify=true;
                return true;
            }
        }
        return false;
    }

    //取消一个用户的所有预约
    public boolean cancelByID(String ID){
        for(int i=0;i<obLists.size();i++){
            if(obLists.get(i).getID().equals(ID)){
                obLists.remove(i);
                orderBookDao.iSModify=true;
                i--;
            }
        }
        return true;
    }

    //取消一个ISBN的所有预约，此本书被删除了！！
    public boolean cancelByISBN(String ISBN){
        for(int i=0;i<obLists.size();i++){
            if(obLists.get(i).getBookISBN().equals(ISBN)){
                obLists.remove(i);
                orderBookDao.iSModify=true;
                i--;
            }
        }
        return true;
    }

    //搜索函数，搜索列表
    public List<OrderBookModel> searchBookForm(String keyWords, int keyType) {
        List<OrderBookModel> searchedList = new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < obLists.size(); i++) {
            String target;
            switch (keyType) {
                case SearchTypeFeedback.BOOK_ISBN:
                    target = obLists.get(i).getBookISBN();
                    if (target.equals(keyWords)) {
                        searchedList.add(obLists.get(i));
                        whetherExist = 1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_NAME:
                    target = obLists.get(i).getBookName();
                    if (target.matches("(.*)" + keyWords + "(.*)")) {
                        searchedList.add(obLists.get(i));
                        whetherExist = 1;
                    }
                    break;
                case SearchTypeFeedback.USER_ID:
                    target = obLists.get(i).getID();
                    if (target.equals(keyWords)) {
                        searchedList.add(obLists.get(i));
                        whetherExist = 1;
                    }
                    break;
            }
        }
        if (whetherExist == 0)
            return null;
        else
            return searchedList;
    }


}
