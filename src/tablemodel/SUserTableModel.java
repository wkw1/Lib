package tablemodel;

import java.util.List;
import javax.swing.JTable;

import model.UserModel;
/**
 * 管理员搜索用户表模型
 * @author 宽伟
 */
public class SUserTableModel extends Table<SUserTableModel, UserModel> {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<UserModel> list=null;
	
	private int tablewight =148;

	private int lastLine = 0;// 当前表的最后一行
	private int firstLine = 0;// 当前表的第一行
	private int rowListAll = 0;// list总长度
	
	private SUserTableModel myTableModel;
	
	public SUserTableModel(JTable table,List<UserModel> lists) {
		this.table=table;
		this.list=lists;
	}
	
	public SUserTableModel(Object[][] objects,Object[] sObjects) {
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
		for(int i=0 ;i<6;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
	}
	
	//删除用户后的操作
	public boolean updateDelData(int index) {
		if (lastLine - firstLine != 8 || lastLine == rowListAll) {
			lastLine--;
		}
		list.remove(index + firstLine);
		rowListAll--;
		updateTable();
		return true;
	}
	
	@Override
	public SUserTableModel get(List<UserModel> lists) {

		// 表单数据用二维数组表示
		Object[][] mObjects = new Object[8][7];
		UserModel um = new UserModel();
		if (lists == null) {
			System.out.println("传入的借书表为空");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			um = lists.get(i);
			mObjects[i][0] = um.getName();
			mObjects[i][1] = um.getID();
			if(um.getPower()==1)
				mObjects[i][2] = "学生";
			else if(um.getPower()==2)
				mObjects[i][2] = "老师";
			mObjects[i][3] = um.getSchool();
			mObjects[i][4] = um.getBNBooks();
			mObjects[i][5] = um.getJoinDate();
			mObjects[i][6] = um.getBalance();
		}
		// 标头数据
		String[] strings = {"姓名" ,"ID","职称", "学院","借书数目", "注册时间", "余额" };

		SUserTableModel model = new SUserTableModel(mObjects, strings);

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
	
	// 公共代码重新修改表格数据
	private void updateTable() {
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for (int i = 0; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
		table.updateUI();
	}
}
