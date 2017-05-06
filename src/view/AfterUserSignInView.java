package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import action.RegisterLoginAction;
import action.UserAction;
import dao.BBHDao;
import dao.SearchKeyDao;
import fileOpreation.BorrowBookFormOp;
import model.InfoModel;
import model.UserModel;
import widget.InitWindow;
import javax.swing.JComboBox;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JScrollPane;

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
	private JButton myOrder;
	private JButton signOut;
	private JButton myBorrowHistory;
	private JButton update;//刷新按钮更新首页数据

	private JTextArea myInfo ;
	private JTextArea searchRanking;
	private JTextArea info;

	private UserAction userAction;

	private boolean first=true;

	/**
	 * Create the application.
	 */
	public AfterUserSignInView() {
		initialize();
		getData();
		action();
	}

	public void updateData(){
		UserModel.userModel.setBNBooks(userAction.getMyBN());//更新我的借书数量
		//我的资料区信息更改
		myInfo.setText("\n          我的资料\n\n"
				      +"      ID:"+UserModel.userModel.getID()+"\n"
		              +"      姓名:"+UserModel.userModel.getName()+"\n"
				      +"      学院:"+UserModel.userModel.getSchool()+"\n"
				      +"      借书数量:"+UserModel.userModel.getBNBooks()+"\n"
				      +"      余额信息:"+UserModel.userModel.getBalance()+"\n");
		SearchKeyDao searchKeyDao= SearchKeyDao.getInstance();
		String rank="";
		for(int i=0;i<10&&i<searchKeyDao.keyWordLists.size();i++){
			rank+="     "+i+" :"+ searchKeyDao.keyWordLists.get(i).keyWord+"\n";
		}
		//搜索排名资料更改
		searchRanking.setText("\n\n"+rank);
	}

	private void getData(){
		//我的资料区信息更改
		userAction = UserAction.getInstance();
		//消息通知
		List<InfoModel> lists = userAction.getInfo();
		String infoString = "";
		if(lists!=null){
			for(int i=0;i<lists.size();i++){
				infoString+="    事件："+lists.get(i).getInformThing() + "\n"+
						"发送人：" +lists.get(i).getInformer() + " "+
						"时间：" +lists.get(i).getInformDate() + "\n";
			}
		}
		else{
			infoString+= "    无通知消息消息";
		}
		info.setText(infoString);
		updateData();
	}

	private void action(){

		welcome.setText("欢迎:  "+UserModel.userModel.getID()+" !");
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
				//生成搜索页面 传递关键字和搜索类型,单例模式
				String word=keyWord.getText().replaceAll("\\s", "");
				SearchKeyDao searchKeyDao = SearchKeyDao.getInstance();
				searchKeyDao.addOne(word);

				SearchResultView window = SearchResultView.getInstance( word, (String) searchType.getSelectedItem(),1);
				window.getFrame().setVisible(true);
			}
		});

		//我的借阅
		myBorrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MyBorrowView myBorrowView = MyBorrowView.getInstance();
				myBorrowView.getFrame().setVisible(true);
			}
		});
		//点击我的预约
		myOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyOrderView myOrderView = MyOrderView.getInstance();
				myOrderView.getFrame().setVisible(true);
			}
		});

		myBorrowHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//从文件中读取数据
				if(first){
					BBHDao bbhDao = BBHDao.getInstance();
					try {
						bbhDao.readBookForm();
						first=false;
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}

				MyBBHView myBBHView = MyBBHView.getInstance();
				myBBHView.getFrame().setVisible(true);

			}
		});

		//退出登录 TODO 销毁UserAction数据
		signOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				RegisterLoginAction.signOut();
				SignInView signInView = new SignInView();
				signInView.getFrame().setVisible(true);
			}
		});

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateData();
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
		myBorrow.setBackground(new Color(0, 128, 128));
		myBorrow.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myBorrow.setBounds(30, 52, 203, 57);
		panel.add(myBorrow);

		myOrder = new JButton(" \u6211\u7684\u9884\u7EA6");
		myOrder.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myOrder.setForeground(new Color(0, 0, 0));
		myOrder.setBackground(new Color(0, 128, 128));
		myOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myOrder.setBounds(30, 122, 203, 57);
		panel.add(myOrder);

		myBorrowHistory = new JButton("\u501F\u4E66\u5386\u53F2");
		myBorrowHistory.setBackground(new Color(0, 128, 128));
		myBorrowHistory.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myBorrowHistory.setBounds(30, 192, 203, 52);
		panel.add(myBorrowHistory);

		myInfo = new JTextArea();
		myInfo.setEditable(false);
		myInfo.setFont(new Font("华文楷体", Font.PLAIN, 20));
		myInfo.setBackground(new Color(60, 179, 113));
		myInfo.setText("\u6211\u7684\u8D44\u6599");
		myInfo.setBounds(0, 257, 273, 498);
		myInfo.setLineWrap(true);
		panel.add(myInfo);


		keyWord = new JTextField();
		keyWord.setFont(new Font("华文宋体", Font.PLAIN, 20));
		keyWord.setBounds(482, 102, 166, 44);
		keyWord.setColumns(10);
		panel.add(keyWord);

		JLabel label1 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		label1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label1.setBounds(364, 52, 104, 34);
		panel.add(label1);

		JLabel label2 = new JLabel("\u5173\u952E\u5B57");
		label2.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label2.setBounds(374, 102, 104, 44);
		panel.add(label2);

		searchRanking= new JTextArea();
		searchRanking.setFont(new Font("华文楷体", Font.PLAIN, 25));
		searchRanking.setToolTipText("");
		searchRanking.setEditable(false);
		searchRanking.setBackground(new Color(188, 143, 143));
		searchRanking.setText("\u641C\u7D22\u6392\u540D");
		searchRanking.setBounds(348, 252, 377, 498);
		panel.add(searchRanking);

		JTextArea systemInfo = new JTextArea();
		systemInfo.setFont(new Font("华文新魏", Font.PLAIN, 25));
		systemInfo.setEditable(false);
		systemInfo.setBackground(new Color(173, 216, 230));
		systemInfo.setText("\u7CFB\u7EDF\u4ECB\u7ECD");
		systemInfo.setBounds(795, 389, 385, 366);
		panel.add(systemInfo);

		signOut = new JButton("\u9000\u51FA\u767B\u5F55");
		signOut.setBackground(new Color(189, 183, 107));
		signOut.setFont(new Font("华文楷体", Font.PLAIN, 15));
		signOut.setBounds(1062, 38, 104, 34);
		panel.add(signOut);

	    search = new JButton("\u641C\u7D22");
	    search.setBackground(new Color(32, 178, 170));
	    search.setFont(new Font("华文楷体", Font.PLAIN, 20));
		search.setBounds(457, 183, 125, 44);
		panel.add(search);

		JLabel label_2 = new JLabel("\u6211\u7684\u56FE\u4E66\u9986");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_2.setBounds(14, 6, 125, 33);
		panel.add(label_2);

		welcome = new JLabel("\u6B22\u8FCE");
		welcome.setFont(new Font("华文楷体", Font.PLAIN, 20));
		welcome.setBounds(979, 7, 201, 25);
		panel.add(welcome);

	    searchType = new JComboBox<String>();
		searchType.setBounds(482, 47, 166, 34);
		panel.add(searchType);
		searchType.addItem("ISBN");
		searchType.addItem("书名");
		searchType.addItem("出版社");
		searchType.addItem("作者");
		searchType.addItem("书类型");

		JLabel label = new JLabel("\u6D88\u606F\u901A\u77E5");
		label.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label.setBounds(955, 39, 87, 31);
		panel.add(label);

		update = new JButton("\u5237\u65B0");
		update.setBackground(new Color(153, 153, 204));
		update.setFont(new Font("华文楷体", Font.PLAIN, 18));
		update.setBounds(123, 5, 78, 34);
		panel.add(update);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(795, 74, 385, 312);
		panel.add(scrollPane);
		
		info = new JTextArea();
		scrollPane.setViewportView(info);
		info.setFont(new Font("华文楷体", Font.PLAIN, 18));
		info.setEditable(false);
		info.setBackground(new Color(153, 153, 255));
		myInfo.setLineWrap(true);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
