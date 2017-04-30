package fileOpreation;

import dao.UserDao;
import db.SearchTypeFeedback;
import db.SignInFeedback;
import model.Balance;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/29.
 *
 * 用户表操作类
 */
public class UserFormOp {

    public List<UserModel> userLists = new ArrayList<>();//用户

    public static UserFormOp userFormOp=null;
    public static UserFormOp getInstance(){
        if(userFormOp==null)
            userFormOp = new UserFormOp();
        return userFormOp;
    }
    public UserFormOp() {
        userLists = UserDao.userLists;
    }

    //更新所有用户的余额信息
    public void updateBalance(List<Balance> list1,List<Balance> list2){
        for(int i=0;i<list2.size();i++){
            float money=0;
            int j;
            for(j=0;j<=i&&j<list1.size();j++){
                if(list2.get(i).ID.equals(list1.get(i).ID)){
                    money = list2.get(i).balance - list1.get(i).balance;
                    break;
                }
            }
            if(j>i||j>=list1.size())
                money = list2.get(i).balance;
            for(int k=0;k<userLists.size();k++){
                if(userLists.get(k).getID().equals(list1.get(i).ID)){
                    userLists.get(k).setBalance(userLists.get(k).getBalance()
                            -money);
                }
            }
        }
    }

    //借书或还书，n=1借书 n =-1 还书
    public boolean borrowBook(int n){
        for(int i=0;i<userLists.size();i++){
            if(userLists.get(i).getID().equals(UserModel.userModel.getID())){
                userLists.get(i).setBNBooks(userLists.get(i).getBNBooks()+n);
                return true;
            }
        }
        return false;
    }

    //注册 TODO 判断是否注册过，未注册则新加入用户
    public int register(String ID,String password){
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){
                if(user.getPassword()==null||user.getPassword().equals("")){
                    user.setPassword(password);
                    return SignInFeedback.SUCCESSFUL;
                }
                else{
                    return SignInFeedback.DO_REGISTER;//已经注册
                }
            }
        }
        return SignInFeedback.NO_INFO;//不是本校成员
    }

    //登录验证 用户
    public int signIn(String ID,String password){
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){

                if(user.getPassword()==null||user.getPassword().equals("")){
                    //未注册情况
                    return SignInFeedback.NOT_REGISTER;
                }
                else if(!user.getPassword().equals(password)){
                    //密码错误
                    return SignInFeedback.WRONG_PASSWORD;
                }
                else{
                    //成功登录
                    UserModel.userModel = user;
                    return SignInFeedback.SUCCESSFUL;
                }
            }
        }
        return SignInFeedback.NO_INFO;//不是此校学生
    }

    //搜索用户
    public List<UserModel> searchUserList(String keyWords,int keyType){
        List<UserModel> searchedList=new ArrayList<>();
        int whetherExist=0;
        for(int i=0;i<userLists.size();i++)
        {
            String target;
            switch(keyType){
                case SearchTypeFeedback.USER_NAME:
                    target=userLists.get(i).getName();
                    if(target.equals(keyWords))
                    {
                        searchedList.add(userLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.USER_ID:
                    target=userLists.get(i).getID();
                    if(target.equals(keyWords))
                    {
                        searchedList.add(userLists.get(i));
                        whetherExist=1;
                    }
                    break;
                case SearchTypeFeedback.USER_SCHOOL:
                    target=userLists.get(i).getSchool();
                    if(target.equals(keyWords))
                    {
                        searchedList.add(userLists.get(i));
                        whetherExist=1;
                    }
                    break;
            }
        }
        if(whetherExist==0)
            return null;
        else
            return searchedList;
    }
}
