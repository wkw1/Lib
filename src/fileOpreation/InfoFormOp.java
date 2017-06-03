package fileOpreation;

import dao.InfoDao;
import model.InfoModel;
import model.UserModel;
import view.SystemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 */
public class InfoFormOp {
    public List<InfoModel> infoLists = new ArrayList<>();
    public InfoDao infoDao;

    public static InfoFormOp orderBookFormOp;

    public static InfoFormOp getInstance() {
        if (orderBookFormOp == null)
            orderBookFormOp = new InfoFormOp();
        return orderBookFormOp;
    }

    public InfoFormOp() {
        infoDao = InfoDao.getInstance();
        infoLists = infoDao.infoLists;
    }

    //给用户发送消息提醒归还图书
    public boolean addReturnInfo(String ID,String ISBN){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("admin");
        infoModel.setInformer("管理员");
        infoModel.setInformThing("请尽快归还ISBN为"+ISBN+"的图书");
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }

    //给用户发送消息提醒归还图书
    public boolean sendOne(String str,String ID){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("admin");
        infoModel.setInformer("管理员");
        infoModel.setInformThing(str);
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }

    //用户得到消息，得到立马删除 TODO 更高级
    public List<InfoModel> getInfoList(String ID){
        List<InfoModel> lists= new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < infoLists.size(); i++) {
            String target;
            target = infoLists.get(i).getInformederID();
            if (target.equals(ID)) {
                lists.add(infoLists.get(i));
                infoLists.remove(i);
                infoDao.iSModify = true;
                i--;
                whetherExist = 1;
            }
        }
        if (whetherExist == 0)
            return null;
        else
            return lists;
    }
    
    //用户给管理员发送消息，希望充值
    public boolean recharge(float money,UserModel userModel){
    	InfoModel infoModel= new InfoModel();
        infoModel.setInformerID(userModel.getID());
        infoModel.setInformer(userModel.getName());
        infoModel.setInformThing("充值"+money+"元");
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID("admin");
        infoModel.setInformeder("管理员");
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }
    
    //管理员得到用户发送给管理员的消息
    public List<InfoModel> AdgetInfoList(){
        List<InfoModel> lists= new ArrayList<>();
        for (int i = 0; i < infoLists.size(); i++) {
            String target;
            target = infoLists.get(i).getInformederID();
            if (target!=null&&target.equals("admin")) {
                lists.add(infoLists.get(i));
            }
        }
        return lists;
    }

    //删除一条消息
    public boolean delOneInfo(InfoModel infoModel){
        infoDao.iSModify = true;
        return infoLists.remove(infoModel);
    }
    //删除所有发给用户的消息
    public boolean delAllUserInfo(){
       for(int i=0;i<infoLists.size();i++){
           if(infoLists.get(i).getInformederID().equals("admin")){
               infoLists.remove(i);
               infoDao.iSModify = true;
               i--;
           }
       }
       return true;
    }
}
