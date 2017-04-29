package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.BBHModel;
/**
 * 借阅历史表模型
 * @author 宽伟
 *
 */

public class RBookTableModel extends Table<RBookTableModel, BBHModel> {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFrame frame;
	private List<BBHModel> list=null;
	
	private int tablewight =140;
	
	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	private RBookTableModel myTableModel;
	
	
	public RBookTableModel(JTable table,JFrame frame,List<BBHModel> list){
		this.table= table;
		this.frame = frame;
		this.list=list;
	}
	
	public RBookTableModel(Object[][] objects,Object[] sObjects) {
		super(objects,sObjects);
		this.fireTableDataChanged();
	}
	
	public void initData(){
		rowListAll = list.size();
		 //填充一部分
		if(rowListAll>=8)
			lastLine+=8;
		else
			lastLine = rowListAll;
		updateTable();
	}
	
	@Override
	public RBookTableModel get(List<BBHModel> lists) {
		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][7];
		BBHModel bbm = new BBHModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = bbm.getID();// 序号
			mObjects[i][1] = bbm.getName();
			mObjects[i][3] = bbm.getBookISBN();
			mObjects[i][2] = bbm.getBookName();
			mObjects[i][4] = bbm.getBookAuthor();
			mObjects[i][5] = bbm.getBorrowDate();
			mObjects[i][6] = bbm.getReturnDate();
		}
		// 标头数据
		String[] strings = {"ID","姓名","书名", "ISBN", "作者", "借书时间", "还书时间"};

		RBookTableModel model = new RBookTableModel(mObjects, strings);

		return model;
	}

	@Override
	public boolean nextPage() {
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
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void updateTable(){
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for(int i=0 ;i<7;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
		table.updateUI();
	}
}
