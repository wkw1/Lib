package tablemodel;

import java.util.List;

import javax.swing.JTable;

import model.BorrowBookModel;
/**
 * �����ģ����
 * @author ��ΰ
 *
 */

public class BBookTableModel extends Table<BBookTableModel, BorrowBookModel> {
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private List<BorrowBookModel> list=null;
	
	private int tablewight =141;
	

	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
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
		 //���һ����
		if(rowListAll>=8)
			lastLine+=8;
		else
			lastLine = rowListAll;
		updateTable();
	}
	
	// ҳ�淢�����ı�����ݵ�֪ͨ��ɾ�������ȡ��ԤԼ������
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
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][7];
		BorrowBookModel bbm = new BorrowBookModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = i;// ���
			mObjects[i][1] = bbm.getBookName();
			mObjects[i][2] = bbm.getBookISBN();
			mObjects[i][3] = bbm.getBookAuthor();
			mObjects[i][4] = bbm.getBorrowDate();
			mObjects[i][5] = bbm.getRTBook();
			mObjects[i][6] = bbm.getAIBook();
		}
		// ��ͷ����
		String[] strings = { "���", "����", "ISBN", "����", "����ʱ��", "ʣ�����ʱ��", "��������" };

		BBookTableModel model = new BBookTableModel(mObjects, strings);

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
