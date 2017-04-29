package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.BBHModel;
/**
 * ������ʷ��ģ��
 * @author ��ΰ
 *
 */

public class RBookTableModel extends Table<RBookTableModel, BBHModel> {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JFrame frame;
	private List<BBHModel> list=null;
	
	private int tablewight =140;
	
	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
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
		 //���һ����
		if(rowListAll>=8)
			lastLine+=8;
		else
			lastLine = rowListAll;
		updateTable();
	}
	
	@Override
	public RBookTableModel get(List<BBHModel> lists) {
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][7];
		BBHModel bbm = new BBHModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = bbm.getID();// ���
			mObjects[i][1] = bbm.getName();
			mObjects[i][3] = bbm.getBookISBN();
			mObjects[i][2] = bbm.getBookName();
			mObjects[i][4] = bbm.getBookAuthor();
			mObjects[i][5] = bbm.getBorrowDate();
			mObjects[i][6] = bbm.getReturnDate();
		}
		// ��ͷ����
		String[] strings = {"ID","����","����", "ISBN", "����", "����ʱ��", "����ʱ��"};

		RBookTableModel model = new RBookTableModel(mObjects, strings);

		return model;
	}

	@Override
	public boolean nextPage() {
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
