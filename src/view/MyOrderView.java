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

import action.UserAction;
import model.OrderBookModel;
import tablemodel.OBookTableModel;
import widget.InitWindow;
import java.awt.Font;
import java.awt.Color;

public class MyOrderView {
	
	private UserAction userAction;

	private JFrame frame;
	private JTable table = null;

	private JButton close;
	private JButton nextPage;
	private JButton formerPage;
	private JButton cancel;

	private int selectedRowIndex = -1;// ѡ�б���е���
	private OBookTableModel myTableModel;
	private List<OrderBookModel> list=null;
	
	/**
	 * Create the application.
	 */
	public MyOrderView() {
		userAction = new UserAction();
		initialize();
		getData();
		action();
	}
	/**
	 * ���õ���ģʽ
	 * @return
	 */
	public static MyOrderView myOrderView =null;
	public static MyOrderView getInstance(){
		if(myOrderView==null){
			myOrderView = new MyOrderView();
		}
		return myOrderView;
	}
	
	//��UserAction�еõ��������ݣ�������tableModel��������
	public void getData(){
		
		list = new ArrayList<>();
		// �õ��ܵı�
		list = userAction.getOrderBook();
		myTableModel = new OBookTableModel(table, frame, list);
		myTableModel.initData();
	}
	
	public void action(){
		// �����һҳ
		formerPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.formerPage())
					selectedRowIndex=-1;
			}
		});
		//�����һҳ
		nextPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myTableModel.nextPage())
					selectedRowIndex = -1;
			}
		});
		//�رմ˴���
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		//���ȡ��ԤԼͼ��
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedRowIndex==-1||table.getValueAt(selectedRowIndex, 1)==null){
					System.out.println("��ѡ��Ҫ������ͼ��");
				}
				else{
					System.out.println("ѡ���˵� " + selectedRowIndex+ " ��");
					String ISBN = (String) table.getValueAt(selectedRowIndex, 1);
					System.out.println("ѡ���˵� " + ISBN+ " ��");
				    if(userAction.cancelOrder(ISBN)){
				    	myTableModel.updateData(selectedRowIndex);
				    	selectedRowIndex =-1;
				    }
					
				}
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
		label.setBounds(495, 13, 113, 36);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 62, 916, 600);
		panel.add(scrollPane);

		table = new JTable();
		// �����಻�������ݴ�С�ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(70);
		scrollPane.setViewportView(table);

		nextPage = new JButton("��һҳ");
		nextPage.setBackground(new Color(0, 139, 139));
		nextPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		nextPage.setBounds(628, 687, 113, 39);
		panel.add(nextPage);

		formerPage = new JButton("��һҳ");
		formerPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		formerPage.setBackground(new Color(0, 139, 139));
		formerPage.setBounds(364, 687, 113, 39);
		panel.add(formerPage);

		cancel = new JButton("\u53D6\u6D88\u9884\u7EA6");
		cancel.setBackground(new Color(0, 206, 209));
		cancel.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		cancel.setBounds(1020, 274, 136, 39);
		panel.add(cancel);

		close = new JButton("\u9000\u51FA");
		close.setBackground(new Color(178, 34, 34));
		close.setBounds(1094, 32, 72, 27);
		panel.add(close);

		JLabel welcome = new JLabel("\u4F60\u597D\uFF01");
		welcome.setBounds(1043, 13, 72, 18);
		panel.add(welcome);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
