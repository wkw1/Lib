package tablemodel;

import java.util.List;

import javax.swing.JTable;
import model.BookModel;
/**
 * 
 * 表单填充模型 搜索图书表
 * 接受列表数据
 * @author 宽伟
 *
 */
public class SBookTableModel extends Table<SBookTableModel, BookModel> {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private List<BookModel> list=null;
	
	private int tablewight =141;

	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	private int chooseLine;
	
	private SBookTableModel myTableModel;
	
	public SBookTableModel(){
		
	}
	
	public SBookTableModel(JTable table,List<BookModel> list){
		this.table= table;
		this.list=list;
	}

	public SBookTableModel(Object[][] objects,Object[] sObjects) {
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
		myTableModel = get(list);
		// 向表格中填充数据
		table.setModel(myTableModel);
		for(int i=0 ;i<7;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
	}	
	
	//借书操作后更改内容
	public boolean updateData(int index){
		//list.get(index+firstLine).setRN(list.get(index+firstLine).getRN()-1);
		updateTable();
		return true;
	}
	//删除图书后的操作
	public boolean updateDelData(int index){
		if(lastLine-firstLine!=8||lastLine==rowListAll){
			lastLine--;
		}
		list.remove(index+firstLine);
		rowListAll--;
		updateTable();
		return true;
	}
	//管理员修改图书信息后更新表格数据
	public void setUpdateData(BookModel bm){
		list.get(chooseLine).setIntroduction(bm.getIntroduction());
		list.get(chooseLine).setAuthor(bm.getAuthor());
		list.get(chooseLine).setName(bm.getName());
		list.get(chooseLine).setBookType(bm.getBookType());
		//更改总数量之后要更改可借数量
		list.get(chooseLine).setRN(bm.getTN()-list.get(chooseLine).getTN()+list.get(chooseLine).getRN());
		list.get(chooseLine).setPowerNeed(bm.getPowerNeed());

		updateTable();
	}
	
	//得到选中行数据
	public BookModel getChoose(int row){
		chooseLine = row+firstLine;
		return list.get(chooseLine);
	}
	
	@Override
	public SBookTableModel get(List<BookModel> lists) {
		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][7];
		BookModel sbm = new BookModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			sbm = lists.get(i);
			mObjects[i][0] = sbm.getName();
			mObjects[i][1] = sbm.getISBN();
			mObjects[i][2] = sbm.getAuthor();
			mObjects[i][3] = sbm.getBookType();
			mObjects[i][4] = sbm.getPress();
			mObjects[i][5] = sbm.getRN();
			mObjects[i][6] = sbm.getPowerNeed();
		}
		// 标头数据
		String[] strings = {"书名", "ISBN","作者","所属类型","出版社","可借数量" ,"所需权限"};

		SBookTableModel model = new SBookTableModel(mObjects, strings);

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
	//前往指定页
	public boolean goPage(int pages){
		firstLine = (pages-1)*8;
		lastLine=firstLine+8;
		if(lastLine>rowListAll)
			lastLine=rowListAll;
		updateTable();
		return true;
	}
	
	// 公共代码重新修改表格数据
	private void updateTable() {
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for (int i = 0; i < 7; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
		table.updateUI();
	}
	
	public int getRowListAll() {
		return rowListAll;
	}
	public void setRowListAll(int rowListAll) {
		this.rowListAll = rowListAll;
	}
}

