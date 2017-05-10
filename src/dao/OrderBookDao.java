package dao;

import model.BorrowBookModel;
import model.OrderBookModel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 */
public class OrderBookDao {

    private  String filePath="file//orderForm.txt";

    public List<OrderBookModel> obLists = new ArrayList<>();
    public  boolean iSModify=false;//标志是否修改了文件
    public  boolean iSAdd = false;//标志是否增加了条目

    private int orderNumber;

    public static OrderBookDao orderBookDao=null;
    public static OrderBookDao getInstance(){
        if(orderBookDao==null){
            orderBookDao =  new OrderBookDao();
        }
        return orderBookDao;
    }

    /**
     * 将文件读取到内存中
     * @return
     */
    public boolean readBookForm() throws IOException, ParseException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        OrderBookModel bookInfo;
        try {
            //读取表单中所有书的信息
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                bookInfo=new OrderBookModel();
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
                            bookInfo.setOrderDate(date1);
                            i=i+1;
                            break;
                    }
                }
                obLists.add(bookInfo);
            }
        } catch (NumberFormatException | IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        orderNumber= obLists.size();
        read.close();
        return true;
    }

    //只增加图书写入文件
    public void addOBooks() throws IOException {
        for(int n=orderNumber;n<obLists.size();n++){
            addOneOBook(obLists.get(n));
        }
    }

    public void addOneOBook(OrderBookModel orderBookModel) throws IOException {
        File f=new File(filePath);
        BufferedWriter fw = null;/////可能需要改编码格式

        fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "GBK"));
        String BkInfo = "\r" + orderBookModel.getName() + "|" + orderBookModel.getID() + "|" +
                orderBookModel.getBookName() + "|" + orderBookModel.getBookISBN() + "|" + orderBookModel.getBookAuthor()
                + "|" + orderBookModel.getOrderDate();
        fw.write(BkInfo);
        fw.close();
    }

    public void writeFile() throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
        if(obLists.size()==0)
            return;
        fw.write(obLists.get(0).getName()+"|"+obLists.get(0).getID()+"|"+
                obLists.get(0).getBookName()+"|"+obLists.get(0).getBookISBN()+"|"+obLists.get(0).getBookAuthor()
                +"|"+obLists.get(0).getOrderDate());
        for(int i=1;i<obLists.size();i++)
        {
            String BkInfo="\r\n"+obLists.get(i).getName()+"|"+obLists.get(i).getID()+"|"+
                    obLists.get(i).getBookName()+"|"+obLists.get(i).getBookISBN()+"|"+obLists.get(i).getBookAuthor()
                    +"|"+obLists.get(i).getOrderDate();
            fw.write(BkInfo);
        }
        fw.close();
    }
}
