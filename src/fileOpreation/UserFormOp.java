package fileOpreation;

import dao.UserDao;
import db.SearchTypeFeedback;
import db.SignInFeedback;
import model.Balance;
import model.UserModel;
import widget.Encryp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/29.
 *
 * �û��������
 */
public class UserFormOp {

    public List<UserModel> userLists = new ArrayList<>();//�û�
    private UserDao userDao;

    public static UserFormOp userFormOp=null;
    public static UserFormOp getInstance(){
        if(userFormOp==null)
            userFormOp = new UserFormOp();
        return userFormOp;
    }
    public UserFormOp() {
        userDao = UserDao.getInstance();
        userLists = userDao.userLists;
    }

    //����Ա�����û�
    public boolean addOne(UserModel userModel){
        userDao.iSAdd = true;
        return userLists.add(userModel);
    }

    //����Աɾ���û�
    public boolean delOne(String ID){
        for(int i=0;i<userLists.size();i++){
            if(ID.equals(userLists.get(i).getID())){
                userLists.remove(i);
                userDao.iSModify = true;
                return true;
            }
        }
        return false;
    }

    //�õ������û���
    public List<UserModel> getRecentUser(){
        List<UserModel> lists=new ArrayList<>();
        int n=0;
        for(int i=userLists.size()-1;i>=0&&n<30;i--){
            if(!(userLists.get(i).getPassword()==null)&&!(userLists.get(i).getPassword().equals(""))){
                lists.add(userLists.get(i));
                n++;
            }
        }
        return lists;
    }

    //�û����飬�����û����
    public boolean reduceBalance(float money,String ID){
        for(int i=0;i<userLists.size();i++){
            if(ID.equals(userLists.get(i).getID())){
                userLists.get(i).setBalance(userLists.get(i).getBalance()-money);
                return true;
            }
        }
        return false;
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
        userDao.iSModify = true;
    }


    //������飬n=1���� n =-1 ����
    public boolean borrowBook(String ID,int n){
        for(int i=0;i<userLists.size();i++){
            if(userLists.get(i).getID().equals(ID)){
                userLists.get(i).setBNBooks(userLists.get(i).getBNBooks()+n);
                userDao.iSModify=true;
                return true;
            }
        }
        return false;
    }

    //ע��
    public int register(String ID,String password){
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){
                if(user.getPassword()==null||user.getPassword().equals("")){
                    String enPassword = Encryp.Encrypt(password);//����
                    user.setPassword(enPassword);
                    userDao.iSModify=true;
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
    public int signIn(String ID,String password) {
        for(UserModel user :userLists){
            if(user.getID().equals(ID)){

                if(user.getPassword()==null||user.getPassword().equals("")){
                    //δע�����
                    return SignInFeedback.NOT_REGISTER;
                }
                else if(!user.getPassword().equals(Encryp.Encrypt(password))){
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
