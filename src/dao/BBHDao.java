package dao;

import model.BBHModel;
import model.BookModel;
import model.UserModel;
import view.SystemEntry;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 宽伟 on 2017/5/5.
 *
 * 借书历史文件dao和操作类
 */
public class BBHDao {

    private String filePath=FilePath.rootFilePath+ "\\bbhForm.txt";

    public List<BBHModel> bbhLists = new ArrayList<>();
    public  boolean iSModify=false;//标志是否修改了文件,暂时未使用
    public  boolean iSAdd = false;//标志是否增加了条目

    private int number;

    public static BBHDao bbhDao=null;
    public static BBHDao getInstance(){
        if(bbhDao==null){
            bbhDao =  new BBHDao();
        }
        return bbhDao;
    }

    public boolean addOne(BookModel model, UserModel user){
        BBHModel bbhModel = new BBHModel(user.getName(),user.getID(),model.getName(),model.getISBN(),
                model.getAuthor(),model.getStorageTime(), SystemEntry.date);
        iSAdd = true;
        return bbhLists.add(bbhModel);
    }

    public List<BBHModel> getBbhLists(String ID){
        //用户得到消息，得到立马删除
        List<BBHModel> lists = new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < bbhLists.size(); i++) {
            String target;
            target = bbhLists.get(i).getID();
            if (target.equals(ID)) {
                lists.add(bbhLists.get(i));
                whetherExist = 1;
            }
        }
        if (whetherExist == 0)
            return null;
        else
            return lists;
    }

    /**
     * 将文件读取到内存中
     * @return
     */
    public boolean readBookForm() throws IOException, ParseException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        BBHModel bookInfo;
        try {
            //读取表单中所有书的信息
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                bookInfo=new BBHModel();
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
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            Date date2 =sdf1.parse(Info);
                            java.sql.Date date3 = new java.sql.Date(date2.getTime());
                            bookInfo.setReturnDate(date3);
                            i=i+1;
                            break;
                    }
                }
                bbhLists.add(bookInfo);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        number = bbhLists.size();
        read.close();
        return true;
    }

    //只增加图书写入文件
    public void addRecords() throws IOException {
        for(int n=number;n<bbhLists.size();n++){
            addOneRecord(bbhLists.get(n));
        }
    }

    public void addOneRecord(BBHModel bbhModel) throws IOException {
        File f=new File(filePath);
        BufferedWriter fw = null;/////可能需要改编码格式

        fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "GBK"));
        String BkInfo =  bbhModel.getName() + "|" + bbhModel.getID() + "|" +
                bbhModel.getBookName() + "|" + bbhModel.getBookISBN() + "|" + bbhModel.getBookAuthor() +
                "|" + bbhModel.getBorrowDate() + "|" + bbhModel.getReturnDate()+"\r";
        fw.write(BkInfo);
        fw.close();
    }
}
