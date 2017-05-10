package action;

import fileOpreation.BorrowBookFormOp;
import fileOpreation.InfoFormOp;
import fileOpreation.OrderBookFormOp;
import fileOpreation.UserFormOp;
import model.OrderBookModel;

/**
 * Created by 宽伟 on 2017/5/1.
 */
public class SystemAction {

    //单例模式
    public static SystemAction systemAction;
    public static SystemAction getInstance(){
        if(systemAction==null){
            systemAction = new SystemAction();
        }
        return systemAction;
    }

    //非登录用户产生借书操作（预约书到了）
    public boolean borrowForOder(OrderBookModel model){
        System.out.println("ISBN"+model.getBookISBN()+"ID"+model.getID());
        UserFormOp userFormOp = UserFormOp.getInstance();
        BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
        OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();

        if(!userFormOp.borrowBook(model.getID(),1))//用户借书数量加一1
            return false;
        if(!borrowBookFormOp.borrowForOrder(model))
            return false;
        if(!orderBookFormOp.cancelOrder(model.getBookISBN(),model.getID()))//删除预约记录
            return false;
        return true;
    }
    //非登录用户还书操作，（书被管理员删除了）
    public boolean delBorrowBook(String ID,String ISBN){
        UserFormOp userFormOp = UserFormOp.getInstance();
        InfoFormOp infoFormOp  = InfoFormOp.getInstance();//给用户发送消息
        infoFormOp.sendOne("ISBN为"+ISBN+"的图书\n        因被管理员删除而自动归还！",ID);

        return userFormOp.borrowBook(ID,-1);
    }
}
