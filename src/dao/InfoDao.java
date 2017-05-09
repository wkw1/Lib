package dao;

import model.InfoModel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 * 消息Dao，存储消息列表
 */
public class InfoDao {
    //存储用户信息，可实时更新
    public List<InfoModel> infoLists = new ArrayList<>();
    public boolean iSModify=false;//标志是否修改了文件
    public boolean iSAdd = false;//标志是否增加了条目
    private int infoNumber=0;

    private String filePath;

    public static InfoDao infoDao=null;
    public static InfoDao getInstance(){
        if(infoDao==null){
            infoDao =  new InfoDao();
        }
        return infoDao;
    }

    public boolean readInfoForm() throws IOException, ParseException {
        filePath="file//infoForm.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        InfoModel userInfo;
        try {
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                userInfo=new InfoModel();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            userInfo.setInformer(Info);
                            i=i+1;
                            break;
                        case 2:
                            userInfo.setInformerID(Info);
                            i=i+1;
                            break;
                        case 3:
                            userInfo.setInformeder(Info);
                            i=i+1;
                            break;
                        case 4:
                            userInfo.setInformederID(Info);
                            i=i+1;
                            break;
                        case 5:
                            userInfo.setInformThing(Info);
                            i=i+1;
                            break;
                        case 6:
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date =sdf.parse(Info);
                            java.sql.Date date1 = new java.sql.Date(date.getTime());
                            userInfo.setInformDate(date1);
                            i=i+1;
                            break;
                    }
                }
                infoLists.add(userInfo);
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        infoNumber = infoLists.size();
        read.close();
        return true;
    }

    public void addInfos(){
        for(int n=infoNumber;n<infoLists.size();n++){
            addOneBook(infoLists.get(n));
        }
    }



    public void addOneBook(InfoModel infoModel){
        File f=new File(filePath);
        BufferedWriter fw= null;/////可能需要改编码格式
        try {
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true),"GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String Info="\r"+infoModel.getInformer()+"|"+infoModel.getInformerID()+"|"+
                infoModel.getInformeder()+"|"+infoModel.getInformederID()+"|"+infoModel.getInformThing()+
                "|"+infoModel.getInformDate();
        try {
            fw.write(Info);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






}
