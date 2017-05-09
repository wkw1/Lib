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
import dao.LogDao;
import dao.SearchKeyDao;
import model.InfoModel;
import model.UserModel;
import widget.InitWindow;
import javax.swing.JComboBox;
import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

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
		//UserModel.userModel.setBNBooks(userAction.getMyBN());//更新我的借书数量
		//我的资料区信息更改
		myInfo.setText("      \nID:"+UserModel.userModel.getID()+"\n"
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
		searchRanking.setText("\n"+rank);
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

		welcome.setText("欢迎:  "+UserModel.userModel.getName()+" !");
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
				LogDao.addLogSystem("用户退出登录");
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
		myBorrow.setBounds(0, 52, 268, 57);
		panel.add(myBorrow);

		myOrder = new JButton(" \u6211\u7684\u9884\u7EA6");
		myOrder.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myOrder.setForeground(new Color(0, 0, 0));
		myOrder.setBackground(new Color(0, 128, 128));
		myOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myOrder.setBounds(0, 122, 268, 57);
		panel.add(myOrder);

		myBorrowHistory = new JButton("\u501F\u4E66\u5386\u53F2");
		myBorrowHistory.setBackground(new Color(0, 128, 128));
		myBorrowHistory.setFont(new Font("华文楷体", Font.PLAIN, 25));
		myBorrowHistory.setBounds(0, 192, 268, 52);
		panel.add(myBorrowHistory);


		keyWord = new JTextField();
		keyWord.setFont(new Font("华文宋体", Font.PLAIN, 20));
		keyWord.setBounds(515, 131, 166, 44);
		keyWord.setColumns(10);
		panel.add(keyWord);

		JLabel label1 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		label1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label1.setBounds(387, 75, 104, 34);
		panel.add(label1);

		JLabel label2 = new JLabel("\u5173\u952E\u5B57");
		label2.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label2.setBounds(397, 131, 104, 44);
		panel.add(label2);

		signOut = new JButton("\u9000\u51FA\u767B\u5F55");
		signOut.setBackground(new Color(255, 0, 0));
		signOut.setFont(new Font("华文楷体", Font.PLAIN, 15));
		signOut.setBounds(1068, 0, 112, 34);
		panel.add(signOut);

	    search = new JButton("\u641C\u7D22");
	    search.setBackground(new Color(32, 178, 170));
	    search.setFont(new Font("华文楷体", Font.PLAIN, 20));
		search.setBounds(469, 200, 125, 44);
		panel.add(search);

		JLabel label_2 = new JLabel("\u6211\u7684\u56FE\u4E66\u9986");
		label_2.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_2.setBounds(31, 6, 125, 33);
		panel.add(label_2);

		welcome = new JLabel("\u6B22\u8FCE");
		welcome.setFont(new Font("华文楷体", Font.PLAIN, 20));
		welcome.setBounds(795, 3, 201, 31);
		panel.add(welcome);

	    searchType = new JComboBox<String>();
		searchType.setBounds(515, 75, 166, 34);
		panel.add(searchType);
		searchType.addItem("ISBN");
		searchType.addItem("书名");
		searchType.addItem("出版社");
		searchType.addItem("作者");
		searchType.addItem("书类型");

		update = new JButton("\u5237\u65B0");
		update.setBackground(new Color(153, 153, 204));
		update.setFont(new Font("华文楷体", Font.PLAIN, 15));
		update.setBounds(149, 6, 68, 33);
		panel.add(update);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(795, 74, 385, 344);
		panel.add(scrollPane);
		
		JLabel label = new JLabel("\u6D88\u606F\u901A\u77E5");
		label.setBackground(new Color(0, 128, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label);
		label.setFont(new Font("华文楷体", Font.PLAIN, 18));
		
		info = new JTextArea();
		scrollPane.setViewportView(info);
		info.setFont(new Font("华文楷体", Font.PLAIN, 18));
		info.setEditable(false);
		info.setBackground(new Color(222, 184, 135));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(795, 417, 385, 338);
		panel.add(scrollPane_1);
		
				JLabel label_1 = new JLabel("\u7CFB\u7EDF\u4ECB\u7ECD");
				scrollPane_1.setColumnHeaderView(label_1);
				label_1.setBackground(new Color(34, 139, 34));
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setFont(new Font("华文楷体", Font.PLAIN, 20));
				
						JTextArea systemInfo = new JTextArea();
						scrollPane_1.setViewportView(systemInfo);
						systemInfo.setLineWrap(true);
						systemInfo.setFont(new Font("华文新魏", Font.PLAIN, 25));
						systemInfo.setEditable(false);
						systemInfo.setBackground(new Color(0, 191, 255));
						systemInfo.setText("\u7CFB\u7EDF\u4ECB\u7ECD");

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 282, 268, 473);
		panel.add(scrollPane_2);

		JLabel label_3 = new JLabel("\u6211\u7684\u8D44\u6599");
		label_3.setBackground(new Color(128, 0, 0));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("华文楷体", Font.PLAIN, 20));
		scrollPane_2.setColumnHeaderView(label_3);

		myInfo = new JTextArea();
		scrollPane_2.setViewportView(myInfo);
		myInfo.setEditable(false);
		myInfo.setFont(new Font("华文楷体", Font.PLAIN, 20));
		myInfo.setBackground(new Color(0, 191, 255));
		myInfo.setText("\u6211\u7684\u8D44\u6599");
		myInfo.setLineWrap(true);
		myInfo.setLineWrap(true);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(394, 316, 287, 439);
		panel.add(scrollPane_3);

		searchRanking = new JTextArea();
		searchRanking.setLineWrap(true);
		scrollPane_3.setViewportView(searchRanking);
		searchRanking.setFont(new Font("华文楷体", Font.PLAIN, 25));
		searchRanking.setToolTipText("");
		searchRanking.setEditable(false);
		searchRanking.setBackground(new Color(210, 180, 140));
		searchRanking.setText("\u641C\u7D22\u6392\u540D");

		JLabel label_4 = new JLabel("\u641C\u7D22\u6392\u540D");
		label_4.setBackground(new Color(0, 0, 205));
		label_4.setFont(new Font("华文楷体", Font.PLAIN, 20));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(label_4);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
