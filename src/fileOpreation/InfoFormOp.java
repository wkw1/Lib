package fileOpreation;

import dao.InfoDao;
import dao.OrderBookDao;
import db.SearchTypeFeedback;
import model.InfoModel;
import model.OrderBookModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宽伟 on 2017/4/30.
 */
public class InfoFormOp {
    public List<InfoModel> obLists= new ArrayList<>();

    public static InfoFormOp orderBookFormOp;

    public static InfoFormOp getInstance() {
        if (orderBookFormOp == null)
            orderBookFormOp = new InfoFormOp();
        return orderBookFormOp;
    }

    public InfoFormOp() {
        obLists = InfoDao.infoLists;
    }

    //用户得到消息，得到立马删除 TODO
    public List<InfoModel> getInfoList(String ID){
        List<InfoModel> lists= new ArrayList<>();
        int whetherExist = 0;
        for (int i = 0; i < obLists.size(); i++) {
            String target;
            target = obLists.get(i).getInformederID();
            if (target.equals(ID)) {
                lists.add(obLists.get(i));
                whetherExist = 1;
            }
        }
        if (whetherExist == 0)
            return null;
        else
            return lists;
    }
}
