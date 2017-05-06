package fileOpreation;

import dao.InfoDao;
import model.InfoModel;
import view.SystemEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 */
public class InfoFormOp {
    public List<InfoModel> infoLists = new ArrayList<>();

    public static InfoFormOp orderBookFormOp;

    public static InfoFormOp getInstance() {
        if (orderBookFormOp == null)
            orderBookFormOp = new InfoFormOp();
        return orderBookFormOp;
    }

    public InfoFormOp() {
        infoLists = InfoDao.infoLists;
    }

    //给用户发送消息提醒归还图书
    public boolean addReturnInfo(String ID,String ISBN){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("管理员");
        infoModel.setInformer("管理员");
        infoModel.setInformThing("归还图书ISBN:"+ISBN);
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);

        return infoLists.add(infoModel);
    }

    //给用户发送消息提醒归还图书
    public boolean sendOne(String str,String ID){
        InfoModel infoModel= new InfoModel();
        infoModel.setInformerID("管理员");
        infoModel.setInformer("管理员");
        infoModel.setInformThing(str);
        infoModel.setInformDate(SystemEntry.date);
        infoModel.setInformederID(ID);

        return infoLists.add(infoModel);
    }

    //用户得到消息，得到立马删除 TODO 更高
    public List<InfoModel> getInfoList(String ID){
        List<InfoModel> lists= new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < infoLists.size(); i++) {
            String target;
            target = infoLists.get(i).getInformederID();
            if (target.equals(ID)) {
                lists.add(infoLists.get(i));
                infoLists.remove(i);
                i--;
                whetherExist = 1;
            }
        }
        if (whetherExist == 0)
            return null;
        else
            return lists;
    }
}
