package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.OrderBookModel;

/**
 * ԤԼͼ���ģ��
 * @author ��ΰ
 * �û��õ�
 */
public class OBookTableModel extends Table<OBookTableModel, OrderBookModel>{
	
	private JTable table;
	private JFrame frame;
	private List<OrderBookModel> list=null;
	
	private int tablewight =180;
	

	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
	
	private OBookTableModel myTableModel;
	

	public OBookTableModel(JTable table,JFrame frame,List<OrderBookModel> list){
		this.table= table;
		this.frame = frame;
		this.list=list;
	}
	
	
	private static final long serialVersionUID = 1L;

	public OBookTableModel(Object[][] objects,Object[] sObjects) {
		super(objects,sObjects);
		this.fireTableDataChanged();
	}
	
	public void initData(){
		rowListAll = list.size();
		 //���һ����
		lastLine+=8;
		updateTable();
	}
	
	//ҳ�淢�����ı�����ݵ�֪ͨ��ɾ�������ȡ��ԤԼ������
	public void updateData(int row){
		if(lastLine-firstLine!=8||lastLine==rowListAll){
			lastLine--;
		}
		list.remove(firstLine+row);
		rowListAll--;
		updateTable();
	}
	
	@Override
	public OBookTableModel get(List<OrderBookModel> lists) {
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][5];
		OrderBookModel bbm = new OrderBookModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			bbm = lists.get(i);
			mObjects[i][0] = i;//���
			mObjects[i][1] = bbm.getBookName();
			mObjects[i][2] = bbm.getBookISBN();
			mObjects[i][3] = bbm.getBookAuthor();
			mObjects[i][4] = bbm.getOrderDate();
		}
		// ��ͷ����
		String[] strings = { "���", "����", "ISBN", "����","ԤԼʱ��"};

		OBookTableModel model = new OBookTableModel(mObjects, strings);

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
	//���ı������
	public void updateTable(){
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for(int i=0 ;i<5;i++){
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
