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
 * 图书表的底层操作文件类
 * 将文件里的图书表读入到内存中，方便下一步的操作
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
     * 将文件读取到内存中
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
            //读取表单中所有书的信息
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
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        read.close();
        return true;
    }

     public boolean updateList(List<BookModel> bookLists){
        return true;
     }

     //将图书表重新写入文件
     public boolean writeListFile(){
         return true;
     }
}
