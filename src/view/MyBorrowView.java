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
import model.BorrowBookModel;
import tablemodel.BBookTableModel;
import widget.InitWindow;
import java.awt.Font;
import java.awt.Color;
/**
 * 我的借阅视图（用户）
 * @author 宽伟
 *
 */

public class MyBorrowView {
	private UserAction userAction=null;
	
	private JFrame frame;

	private JTable table=null;
	
	private List<BorrowBookModel> list=null;//搜索结果列表，保存所有搜索结果
	private BBookTableModel myTableModel =null;
	
	private JButton close;
	private JButton nextPage;
	private JButton formerPage;
	private JButton returnBook;
	
	private int selectedRowIndex=-1;//选中表格中的行
	/**
	 * Create the application.
	 */
	public MyBorrowView() {
		userAction = new UserAction();
		initialize();
		getDate();
		action();
	}
	//单例模式
	public static MyBorrowView myBorrowView;
	public static MyBorrowView getInstance(){
		if(myBorrowView==null){
			myBorrowView= new MyBorrowView();
		}
		return myBorrowView;
	}

	//从UserAction中得到借书数据，并先填充一部分到table里
	public void getDate(){
		
		list = new ArrayList<>();
		
		// 得到总的表
		list = userAction.getBorrowBook();
		myTableModel = new BBookTableModel(table, frame, list);
		myTableModel.initData();
	}
	
	public void action(){
		// 点击上一页
		formerPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(myTableModel.formerPage())
					selectedRowIndex=-1;
			}
		});

		//点击下一页
		nextPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(myTableModel.nextPage())
					selectedRowIndex=-1;
			}
		});
		//关闭此窗口
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				myBorrowView =null;
			}
		});
		
		//点击归还图书
		returnBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selectedRowIndex==-1||table.getValueAt(selectedRowIndex, 1)==null){
					System.out.println("请选中要操作的图书");
				}
				else{
					System.out.println("选择了第"+ selectedRowIndex+ "行");
					String ISBN = (String) table.getValueAt(selectedRowIndex, 1);
					//归还图书操作，用户归还成功之后进行表的刷新
					if(userAction.returnBook(ISBN)){
						//刷新表格
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				// 对用户选取的单行数据操作
				int selectRows = table.getSelectedRows().length;// 取得用户所选行的行数
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// 取得用户所选单行
					// System.out.print(list.get(selectedRowIndex).get("i"));
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
		
		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片

		frame.getContentPane().add(panel);

		JLabel label = new JLabel("\u641C\u7D22\u7ED3\u679C");
		label.setBounds(557, 26, 72, 18);
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 66, 1000, 505);
		panel.add(scrollPane);


		table = new JTable();
		// 设置类不可随数据大小改变
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(60);
		scrollPane.setViewportView(table);

	    nextPage = new JButton("下一页");
	    nextPage.setBackground(new Color(119, 136, 153));
	    nextPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		nextPage.setBounds(633, 646, 113, 42);
		panel.add(nextPage);

	    formerPage = new JButton("上一页");
	    formerPage.setBackground(new Color(119, 136, 153));
	    formerPage.setFont(new Font("华文楷体", Font.PLAIN, 20));
		formerPage.setBounds(393, 646, 113, 42);
		panel.add(formerPage);
		
		returnBook = new JButton("\u5F52\u8FD8");
		returnBook.setBackground(new Color(0, 139, 139));
		returnBook.setFont(new Font("华文楷体", Font.PLAIN, 25));
		returnBook.setBounds(1043, 289, 113, 50);
		panel.add(returnBook);
		
		close = new JButton("\u9000\u51FA");
		close.setBounds(1053, 44, 103, 27);
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
