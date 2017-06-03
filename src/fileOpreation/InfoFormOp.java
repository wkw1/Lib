package fileOpreation;

import dao.InfoDao;
import model.InfoModel;
import model.UserModel;
import view.SystemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ��ΰ on 2017/4/30.
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

    //���û�������Ϣ���ѹ黹ͼ��
    public boolean addReturnInfo(String ID,String ISBN){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("admin");
        infoModel.setInformer("����Ա");
        infoModel.setInformThing("�뾡��黹ISBNΪ"+ISBN+"��ͼ��");
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }

    //���û�������Ϣ���ѹ黹ͼ��
    public boolean sendOne(String str,String ID){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("admin");
        infoModel.setInformer("����Ա");
        infoModel.setInformThing(str);
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }

    //�û��õ���Ϣ���õ�����ɾ�� TODO ���߼�
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
    
    //�û�������Ա������Ϣ��ϣ����ֵ
    public boolean recharge(float money,UserModel userModel){
    	InfoModel infoModel= new InfoModel();
        infoModel.setInformerID(userModel.getID());
        infoModel.setInformer(userModel.getName());
        infoModel.setInformThing("��ֵ"+money+"Ԫ");
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID("admin");
        infoModel.setInformeder("����Ա");
        infoDao.iSAdd = true;

        return infoLists.add(infoModel);
    }
    
    //����Ա�õ��û����͸�����Ա����Ϣ
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

    //ɾ��һ����Ϣ
    public boolean delOneInfo(InfoModel infoModel){
        infoDao.iSModify = true;
        return infoLists.remove(infoModel);
    }
    //ɾ�����з����û�����Ϣ
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
