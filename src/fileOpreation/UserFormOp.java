package fileOpreation;

import dao.UserDao;
import db.SearchTypeFeedback;
import db.SignInFeedback;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/29.
 *
 * �û��������
 */
public class UserFormOp {

    public List<UserModel> userLists = new ArrayList<>();

    public static UserFormOp userFormOp=null;
    public static UserFormOp getInstance(){
        if(userFormOp==null)
            userFormOp = new UserFormOp();
        return userFormOp;
    }
    public UserFormOp() {
        userLists = UserDao.userLists;
    }
    //��¼��֤
    public int signIn(String ID,String password){
        System.out.println(ID.toString());
        System.out.println(password.toString());
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){

                if(user.getPassword()==null||user.getPassword().equals("")){
                    //δע�����
                    return SignInFeedback.NOT_REGISTER;
                }
                else if(!user.getPassword().equals(password)){
                    //�������
                    return SignInFeedback.WRONG_PASSWORD;
                }
                else{
                    //�ɹ���¼
                    UserModel.userModel = user;
                    return SignInFeedback.SUCCESSFUL;
                }
            }
        }
        return SignInFeedback.NO_INFO;//���Ǵ�Уѧ��
    }

    //�����û�
    public List<UserModel> searchUserList(String keyWords,int keyType){
        List<UserModel> searchedList=new ArrayList<>();
        int whetherExist=0;
        for(int i=0;i<userLists.size();i++)
        {
            String target;
            switch(keyType){
                case SearchTypeFeedback.USER_NMAE:
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
