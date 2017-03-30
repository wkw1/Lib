package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.OrderBookModel;

/**
 * 预约图书表模型
 * @author 宽伟
 * 用户得到
 */
public class OBookTableModel extends Table<OBookTableModel, OrderBookModel>{
	
	private JTable table;
	private JFrame frame;
	private List<OrderBookModel> list=null;
	
	private int tablewight =180;
	

	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	
	private OBookTableModel myTableModel;
	

	public OBookTableModel(JTable table,JFrame frame,List<OrderBookModel> list){
		this.table= table;
		this.frame = frame;
		this.list=list;
	}
	
	
	private static final long serialVersionUID = 1L;

	public OBookTableModel(Object[][] objects,Object[] sObjects) {
		super(objects,sObjects);
		this.fireTableDataChanged();
	}
	
	public void initData(){
		rowListAll = list.size();
		 //填充一部分
		lastLine+=8;
		updateTable();
	}
	
	//页面发出更改表格数据的通知，删除表格中取消预约的数据
	public void updateData(int row){
		if(lastLine-firstLine!=8||lastLine==rowListAll){
			lastLine--;
		}
		list.remove(firstLine+row);
		rowListAll--;
		updateTable();
	}
	
	@Override
	public OBookTableModel get(List<OrderBookModel> lists) {
		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][5];
		OrderBookModel bbm = new OrderBookModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = i;//序号
			mObjects[i][1] = bbm.getBookName();
			mObjects[i][2] = bbm.getBookISBN();
			mObjects[i][3] = bbm.getBookAuthor();
			mObjects[i][4] = bbm.getOrderDate();
		}
		// 标头数据
		String[] strings = { "序号", "书名", "ISBN", "作者","预约时间"};

		OBookTableModel model = new OBookTableModel(mObjects, strings);

		return model;
	}

	@Override
	public boolean nextPage() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("点击下一页SearchView");
		if(rowListAll-lastLine>0){
			if(rowListAll-lastLine>=7){
				firstLine=lastLine;
				lastLine+=8;
			}
			else{
				firstLine = lastLine;
				lastLine =rowListAll;
			}
			updateTable();
			return true;
		}
		else{
			System.out.println("已经是最后一页了");
			return false;
		}
	}
	
	
	@Override
	public boolean formerPage() {
		// TODO Auto-generated method stub
		System.out.println("点击上一页SearchView");
		if (firstLine>0) {
			if(firstLine>=8){
				lastLine=firstLine;
				firstLine-=8;
			}
			else{
				lastLine=firstLine;
				firstLine=0;
			}
			updateTable();
			return true;
				
		} else {
			System.out.println("已经是第一页");
			return false;
		}
	}
	//更改表格数据
	public void updateTable(){
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for(int i=0 ;i<5;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
		table.updateUI();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
