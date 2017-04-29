package dao;

import model.BookModel;

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
 * ͼ���ĵײ�����ļ���
 * ���ļ����ͼ�����뵽�ڴ��У�������һ���Ĳ���
 *
 */
public class BookDao {

    public static List<BookModel> bookLists = new ArrayList<>();

    public static BookDao bookDao=null;
    public static BookDao getInstance(){
        if(bookDao==null){
            bookDao =  new BookDao();
        }
        return bookDao;
    }

    /**
     * ���ļ���ȡ���ڴ���
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public boolean readBookForm() throws IOException, ParseException {
        String filePath="file//bookform.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        BookModel bookInfo;
        int bookNum=0;
        try {
            //��ȡ�������������Ϣ
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                bookInfo=new BookModel();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            bookInfo.setISBN(Info);
                            i=i+1;
                            break;
                        case 2:
                            bookInfo.setName(Info);
                            i=i+1;
                            break;
                        case 3:
                            bookInfo.setIntroduction(Info);
                            i=i+1;
                            break;
                        case 4:
                            bookInfo.setBookType(Info);
                            i=i+1;
                            break;
                        case 5:
                            bookInfo.setAuthor(Info);
                            i=i+1;
                            break;
                        case 6:
                            bookInfo.setPress(Info);
                            i=i+1;
                            break;
                        case 7:
                            bookInfo.setTN(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 8:
                            bookInfo.setRN(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 9:
                            bookInfo.setPowerNeed(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 10:
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date =sdf.parse(Info);
                            java.sql.Date date1 = new java.sql.Date(date.getTime());
                            bookInfo.setStorageTime(date1);
                            i=i+1;
                            break;
                    }
                }
                bookLists.add(bookInfo);
                bookNum=bookNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
        read.close();
        return true;
    }

     public boolean updateList(List<BookModel> bookLists){
        return true;
     }

     //��ͼ�������д���ļ�
     public boolean writeListFile(){
         return true;
     }
}
