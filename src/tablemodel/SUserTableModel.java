package tablemodel;

import java.util.List;

import javax.swing.JTable;

import model.UserModel;
/**
 * ����Ա�����û���ģ��
 * @author ��ΰ
 *
 */
public class SUserTableModel extends Table<SUserTableModel, UserModel> {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private List<UserModel> list=null;
	
	private int tablewight =140;

	private int lastLine = 0;// ��ǰ�������һ��
	private int firstLine = 0;// ��ǰ���ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
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
		 //���һ����
		lastLine+=8;
		myTableModel = get(list);
		// ��������������
		table.setModel(myTableModel);
		for(int i=0 ;i<6;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
	}
	
	@Override
	public SUserTableModel get(List<UserModel> lists) {

		// ���������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][6];
		UserModel um = new UserModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			um = lists.get(i);
			mObjects[i][5] = i;// ���
			mObjects[i][0] = um.getName();
			mObjects[i][1] = um.getID();
			mObjects[i][2] = um.getJoinDate();
			mObjects[i][3] = um.getSchool();
			mObjects[i][4] = um.getBalance();

		}
		// ��ͷ����
		String[] strings = { "ID", "����","ְ��", "������Ŀ", "ע��ʱ��", "Ƿ��" };

		SUserTableModel model = new SUserTableModel(mObjects, strings);

		return model;
	}

	@Override
	public boolean nextPage() {
		// TODO Auto-generated method stub
		System.out.println("�����һҳSearchView");
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
			System.out.println("�Ѿ������һҳ��");
			return false;
		}
	}

	@Override
	public boolean formerPage() {
		// TODO Auto-generated method stub
		System.out.println("�����һҳSearchView");
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
			System.out.println("�Ѿ��ǵ�һҳ");
			return false;
		}
	}
	
	// �������������޸ı�������
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