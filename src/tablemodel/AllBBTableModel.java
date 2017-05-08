package tablemodel;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.BorrowBookModel;
/**
 * �����û������ģ��
 * ���AllBrrowViewʹ��
 * @author ��ΰ
 *
 */

public class AllBBTableModel extends Table<AllBBTableModel, BorrowBookModel> {
	private static final long serialVersionUID = 1L;
	
	private AllBBTableModel myTableModel=null;
	private JTable table;
	private List<BorrowBookModel> list=null;
	
	
    private int tablewight =140;
	
	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
	public AllBBTableModel(JTable table,List<BorrowBookModel> list){
		this.table= table;
		this.list=list;
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
	public AllBBTableModel get(List<BorrowBookModel> lists) {
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][7];
		BorrowBookModel bbm = new BorrowBookModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
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
		// ��ͷ����
		String[] strings = { "������", "������ID", "����", "ISBN", "��������", "������", "ʣ�����ʱ��"};
		AllBBTableModel model = new AllBBTableModel(mObjects, strings);
		return model;
	}
	
	public AllBBTableModel(Object[][] objects, Object[] sObjects) {
		super(objects,sObjects);
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
			JOptionPane.showConfirmDialog(null, "�Ѿ������һҳ", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
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
			JOptionPane.showConfirmDialog(null, "�Ѿ��ǵ�һҳ", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
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
