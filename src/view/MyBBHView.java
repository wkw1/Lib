package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import action.UserAction;
import dao.BBHDao;
import model.BBHModel;
import tablemodel.RBookTableModel;
import widget.InitWindow;

/**
 * 
 * 借阅历史页面
 * @author 宽伟
 *
 */
public class MyBBHView {

	private JFrame frame;
	private JTable table=null;
	
	private JButton nextPage;
	private JButton formerPage;
	private JButton signOut;
	
	private UserAction userAction=null;
	
	private List<BBHModel> list=null;//搜索结果列表，保存所有搜索结果
	
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

	public static MyBBHView myBBHView;
	public static MyBBHView getInstance(){
		if(myBBHView==null){
			myBBHView= new MyBBHView();
		}
		return myBBHView;
	}

	public void geData() {
		list = new ArrayList<>();
		// 得到总的表
		list = userAction.getBorrowHistory();
		if(list==null){
			list = new ArrayList<>();
			BBHModel bbhModel= new BBHModel();
			bbhModel.setBookName("无借书历史");
			list.add(bbhModel);
		}
		myTableModel = new RBookTableModel(table, list);
		myTableModel.initData();
	}
	
	public void action(){
		signOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				myBBHView =null;
			}
		});
		
		formerPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!myTableModel.formerPage())
				    JOptionPane.showConfirmDialog(null, "已经是第一页了！",
						"提示信息", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		nextPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!myTableModel.nextPage())
					JOptionPane.showConfirmDialog(null, "已经是最后一页了！",
							"提示信息", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
        InitWindow.init(frame);
		
		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片

		frame.getContentPane().add(panel);

		JLabel label = new JLabel("\u5386\u53F2\u501F\u4E66");
		label.setFont(new Font("华文楷体", Font.PLAIN, 25));
		label.setBounds(536, 30, 119, 34);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 77, 1000, 505);
		panel.add(scrollPane);


		table = new JTable();
		// 设置类不可随数据大小改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(60);
		scrollPane.setViewportView(table);
		
		formerPage = new JButton("\u4E0A\u4E00\u9875");
		formerPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		formerPage.setBackground(new Color(143, 188, 143));
		formerPage.setBounds(468, 631, 113, 43);
		panel.add(formerPage);
		
		nextPage = new JButton("\u4E0B\u4E00\u9875");
		nextPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		nextPage.setBackground(new Color(143, 188, 143));
		nextPage.setBounds(654, 631, 113, 43);
		panel.add(nextPage);
		
		signOut = new JButton("\u9000\u51FA");
		signOut.setBackground(new Color(255, 0, 0));
		signOut.setFont(new Font("华文楷体", Font.PLAIN, 15));
		signOut.setBounds(1093, 0, 89, 27);
		panel.add(signOut);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	

}
