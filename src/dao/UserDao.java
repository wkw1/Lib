package dao;

import model.UserModel;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/29.
 *
 * �û��ļ�dao
 */
public class UserDao {

    private String filePath=FilePath.rootFilePath+ "\\userform.txt";

    //�洢�û���Ϣ����ʵʱ����
    public List<UserModel> userLists = new ArrayList<>();
    public boolean iSModify=false;//��־�Ƿ��޸����ļ�
    public boolean iSAdd = false;//��־�Ƿ���������Ŀ
    private int userNumber;

    public static UserDao userDao=null;
    public static UserDao getInstance(){
        if(userDao==null){
            userDao =  new UserDao();
        }
        return userDao;
    }

    public boolean readUserForm() throws IOException, ParseException {
        InputStreamReader read = new InputStreamReader(new FileInputStream(filePath),"GBK");
        BufferedReader reader = new BufferedReader(read);
        String eachLine;
        UserModel userInfo;
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
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        userNumber = userLists.size();
        read.close();
        return true;
    }

    //ֻ�����û�
    public void addUsers() throws IOException {
        for(int n=userNumber;n<userLists.size();n++){
            addOneUser(userLists.get(n));
        }
    }

    public void addOneUser(UserModel UAdded) throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));/////������Ҫ�ı����ʽ
        String UInfo=UAdded.getName()+"|"+UAdded.getID()+"|"+
                UAdded.getSchool()+"|"+UAdded.getPower()+"|"+UAdded.getANBooks()+
                "|"+UAdded.getBNBooks()+"|"+UAdded.getBalance()+"|"+UAdded.getJoinDate()
                +"|"+(userLists.get(0).getPassword()==null?"":userLists.get(0).getPassword())+"\r\n";
        fw.write(UInfo);
        fw.close();
    }

    //���ĺ��listд���ļ�
    public void writeFile() throws IOException{
        File f=new File(filePath);
        BufferedWriter fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));/////������Ҫ�ı����ʽ
        if(userLists.size()==0)
            return;
        fw.write(userLists.get(0).getName()+"|"+userLists.get(0).getID()+"|"+
                userLists.get(0).getSchool()+"|"+userLists.get(0).getPower()+"|"+userLists.get(0).getANBooks()+
                "|"+userLists.get(0).getBNBooks()+"|"+userLists.get(0).getBalance()+"|"+userLists.get(0).getJoinDate()
                +"|"+(userLists.get(0).getPassword()==null?"":userLists.get(0).getPassword())+"\r\n");
        for(int i=1;i<userLists.size();i++)
        {
            String UInfo=userLists.get(i).getName()+"|"+userLists.get(i).getID()+"|"+
                    userLists.get(i).getSchool()+"|"+userLists.get(i).getPower()+"|"+userLists.get(i).getANBooks()+
                    "|"+userLists.get(i).getBNBooks()+"|"+userLists.get(i).getBalance()+"|"+userLists.get(i).getJoinDate()
                    +"|"+(userLists.get(0).getPassword()==null?"":userLists.get(0).getPassword())+"\r\n";
            if(userLists.get(i).getPassword()!=null)
                UInfo+=userLists.get(i).getPassword();
            fw.write(UInfo);
        }
        fw.close();
    }
}
