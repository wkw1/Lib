package tablemodel;

import java.util.List;

import javax.swing.JTable;

import model.BorrowBookModel;
/**
 * 借书表模型类
 * @author 宽伟
 *
 */

public class BBookTableModel extends Table<BBookTableModel, BorrowBookModel> {
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private List<BorrowBookModel> list=null;
	
	private int tablewight =141;
	

	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	private BBookTableModel myTableModel;
	
	public BBookTableModel(JTable table,List<BorrowBookModel> list){
		this.table= table;
		this.list=list;
	}
	
	public BBookTableModel(Object[][] objects,Object[] sObjects) {
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
	
	// 页面发出更改表格数据的通知，删除表格中取消预约的数据
	public void updateData(int row) {
		if (lastLine - firstLine != 8 || lastLine == rowListAll) {
			lastLine--;
		}
		list.remove(firstLine + row);
		rowListAll--;
		updateTable();
	}

	@Override
	public BBookTableModel get(List<BorrowBookModel> lists) {
		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][7];
		BorrowBookModel bbm = new BorrowBookModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = i;// 序号
			mObjects[i][1] = bbm.getBookName();
			mObjects[i][2] = bbm.getBookISBN();
			mObjects[i][3] = bbm.getBookAuthor();
			mObjects[i][4] = bbm.getBorrowDate();
			mObjects[i][5] = bbm.getRTBook();
			mObjects[i][6] = bbm.getAIBook();
		}
		// 标头数据
		String[] strings = { "序号", "书名", "ISBN", "作者", "借书时间", "剩余借书时间", "产生费用" };

		BBookTableModel model = new BBookTableModel(mObjects, strings);

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
