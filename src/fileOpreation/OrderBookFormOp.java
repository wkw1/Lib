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
 * Created by ��ΰ on 2017/4/30.
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

    //��ѯԤԼ���Ƿ���������ԤԼĳͼ��
    public OrderBookModel searchForOrder(String ISBN){
        for(OrderBookModel model:obLists){
            if(model.getBookISBN().equals(ISBN)){
                return model;
            }
        }
        return null;
    }

    //ԤԼͼ��,ԤԼ�б�������Ϣ TODO �ж��ظ�ԤԼ
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
    //ȡ��ԤԼ
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

    //ȡ��һ���û�������ԤԼ
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

    //ȡ��һ��ISBN������ԤԼ���˱��鱻ɾ���ˣ���
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

    //���������������б�
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
