package view;


import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import java.awt.Color;

public class AfterAdSignInView {

	private JFrame frame;
	private JTextField keyWord;
	private JComboBox<String> whichTypeForBook;
	private JTextField KeyWordForUser;
	
	private JButton InputBook ;
	private JButton searchBook;
	private JButton seeOrderTable;
	private JButton seeBorrowTable;

	/**
	 * Create the application.
	 */
	public AfterAdSignInView() {
		initialize();
		action();
	}
	
	public void action(){
		//录入图书
		InputBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InputBookView inputBookView = new InputBookView(null,1);
				inputBookView.getFrame().setVisible(true);
			}
		});
		//搜索图书
		searchBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchResultView view =SearchResultView.getInstance("", "", 2);
				view.getFrame().setVisible(true);
			}
		});
		seeOrderTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AllOrderView allOrderView = AllOrderView.getInstance();
				allOrderView.getFrame().setVisible(true);
			}
		});
		seeBorrowTable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AllBorrowView allBrderView = AllBorrowView.getInstance();
				allBrderView.getFrame().setVisible(true);
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
		
		InputBook = new JButton("\u5F55\u5165\u56FE\u4E66");
		InputBook.setBackground(new Color(175, 238, 238));
		InputBook.setFont(new Font("华文行楷", Font.PLAIN, 30));
		InputBook.setBounds(205, 244, 183, 78);
		panel.add(InputBook);
		
		seeOrderTable = new JButton("\u67E5\u770B\u9884\u7EA6\u8868");
		seeOrderTable.setBackground(new Color(135, 206, 250));
		seeOrderTable.setFont(new Font("华文行楷", Font.PLAIN, 30));
		seeOrderTable.setBounds(59, 374, 200, 78);
		panel.add(seeOrderTable);
		
		searchBook = new JButton("\u641C\u7D22");
		searchBook.setFont(new Font("华文楷体", Font.PLAIN, 20));
		searchBook.setBounds(224, 664, 113, 52);
		panel.add(searchBook);
		
		keyWord = new JTextField();
		keyWord.setFont(new Font("宋体", Font.PLAIN, 20));
		keyWord.setBounds(262, 590, 113, 35);
		panel.add(keyWord);
		keyWord.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5173\u952E\u5B57");
		label_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_1.setBounds(157, 594, 91, 27);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_2.setBounds(157, 532, 91, 32);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u56FE\u4E66\u4FE1\u606F\u7BA1\u7406");
		label_3.setFont(new Font("华文楷体", Font.PLAIN, 40));
		label_3.setBounds(163, 60, 287, 78);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u7528\u6237\u7BA1\u7406");
		label_4.setFont(new Font("华文楷体", Font.PLAIN, 40));
		label_4.setBounds(720, 87, 215, 51);
		panel.add(label_4);
		
		JButton inputUser = new JButton("\u5F55\u5165\u7528\u6237");
		inputUser.setBackground(new Color(255, 0, 204));
		inputUser.setFont(new Font("华文行楷", Font.PLAIN, 25));
		inputUser.setBounds(645, 246, 171, 78);
		panel.add(inputUser);
		
		JButton btnNewButton_4 = new JButton(" \u67E5\u770B\u8FD1\u671F\u7528\u6237");
		btnNewButton_4.setBackground(new Color(204, 102, 153));
		btnNewButton_4.setFont(new Font("华文行楷", Font.PLAIN, 25));
		btnNewButton_4.setBounds(871, 244, 200, 78);
		panel.add(btnNewButton_4);
		
		
		whichTypeForBook = new JComboBox<String>();
		whichTypeForBook.setBounds(262, 534, 113, 32);
		panel.add(whichTypeForBook);
		
		JLabel label_5 = new JLabel("\u641C\u7D22\u56FE\u4E66\u8868");
		label_5.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_5.setBounds(216, 492, 121, 27);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u641C\u7D22\u7528\u6237");
		label_6.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_6.setBounds(807, 402, 85, 24);
		panel.add(label_6);
		
		JLabel lblNewLabel_1 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		lblNewLabel_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(711, 500, 98, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5173\u952E\u5B57");
		lblNewLabel_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(711, 572, 91, 25);
		panel.add(lblNewLabel_2);
		
		KeyWordForUser = new JTextField();
		KeyWordForUser.setBounds(823, 562, 112, 35);
		panel.add(KeyWordForUser);
		KeyWordForUser.setColumns(10);
		
		JButton searchUser = new JButton("\u641C\u7D22");
		searchUser.setFont(new Font("华文楷体", Font.PLAIN, 25));
		searchUser.setBounds(799, 668, 113, 48);
		panel.add(searchUser);
		
		JComboBox<String> whichTypeForUser = new JComboBox<String>();
		whichTypeForUser.setBounds(823, 492, 112, 35);
		panel.add(whichTypeForUser);
		whichTypeForUser.addItem("姓名");
		whichTypeForUser.addItem("ID");
		whichTypeForUser.addItem("学院");
		
		seeBorrowTable = new JButton("\u67E5\u770B\u501F\u4E66\u8868");
		seeBorrowTable.setFont(new Font("华文行楷", Font.PLAIN, 30));
		seeBorrowTable.setBackground(new Color(135, 206, 250));
		seeBorrowTable.setBounds(334, 374, 200, 78);
		panel.add(seeBorrowTable);
		
		whichTypeForBook.addItem("ISBN");
		whichTypeForBook.addItem("书名");
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
