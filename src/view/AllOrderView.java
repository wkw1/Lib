package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import action.AdAction;
import model.OrderBookModel;
import tablemodel.AllOBTableModel;
import widget.InitWindow;
import java.awt.Font;
import java.awt.Color;
/**
 * ����Ա��ԤԼ����ͼ
 * @author ��ΰ
 *
 */

public class AllOrderView {

	private JFrame frame;
	
	private JTable table = null;

	private JButton close;
	private JButton nextPage;
	private JButton formerPage;
	
	private int selectedRowIndex = -1;// ѡ�б���е���
	private AllOBTableModel myTableModel;
	private List<OrderBookModel> list=null;
	
	private AdAction adAction;


	
	public AllOrderView() {
		adAction = AdAction.getInstance();
		initialize();
		getData();
		action();
	}
	
	
	public static AllOrderView allOrderView=null;
	public static AllOrderView getInstance(){
		if(allOrderView==null){
			allOrderView = new AllOrderView();
		}
		return allOrderView;
	}
	
	
	//��UserAction�еõ��������ݣ�������tableModel��������
	public void getData() {
		list = new ArrayList<>();
		// �õ��ܵı�
		list = adAction.getAllOrderBook();
		if(list==null){
			OrderBookModel info = new OrderBookModel();
			info.setName("δ�ҵ�ͼ�飡");
			list = new ArrayList<>();
			list.add(info);
		}
		myTableModel = new AllOBTableModel(table, list);
		myTableModel.initData();
	}
	
	public void action(){
		// �����һҳ
		formerPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.formerPage())
					selectedRowIndex = -1;
			}
		});
		// �����һҳ
		nextPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.nextPage())
					selectedRowIndex = -1;
			}
		});
		// �رմ˴���
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				allOrderView =null;
				frame.dispose();
			}
		});
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// ���û�ѡȡ�ĵ������ݲ���
				int selectRows = table.getSelectedRows().length;// ȡ���û���ѡ�е�����
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// ȡ���û���ѡ����
					System.out.print(selectedRowIndex);
					String string = (String) table.getValueAt(selectedRowIndex, 1);
					System.out.print(string);
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		InitWindow.init(frame);
		
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ

		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u9884\u7EA6\u5217\u8868");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label.setBounds(487, 13, 122, 37);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 54, 1000, 596);
		panel.add(scrollPane);

		table = new JTable();
		// �����಻�������ݴ�С�ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(70);
		scrollPane.setViewportView(table);

		nextPage = new JButton("��һҳ");
		nextPage.setBackground(new Color(0, 139, 139));
		nextPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		nextPage.setBounds(628, 687, 113, 42);
		panel.add(nextPage);

		formerPage = new JButton("��һҳ");
		formerPage.setBackground(new Color(0, 139, 139));
		formerPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		formerPage.setBounds(364, 687, 113, 42);
		panel.add(formerPage);

		close = new JButton("\u9000\u51FA");
		close.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		close.setBackground(new Color(220, 20, 60));
		close.setBounds(1095, 0, 87, 27);
		panel.add(close);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
