package fileOpreation;

import dao.BorrowBookDao;
import db.SearchTypeFeedback;
import model.*;
import view.SystemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/30.
 * ��������ݲ���
 */
public class BorrowBookFormOp {

    public List<BorrowBookModel> bbLists = new ArrayList<>();

    public static BorrowBookFormOp borrowBookFormOp;

    public static BorrowBookFormOp getInstance() {
        if (borrowBookFormOp == null)
            borrowBookFormOp = new BorrowBookFormOp();
        return borrowBookFormOp;
    }

    public BorrowBookFormOp() {
        bbLists = BorrowBookDao.bblists;
    }

    /**
     * ��������һ�������е������鼮��ʣ�����ʱ���һ
     * ���ʱ��Ϊ�����������������
     *
     * @param days ����������
     */
    public void dateGrowth(int days) {
        for (int i = 0; i < bbLists.size(); i++) {
            int an = bbLists.get(i).getRTBook();
            bbLists.get(i).setRTBook(an - days);
            if (an - days < 0)
                bbLists.get(i).setAIBook((float) (-(an - days) * 0.2));
        }
    }

    //���飬���������һ����¼
    public boolean borrowOne(BookModel bookModel, UserModel userModel) {
        BorrowBookModel borrowBookModel = new BorrowBookModel();
        borrowBookModel.setAIBook(0);
        borrowBookModel.setRTBook(30);
        borrowBookModel.setName(userModel.getName());
        borrowBookModel.setBorrowDate(SystemEntry.date);
        borrowBookModel.setBookAuthor(bookModel.getAuthor());
        borrowBookModel.setBookName(bookModel.getName());
        borrowBookModel.setID(userModel.getID());
        borrowBookModel.setBookISBN(bookModel.getISBN());

        return bbLists.add(borrowBookModel);
    }

    //ԤԼ���飬���������һ����¼
    public boolean borrowForOrder(OrderBookModel model) {
        BorrowBookModel borrowBookModel = new BorrowBookModel();
        borrowBookModel.setAIBook(0);
        borrowBookModel.setRTBook(30);
        borrowBookModel.setName(model.getName());
        borrowBookModel.setBorrowDate(SystemEntry.date);
        borrowBookModel.setBookAuthor(model.getBookAuthor());
        borrowBookModel.setBookName(model.getBookName());
        borrowBookModel.setID(model.getID());
        borrowBookModel.setBookISBN(model.getBookISBN());

        return bbLists.add(borrowBookModel);
    }

    //���飬ɾ��һ����¼
    public boolean returnOne(String ISBN, String ID) {
        for (int i = 0; i < bbLists.size(); i++) {
            if (bbLists.get(i).getID().equals(ID) && bbLists.get(i).getBookISBN().equals(ISBN)) {
                bbLists.remove(i);
                return true;
            }
        }
        return false;
    }

    //ͳ��һ���û��ڽ���������ݲ�����Ƿ����Ϣ
    public List<Balance> getBalanceList() {
        List<Balance> lists = new ArrayList<>();
        for (BorrowBookModel borrowBookModel : bbLists) {
            if (borrowBookModel.getAIBook() > 0) {//��Ƿ��
                int j;
                for (j = 0; j < lists.size(); j++) {
                    if (lists.get(j).ID.equals(borrowBookModel.getID())) {
                        lists.get(j).balance += borrowBookModel.getAIBook();
                        break;
                    }
                }
                if (j == lists.size()) {//��δ�������û�����Ϣ
                    lists.add(new Balance(borrowBookModel.getID(), borrowBookModel.getAIBook()));
                }
            }
        }
        return lists;
    }

    //���������������б�
    public List<BorrowBookModel> searchBookForm(String keyWords, int keyType) {
        List<BorrowBookModel> searchedList = new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < bbLists.size(); i++) {
            String target;
            switch (keyType) {
                case SearchTypeFeedback.BOOK_ISBN:
                    target = bbLists.get(i).getBookISBN();
                    if (target.equals(keyWords)) {
                        searchedList.add(bbLists.get(i));
                        whetherExist = 1;
                    }
                    break;
                case SearchTypeFeedback.BOOK_NAME:
                    target = bbLists.get(i).getBookName();
                    if (target.matches("(.*)" + keyWords + "(.*)")) {
                        searchedList.add(bbLists.get(i));
                        whetherExist = 1;
                    }
                    break;
                case SearchTypeFeedback.USER_ID:
                    target = bbLists.get(i).getID();
                    if (target.equals(keyWords)) {
                        searchedList.add(bbLists.get(i));
                        whetherExist = 1;
                    }
                    break;
                case SearchTypeFeedback.USER_NAME:
                    target = bbLists.get(i).getName();
                    if (target.equals(keyWords)) {
                        searchedList.add(bbLists.get(i));
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
