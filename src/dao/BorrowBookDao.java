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
 * Created by 宽伟 on 2017/4/30.
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
     * 将文件读取到内存中
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
            //读取表单中所有书的信息
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
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        read.close();
        return true;
    }
}
