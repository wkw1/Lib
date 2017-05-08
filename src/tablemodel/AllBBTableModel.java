package tablemodel;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.BorrowBookModel;
/**
 * 所有用户借书表模型
 * 配合AllBrrowView使用
 * @author 宽伟
 *
 */

public class AllBBTableModel extends Table<AllBBTableModel, BorrowBookModel> {
	private static final long serialVersionUID = 1L;
	
	private AllBBTableModel myTableModel=null;
	private JTable table;
	private List<BorrowBookModel> list=null;
	
	
    private int tablewight =140;
	
	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	public AllBBTableModel(JTable table,List<BorrowBookModel> list){
		this.table= table;
		this.list=list;
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
	public AllBBTableModel get(List<BorrowBookModel> lists) {
		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][7];
		BorrowBookModel bbm = new BorrowBookModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = bbm.getName();
			mObjects[i][1] = bbm.getID();
			mObjects[i][2] = bbm.getBookName();
			mObjects[i][3] = bbm.getBookISBN();
			mObjects[i][4] = bbm.getBorrowDate();
			mObjects[i][5] = bbm.getBookAuthor();
			mObjects[i][6] = bbm.getRTBook();
		}
		// 标头数据
		String[] strings = { "借书人", "借书人ID", "书名", "ISBN", "借书日期", "书作者", "剩余借书时间"};
		AllBBTableModel model = new AllBBTableModel(mObjects, strings);
		return model;
	}
	
	public AllBBTableModel(Object[][] objects, Object[] sObjects) {
		super(objects,sObjects);
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
			JOptionPane.showConfirmDialog(null, "已经是最后一页", "提示信息", JOptionPane.PLAIN_MESSAGE);
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
			JOptionPane.showConfirmDialog(null, "已经是第一页", "提示信息", JOptionPane.PLAIN_MESSAGE);
			return false;
		}
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
