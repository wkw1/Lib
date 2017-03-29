package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.OrderBookModel;
/**
 * ����Ա��ѯ��ԤԼ���ģ��
 * ���AllOrderView ʹ��
 * @author ��ΰ
 *
 */

public class AllOBTableModel extends Table<AllOBTableModel, OrderBookModel>{
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JFrame frame;
	private List<OrderBookModel> list=null;
	
	private int tablewight =140;
	

	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
	private AllOBTableModel myTableModel;
	
	public AllOBTableModel(JTable table,JFrame frame,List<OrderBookModel> list){
		this.table= table;
		this.frame = frame;
		this.list=list;
	}

	public AllOBTableModel(Object[][] objects, Object[] sObjects) {
		super(objects,sObjects);
	}
	
	public void initData(){
		rowListAll = list.size();
		 //���һ����
		lastLine+=8;
		updateTable();
	}

	@Override
	public AllOBTableModel get(List<OrderBookModel> lists) {
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][7];
		OrderBookModel bbm = new OrderBookModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = i;//���
			mObjects[i][1] = bbm.getName();
			mObjects[i][2] = bbm.getID();
			mObjects[i][3] = bbm.getBookName();
			mObjects[i][4] = bbm.getBookISBN();
			mObjects[i][5] = bbm.getOrderDate();
			mObjects[i][6] = bbm.getBookAuthor();
			
		}
		// ��ͷ����
		String[] strings = { "���", "����", "ID", "����","ISBN","��������","������"};

		AllOBTableModel model = new AllOBTableModel(mObjects, strings);

		return model;

	}

	@Override
	public boolean nextPage() {
		// TODO Auto-generated method stub
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
	private void updateTable(){
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for(int i=0 ;i<7;i++){
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
