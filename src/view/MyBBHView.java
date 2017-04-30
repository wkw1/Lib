package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import action.UserAction;
import model.BBHModel;
import tablemodel.RBookTableModel;
import widget.InitWindow;
import javax.swing.JButton;
/**
 * 
 * ������ʷҳ��
 * @author ��ΰ
 *
 */
public class MyBBHView {

	private JFrame frame;
	private JTable table=null;
	
	private JButton nextPage;
	private JButton formerPage;
	private JButton signOut;
	
	private UserAction userAction=null;
	
	private List<BBHModel> list=null;//��������б����������������
	
	private RBookTableModel myTableModel;


	/**
	 * Create the application.
	 */
	public MyBBHView() {
		userAction = UserAction.getInstance();;
		initialize();
		geData();
		action();
		
	}
	
	public void geData() {
		list = new ArrayList<>();

		// �õ��ܵı�
		list = userAction.getBorrowHistory();
		myTableModel = new RBookTableModel(table, frame, list);
		myTableModel.initData();
	}
	
	public void action(){
		signOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		formerPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myTableModel.formerPage();
			}
		});
		
		nextPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				myTableModel.nextPage();
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

		JLabel label = new JLabel("\u5386\u53F2\u501F\u4E66");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		label.setBounds(536, 30, 119, 34);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 77, 1000, 505);
		panel.add(scrollPane);


		table = new JTable();
		// �����಻�������ݴ�С�ı�
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(60);
		scrollPane.setViewportView(table);
		
		formerPage = new JButton("\u4E0A\u4E00\u9875");
		formerPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		formerPage.setBackground(new Color(143, 188, 143));
		formerPage.setBounds(468, 631, 113, 43);
		panel.add(formerPage);
		
		nextPage = new JButton("\u4E0B\u4E00\u9875");
		nextPage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		nextPage.setBackground(new Color(143, 188, 143));
		nextPage.setBounds(654, 631, 113, 43);
		panel.add(nextPage);
		
		signOut = new JButton("\u9000\u51FA");
		signOut.setBounds(1055, 13, 113, 27);
		panel.add(signOut);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	

}
