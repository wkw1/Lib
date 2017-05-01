package dao;

import model.BookModel;
import model.BorrowBookModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/30.
 */
public class BorrowBookDao {

    public static List<BorrowBookModel> bblists = new ArrayList<>();

    public static BorrowBookDao borrowBookDao=null;
    public static BorrowBookDao getInstance(){
        if(borrowBookDao==null){
            borrowBookDao =  new BorrowBookDao();
        }
        return borrowBookDao;
    }

    /**
     * ���ļ���ȡ���ڴ���
     * @return
     */
    public boolean readBookForm() throws IOException, ParseException {
        String filePath="file//borrowbookform.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        BorrowBookModel bookInfo;
        int bookNum=0;
        try {
            //��ȡ�������������Ϣ
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                bookInfo=new BorrowBookModel();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            bookInfo.setName(Info);
                            i=i+1;
                            break;
                        case 2:
                            bookInfo.setID(Info);
                            i=i+1;
                            break;
                        case 3:
                            bookInfo.setBookName(Info);
                            i=i+1;
                            break;
                        case 4:
                            bookInfo.setBookISBN(Info);
                            i=i+1;
                            break;
                        case 5:
                            bookInfo.setBookAuthor(Info);
                            i=i+1;
                            break;
                        case 6:
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date =sdf.parse(Info);
                            java.sql.Date date1 = new java.sql.Date(date.getTime());
                            bookInfo.setBorrowDate(date1);
                            i=i+1;
                            break;
                        case 7:
                            bookInfo.setRTBook(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 8:
                            bookInfo.setAIBook(Float.parseFloat(Info));
                            i=i+1;
                            break;
                    }
                }
                bblists.add(bookInfo);
                bookNum=bookNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
        read.close();
        return true;
    }
}
