package dao;

import model.BookModel;
import model.BorrowBookModel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/30.
 */
public class BorrowBookDao {

    private  String filePath="file//borrowbookform.txt";

    public List<BorrowBookModel> bblists = new ArrayList<>();
    public  boolean iSModify=false;//��־�Ƿ��޸����ļ�
    public  boolean iSAdd = false;//��־�Ƿ���������Ŀ

    private int bbNumber;

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
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        BorrowBookModel bookInfo;
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
            }
        } catch (NumberFormatException | IOException e) {
            // TODO �Զ����ɵ� catch ��
            e.printStackTrace();
        }
        bbNumber = bblists.size();
        read.close();
        return true;
    }

    //ֻ����ͼ��д���ļ�
    public void addBBooks() throws IOException {
        for(int n=bbNumber;n<bblists.size();n++){
            addOneBBook(bblists.get(n));
        }
    }

    public void addOneBBook(BorrowBookModel bbModel) throws IOException {
        File f=new File(filePath);
        BufferedWriter fw = null;/////������Ҫ�ı����ʽ

        fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "GBK"));
        String BkInfo = "\r" + bbModel.getName() + "|" + bbModel.getID() + "|" +
                bbModel.getBookName() + "|" + bbModel.getBookISBN() + "|" + bbModel.getBookAuthor() +
                "|" + bbModel.getBorrowDate() + "|" + bbModel.getRTBook() + "|" + bbModel.getAIBook() ;
        fw.write(BkInfo);
        fw.close();
    }

    public void writeFile() throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
        if(bblists.size()==0)
            return;
        fw.write(bblists.get(0).getName()+"|"+bblists.get(0).getID()+"|"+
                bblists.get(0).getBookName()+"|"+bblists.get(0).getBookISBN()+"|"+bblists.get(0).getBookAuthor()+
                "|"+bblists.get(0).getBorrowDate()+"|"+bblists.get(0).getRTBook()+"|"+bblists.get(0).getAIBook());
        for(int i=1;i<bblists.size();i++)
        {
            String BkInfo="\r\n"+bblists.get(i).getName()+"|"+bblists.get(i).getID()+"|"+
                    bblists.get(i).getBookName()+"|"+bblists.get(i).getBookISBN()+"|"+bblists.get(i).getBookAuthor()+
                    "|"+bblists.get(i).getBorrowDate()+"|"+bblists.get(i).getRTBook()+"|"+bblists.get(i).getAIBook();
            fw.write(BkInfo);
        }
        fw.close();
    }

}
