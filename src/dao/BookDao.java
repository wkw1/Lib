package dao;

import model.BookModel;
import model.UserModel;

import java.io.*;
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
    private  String filePath="file//bookform.txt";

    public  List<BookModel> bookLists = new ArrayList<>();
    public  boolean iSModify=false;//标志是否修改了文件
    public  boolean iSAdd = false;//标志是否增加了条目
    public int bookNumber=0;

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
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        BookModel bookInfo;
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
            }
            bookNumber = bookLists.size();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        read.close();
        return true;
    }

    //只增加图书写入文件
    public void addBooks() throws IOException {
        for(int n=bookNumber;n<bookLists.size();n++){
            addOneBook(bookLists.get(n));
        }
    }

    public void addOneBook(BookModel BkAdded) throws IOException {
        File f=new File(filePath);
        BufferedWriter fw = null;/////可能需要改编码格式

        fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "GBK"));
        String BkInfo = "\r" + BkAdded.getISBN() + "|" + BkAdded.getName() + "|" +
                BkAdded.getIntroduction() + "|" + BkAdded.getBookType() + "|" + BkAdded.getAuthor() +
                "|" + BkAdded.getPress() + "|" + BkAdded.getTN() + "|" + BkAdded.getRN() +
                "|" + BkAdded.getPowerNeed() + "|" + BkAdded.getStorageTime();
        fw.write(BkInfo);
        fw.close();
    }

    public void writeFile() throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
        if(bookLists.size()==0)
            return;
        fw.write(bookLists.get(0).getISBN()+"|"+bookLists.get(0).getName()+"|"+
                bookLists.get(0).getIntroduction()+"|"+bookLists.get(0).getBookType()+"|"+bookLists.get(0).getAuthor()+
                "|"+bookLists.get(0).getPress()+"|"+bookLists.get(0).getTN()+"|"+bookLists.get(0).getRN()+
                "|"+bookLists.get(0).getPowerNeed()+"|"+bookLists.get(0).getStorageTime());
        for(int i=1;i<bookLists.size();i++)
        {
            String BkInfo="\r\n"+bookLists.get(i).getISBN()+"|"+bookLists.get(i).getName()+"|"+
                    bookLists.get(i).getIntroduction()+"|"+bookLists.get(i).getBookType()+"|"+bookLists.get(i).getAuthor()+
                    "|"+bookLists.get(i).getPress()+"|"+bookLists.get(i).getTN()+"|"+bookLists.get(i).getRN()+
                    "|"+bookLists.get(i).getPowerNeed()+"|"+bookLists.get(i).getStorageTime();
            fw.write(BkInfo);
        }
        fw.close();
    }
}
