package dao;

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
 * Created by 宽伟 on 2017/4/29.
 *
 * 用户文件dao
 */
public class UserDao {

    //存储用户信息，可实时更新
    public List<UserModel> userLists = new ArrayList<>();
    public boolean iSModify=false;//标志是否修改了文件
    public boolean iSAdd = false;//标志是否增加了条目

    public static UserDao userDao=null;
    public static UserDao getInstance(){
        if(userDao==null){
            userDao =  new UserDao();
        }
        return userDao;
    }

    public boolean readUserForm() throws IOException, ParseException {
        String filePath="file//userform.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        UserModel userInfo;
        int UserNum=0;
        try {
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                userInfo=new UserModel();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            userInfo.setName(Info);
                            i=i+1;
                            break;
                        case 2:
                            userInfo.setID(Info);
                            i=i+1;
                            break;
                        case 3:
                            userInfo.setSchool(Info);
                            i=i+1;
                            break;
                        case 4:
                            userInfo.setPower(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 5:
                            userInfo.setANBooks(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 6:
                            userInfo.setBNBooks(Integer.parseInt(Info));
                            i=i+1;
                            break;
                        case 7:
                            userInfo.setBalance(Float.parseFloat(Info));
                            i=i+1;
                            break;
                        case 8:
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date =sdf.parse(Info);
                            java.sql.Date date1 = new java.sql.Date(date.getTime());
                            userInfo.setJoinDate(date1);
                            i=i+1;
                            break;
                        case 9:
                            userInfo.setPassword(Info);
                            i=i+1;
                            break;
                    }
                }
                userLists.add(userInfo);
                UserNum=UserNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        read.close();
        return true;
    }
}
