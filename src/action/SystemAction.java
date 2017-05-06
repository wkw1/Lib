package action;

import fileOpreation.BorrowBookFormOp;
import fileOpreation.OrderBookFormOp;
import fileOpreation.UserFormOp;
import model.OrderBookModel;

/**
 * Created by ��ΰ on 2017/5/1.
 */
public class SystemAction {

    //����ģʽ
    public static SystemAction systemAction;
    public static SystemAction getInstance(){
        if(systemAction==null){
            systemAction = new SystemAction();
        }
        return systemAction;
    }

    //�ǵ�¼�û��������������ԤԼ�鵽�ˣ�
    public boolean borrowForOder(OrderBookModel model){
        System.out.println("ISBN"+model.getBookISBN()+"ID"+model.getID());
        UserFormOp userFormOp = UserFormOp.getInstance();
        BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
        OrderBookFormOp orderBookFormOp = OrderBookFormOp.getInstance();
        if(!userFormOp.borrowBook(model.getID(),1))//�û�����������һ1
            return false;
        if(!borrowBookFormOp.borrowForOrder(model))
            return false;
        if(!orderBookFormOp.cancelOrder(model.getBookISBN(),model.getID()))//ɾ��ԤԼ��¼
            return false;
        return true;
    }
}