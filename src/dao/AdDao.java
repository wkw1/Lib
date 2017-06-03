package dao;

import db.SignInFeedback;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 *
 * 管理员Dao
 * 由于管理员的操作较少
 * 所有的验证操作都放在Dao类中
 */
public class AdDao {

    class AdModel{
        private String ID;
        private String password;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public List<AdModel> adLists = new ArrayList<>();

    public static AdDao adDao=null;
    public static AdDao getInstance(){
        if(adDao==null){
            adDao =  new AdDao();
        }
        return adDao;
    }

    public int signIn(String ID,String password){
        for(AdModel adModel:adLists){
            if(adModel.getID().equals(ID)){
                if(adModel.getPassword()==null||!adModel.getPassword().equals(password)){
                    //密码错误
                    return SignInFeedback.WRONG_PASSWORD;
                }
                else{
                    //成功登录 TODO 标志管理员
                    return SignInFeedback.SUCCESSFUL;
                }
            }
        }
        return SignInFeedback.NO_INFO;//不存在此管理员
    }

    public boolean readUserForm() throws IOException, ParseException {
        String filePath= FilePath.rootFilePath+ "\\adForm.txt";
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        AdModel userInfo;
        int UserNum=0;
        try {
            while((eachLine=reader.readLine())!=null)
            {
                int i=1;
                userInfo=new AdModel();
                for(String Info:eachLine.split("\\|"))
                {
                    switch(i){
                        case 1:
                            userInfo.setID(Info);
                            i=i+1;
                            break;
                        case 2:
                            userInfo.setPassword(Info);
                            i=i+1;
                            break;
                    }
                }
                adLists.add(userInfo);
                UserNum=UserNum+1;
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        read.close();
        return true;
    }
}
