package fileOpreation;

import dao.UserDao;
import db.SearchTypeFeedback;
import db.SignInFeedback;
import model.Balance;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/29.
 *
 * �û��������
 */
public class UserFormOp {

    public List<UserModel> userLists = new ArrayList<>();//�û�

    public static UserFormOp userFormOp=null;
    public static UserFormOp getInstance(){
        if(userFormOp==null)
            userFormOp = new UserFormOp();
        return userFormOp;
    }
    public UserFormOp() {
        userLists = UserDao.userLists;
    }

    //���������û��������Ϣ
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

    //������飬n=1���� n =-1 ����
    public boolean borrowBook(int n){
        for(int i=0;i<userLists.size();i++){
            if(userLists.get(i).getID().equals(UserModel.userModel.getID())){
                userLists.get(i).setBNBooks(userLists.get(i).getBNBooks()+n);
                return true;
            }
        }
        return false;
    }

    //ע�� TODO �ж��Ƿ�ע�����δע�����¼����û�
    public int register(String ID,String password){
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){
                if(user.getPassword()==null||user.getPassword().equals("")){
                    user.setPassword(password);
                    return SignInFeedback.SUCCESSFUL;
                }
                else{
                    return SignInFeedback.DO_REGISTER;//�Ѿ�ע��
                }
            }
        }
        return SignInFeedback.NO_INFO;//���Ǳ�У��Ա
    }

    //��¼��֤ �û�
    public int signIn(String ID,String password){
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
