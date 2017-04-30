package dao;

import model.InfoModel;
import model.UserModel;

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
 * 消息Dao，存储消息列表
 */
public class InfoDao {
    //存储用户信息，可实时更新
    public static List<InfoModel> infoLists = new ArrayList<>();

    public static InfoDao infoDao=null;
    public static InfoDao getInstance(){
        if(infoDao==null){
            infoDao =  new InfoDao();
        }
        return infoDao;
    }

    public boolean readInfoForm() throws IOException, ParseException {
        String filePath="file//infoForm.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        InfoModel userInfo;
        int UserNum=0;
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
                UserNum=UserNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        read.close();
        return true;
    }
}
