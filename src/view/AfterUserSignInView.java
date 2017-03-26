package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import model.UserModel;
import widget.InitWindow;
import javax.swing.JComboBox;
import java.awt.Font;
/**
 * 用户登录后页面
 * @author 宽伟
 *
 */

public class AfterUserSignInView {

	private JFrame frame;
	private JTextField keyWord;
	private JLabel welcome;
	private JComboBox<String> searchType;
	private JButton search;
	private JButton myBorrow;
	private JButton myorder ;
	private JButton signOut;
	private JButton myBorrowHistory;
	private JButton infoMore ;
	
	private JTextArea myInfo ;
	private JTextArea searchRanking;
	private JTextArea info;

	/**
	 * Create the application.
	 */
	public AfterUserSignInView() {
		initialize();
		getData();
		action();
	}
	
	private void getData(){
		//我的资料区信息更改
		myInfo.setText("ID:"+UserModel.getIstance().getID()+"\n");
		//搜索排名资料更改
		searchRanking.setText("\n  计算机 \n  Java\n  C++ \n  "
				+ "Python\n  计算机网络 \n  数据库\n  操作系统");
		//消息通知
		info.setText("这是消息提醒栏");
		
	}

	private void action(){
		
		welcome.setText("欢迎:  "+UserModel.getIstance().getID()+" !");
		/**
		 * 点击搜索按钮，跳转到搜索结果页面
		 * 需执行操作
		 * 1 获取查询的类型  searchType
		 * 2 获取查询关键字 keyWord
		 * 3 判断是否填入信息
		 */
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//生成搜搜页面 传递关键字和搜做类型,单例模式
				SearchResultView window = SearchResultView.getInstance
						( keyWord.getText().replaceAll("\\s", ""),
								(String) searchType.getSelectedItem(),1);
				window.getFrame().setVisible(true);
			}
		});
		
		/**
		 * 点击我的借阅按钮
		 * 查询借书表得到我的借阅情况
		 * 跳转到另一个页面
		 * 
		 */
		myBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MyBorrowView myBorrowView = MyBorrowView.getInstance();
				myBorrowView.getFrame().setVisible(true);
			}
		});
		//点击我的预约
		myorder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyOrderView myOrderView = MyOrderView.getInstance();
				myOrderView.getFrame().setVisible(true);
			}
		});
		
		myBorrowHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyBBHView myBBHView = new MyBBHView();
				myBBHView.getFrame().setVisible(true);
				
			}
		});
		
		//退出登录
		signOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				SignInView signInView = new SignInView();
				signInView.getFrame().setVisible(true);
			}
		});
		
		//更多消息
		infoMore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, 
						"没\nr\nr\ne\ne\n\n\nrer", "帮助", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		InitWindow.init(frame);//初始化窗口
		
		//设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);//设置为透明，可以看到图片
		frame.getContentPane().add(panel);
		
		
		myBorrow = new JButton("\u6211\u7684\u501F\u9605");
		myBorrow.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myBorrow.setBounds(0, 46, 203, 57);
		panel.add(myBorrow);
		
		myorder = new JButton(" \u6211\u7684\u9884\u7EA6");
		myorder.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myorder.setForeground(new Color(0, 0, 0));
		myorder.setBackground(new Color(204, 153, 0));
		myorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myorder.setBounds(0, 122, 203, 57);
		panel.add(myorder);
		
		myBorrowHistory = new JButton("\u501F\u4E66\u5386\u53F2");
		myBorrowHistory.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myBorrowHistory.setBounds(0, 192, 203, 52);
		panel.add(myBorrowHistory);
		
		myInfo = new JTextArea();
		myInfo.setEditable(false);
		myInfo.setFont(new Font("华文楷体", Font.PLAIN, 20));
		myInfo.setBackground(new Color(60, 179, 113));
		myInfo.setText("\u6211\u7684\u8D44\u6599");
		myInfo.setBounds(0, 257, 203, 498);
		panel.add(myInfo);
		
		
		keyWord = new JTextField();
		keyWord.setFont(new Font("华文宋体", Font.PLAIN, 20));
		keyWord.setBounds(393, 102, 166, 44);
		keyWord.setColumns(10);
		panel.add(keyWord);
		
		JLabel label1 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		label1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label1.setBounds(284, 55, 104, 34);
		panel.add(label1);
		
		JLabel label2 = new JLabel("\u5173\u952E\u5B57");
		label2.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label2.setBounds(284, 102, 104, 44);
		panel.add(label2);
		
		searchRanking= new JTextArea();
		searchRanking.setFont(new Font("华文楷体", Font.PLAIN, 25));
		searchRanking.setToolTipText("");
		searchRanking.setEditable(false);
		searchRanking.setBackground(new Color(188, 143, 143));
		searchRanking.setText("\u641C\u7D22\u6392\u540D");
		searchRanking.setBounds(284, 257, 377, 498);
		panel.add(searchRanking);
		
		info = new JTextArea();
		info.setFont(new Font("华文行楷", Font.PLAIN, 25));
		info.setEditable(false);
		info.setBackground(new Color(153, 153, 255));
		info.setBounds(795, 70, 385, 260);
		panel.add(info);
		
		JTextArea systemInfo = new JTextArea();
		systemInfo.setFont(new Font("华文新魏", Font.PLAIN, 25));
		systemInfo.setEditable(false);
		systemInfo.setBackground(new Color(173, 216, 230));
		systemInfo.setText("\u7CFB\u7EDF\u4ECB\u7ECD");
		systemInfo.setBounds(795, 376, 385, 379);
		panel.add(systemInfo);
		
		signOut = new JButton("\u9000\u51FA\u767B\u5F55");
		signOut.setFont(new Font("华文楷体", Font.PLAIN, 15));
		signOut.setBounds(1041, 30, 125, 27);
		panel.add(signOut);
		
	    search = new JButton("\u641C\u7D22");
	    search.setFont(new Font("华文楷体", Font.PLAIN, 20));
		search.setBounds(384, 173, 125, 34);
		panel.add(search);
		
		JLabel label_2 = new JLabel("\u6211\u7684\u56FE\u4E66\u9986");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_2.setBounds(0, 0, 125, 33);
		panel.add(label_2);
		
		welcome = new JLabel("\u6B22\u8FCE");
		welcome.setFont(new Font("华文楷体", Font.PLAIN, 20));
		welcome.setBounds(1041, 7, 125, 18);
		panel.add(welcome);
		
	    searchType = new JComboBox<String>();
		searchType.setBounds(393, 55, 166, 34);
		panel.add(searchType);
		searchType.addItem("ISBN");
		searchType.addItem("书名");
		searchType.addItem("出版社");
		searchType.addItem("作者");
		
		JLabel label = new JLabel("\u6D88\u606F\u901A\u77E5");
		label.setFont(new Font("华文楷体", Font.PLAIN, 15));
		label.setBounds(939, 49, 72, 18);
		panel.add(label);
		
		infoMore = new JButton("\u66F4\u591A");
		infoMore.setFont(new Font("华文楷体", Font.PLAIN, 15));
		infoMore.setBackground(new Color(70, 130, 180));
		infoMore.setBounds(1096, 336, 84, 27);
		panel.add(infoMore);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
